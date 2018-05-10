package demo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import com.google.protobuf.ByteString;

import org.slf4j.LoggerFactory;

import crpyto.CryptographicSignature;
import crpyto.CryptographicUtils;
import de.schildbach.wallet.ui.WalletActivity;
import de.schildbach.wallet.ui.WalletTransactionsFragment;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.bverify.BVerifyServerAPIGrpc;
import io.grpc.bverify.BVerifyServerAPIGrpc.BVerifyServerAPIBlockingStub;
import io.grpc.bverify.CommitmentsRequest;
import io.grpc.bverify.CommitmentsResponse;
import io.grpc.bverify.DataRequest;
import io.grpc.bverify.DataResponse;
import io.grpc.bverify.GetForwardedRequest;
import io.grpc.bverify.GetForwardedResponse;
import io.grpc.bverify.IssueReceiptRequest;
import io.grpc.bverify.PathRequest;
import io.grpc.bverify.PathResponse;
import io.grpc.bverify.Receipt;
import io.grpc.bverify.SubmitRequest;
import io.grpc.bverify.SubmitResponse;
import io.grpc.bverify.TransferReceiptRequest;
import mpt.core.InsufficientAuthenticationDataException;
import mpt.core.InvalidSerializationException;
import mpt.core.Utils;
import mpt.dictionary.MPTDictionaryPartial;
import mpt.set.AuthenticatedSetServer;
import mpt.set.MPTSetFull;
import pki.Account;

public class MockDepositor implements Runnable {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(MockDepositor.class);
	private static Account account = null;
	
	// data 
	private final byte[] adsKey;

	public static final Set<Receipt> adsData = new HashSet<>();
	private static final AuthenticatedSetServer ads = new MPTSetFull();
	
	// witnessing 
	private byte[] currentCommitment;
	private int currentCommitmentNumber;
	
	// gRPC
	private static ManagedChannel channel = null;
	private static BVerifyServerAPIBlockingStub blockingStub = null;

	private final WalletActivity activity;

	public MockDepositor(Account a, String host, int port, WalletActivity activity) {
		this.activity = activity;
		logger.info( "...loading mock depositor connected to server on host: "+host+" port: "+port);

		MockDepositor.channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
		MockDepositor.blockingStub = BVerifyServerAPIGrpc.newBlockingStub(channel);

	    MockDepositor.account = a;
//		this.ads = new MPTSetFull();
//		this.adsData = new HashSet<>();
		assert a.getADSKeys().size() == 1;
		this.adsKey = a.getADSKeys().iterator().next();

		logger.info("...loading mock depositor "+a.getFirstName());
		logger.info( "...cares about ads: "+Utils.byteArrayAsHexString(this.adsKey));

		logger.info( "...getting commitments from server");
		List<byte[]> commitments = this.getCommitments();
		this.currentCommitmentNumber = commitments.size()-1;
		this.currentCommitment = commitments.get(this.currentCommitmentNumber);
		
		logger.info( "...current commitment: #"+this.currentCommitmentNumber+" - "+
				Utils.byteArrayAsHexString(this.currentCommitment));

		logger.info( "...asking for data from the server");
		List<Receipt> receipts = this.getDataRequest(this.adsKey, this.currentCommitmentNumber);
		for(Receipt r : receipts) {
			logger.info( "...adding receipt: ");
			this.adsData.add(r);
			updateUI();
			byte[] receiptWitness = CryptographicUtils.witnessReceipt(r);
			this.ads.insert(receiptWitness);
		}
		
		logger.info( "...asking for a proof, checking latest commitment");
		this.checkCommitment(this.currentCommitment, this.currentCommitmentNumber);
		logger.info( "...setup complete!");

	}

	public static void updateUI(){
		WalletTransactionsFragment.receiptList.removeAll(WalletTransactionsFragment.receiptList);
		WalletTransactionsFragment.receiptList.addAll(MockDepositor.adsData);
		WalletTransactionsFragment.receiptAdapter.notifyDataSetChanged();
	}
//
//	public void checkForCommitments(){
//		logger.info( "...polling server for forwarded requests");
//		IssueReceiptRequest request = this.getForwarded();
//		if(request != null) {
//			logger.info( "...request recieved, sending to UI");
//			sendApprovalRequestToUI(request);
//
////			IssueReceiptRequest approvedRequest = this.approveRequestAndApply(request);
////			logger.info( "...submitting approved request to server");
////			this.submitApprovedRequest(approvedRequest);
//		}
//		logger.info( "...polling sever for new commitments");
//		List<byte[]> commitments  = this.getCommitments();
//		// get the new commitments if any
//		List<byte[]> newCommitments = commitments.subList(this.currentCommitmentNumber+1, commitments.size());
//		if(newCommitments.size() > 0) {
//			for(byte[] newCommitment : newCommitments) {
//				int newCommitmentNumber = this.currentCommitmentNumber + 1;
//				logger.info( "...new commitment found asking for proof");
//				boolean result = this.checkCommitment(newCommitment, newCommitmentNumber);
//				this.currentCommitmentNumber = newCommitmentNumber;
//				this.currentCommitment = newCommitment;
//			}
//		}
//	}
	/**
	 * Periodically the mock depositor polls the serve and approves any requests
	 */
	@Override
	public void run() {
		logger.info( "...polling server for forwarded requests");
		GetForwardedResponse approvals = this.getForwarded();
		if(approvals.hasIssueReceipt()) {
			sendApprovalRequestToUI(approvals.getIssueReceipt());
//			logger.info( "...submitting approved request to UI");
//			IssueReceiptRequest approvedRequest = this.approveRequestAndApply(approvals.getIssueReceipt());
//			MockDepositor.submitApprovedRequest(approvedRequest);
		}
		if(approvals.hasTransferReceipt()) {
			TransferReceiptRequest approvedRequest = this.approveTransferRequestAndApply(approvals.getTransferReceipt());
			logger.info( "...submitting approved request to server");
			MockDepositor.submitApprovedRequest(approvedRequest);
		}
		logger.info( "...polling sever for new commitments");
		List<byte[]> commitments  = this.getCommitments();
		// get the new commitments if any
		List<byte[]> newCommitments = commitments.subList(this.currentCommitmentNumber+1, commitments.size());
		if(newCommitments.size() > 0) {
			for(byte[] newCommitment : newCommitments) {
				int newCommitmentNumber = this.currentCommitmentNumber + 1;
				logger.info( "...new commitment found asking for proof");
				this.checkCommitment(newCommitment, newCommitmentNumber);
				this.currentCommitmentNumber = newCommitmentNumber;
				this.currentCommitment = newCommitment;
			}
		}
	}
//	public void run() {
//		logger.info( "...polling server for forwarded requests");
//		IssueReceiptRequest request = this.getForwarded();
//		if(request != null) {
//			logger.info( "...request recieved, sending to UI");
//			sendApprovalRequestToUI(request);
//
////			IssueReceiptRequest approvedRequest = this.approveRequestAndApply(request);
//////			logger.info( "...submitting approved request to server");
////			this.submitApprovedRequest(approvedRequest);
//		}
////		logger.info( "...polling sever for new commitments");
//		List<byte[]> commitments  = this.getCommitments();
//		// get the new commitments if any
//		List<byte[]> newCommitments = commitments.subList(this.currentCommitmentNumber+1, commitments.size());
//		if(newCommitments.size() > 0) {
//			for(byte[] newCommitment : newCommitments) {
//				int newCommitmentNumber = this.currentCommitmentNumber + 1;
////				logger.info( "...new commitment found asking for proof");
//				boolean result = this.checkCommitment(newCommitment, newCommitmentNumber);
//				this.currentCommitmentNumber = newCommitmentNumber;
//				this.currentCommitment = newCommitment;
//			}
//		}
//	}
	
	public void shutdown() throws InterruptedException {
	    this.channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	}
	
	private List<byte[]> getCommitments() {
		CommitmentsRequest request = CommitmentsRequest.newBuilder().build();
		CommitmentsResponse response = this.blockingStub.getCommitments(request);
		ArrayList<byte[]> commitments = new ArrayList<byte[]>();
		for (ByteString str : response.getCommitmentsList()){
			commitments.add(str.toByteArray());
		}
		return commitments;
	}
	
	private List<Receipt> getDataRequest(byte[] adsId, int commitmentNumber){
		DataRequest request = DataRequest.newBuilder()
				.setAdsId(ByteString.copyFrom(adsId))
				.setCommitmentNumber(commitmentNumber)
				.build();
		DataResponse response = this.blockingStub.getDataRequest(request);
		return response.getReceiptsList();
		
	}
	
	private MPTDictionaryPartial getPath(List<byte[]> adsIds, int commitment) {
		ArrayList<ByteString> adIdsList = new ArrayList<ByteString>();
		for (byte[] adId: adsIds){
			adIdsList.add(ByteString.copyFrom(adId));
		}
		PathRequest request = PathRequest.newBuilder()
				.setCommitmentNumber(commitment)
				.addAllAdsIds(adIdsList)
				.build();
		PathResponse response = this.blockingStub.getAuthPath(request);
		MPTDictionaryPartial res;
		try {
			res = MPTDictionaryPartial.deserialize(response.getPath());
		} catch (InvalidSerializationException e) {
			e.printStackTrace();
			throw new RuntimeException("MPT cannot be deserialized");
		}
		return res;
	}

	private GetForwardedResponse getForwarded() {
		GetForwardedRequest request = GetForwardedRequest.newBuilder()
				.setId(this.account.getIdAsString())
				.build();
		return this.blockingStub.getForwarded(request);
	}

	private void sendApprovalRequestToUI(IssueReceiptRequest request){
		activity.handleReceiptIssue(request);
	}

	public static IssueReceiptRequest approveRequestAndApply(IssueReceiptRequest request) {
//		logger.info( "...approving request: "+request);
		Receipt r = request.getReceipt();
		MockDepositor.adsData.add(r);
		WalletTransactionsFragment.receiptList.removeAll(WalletTransactionsFragment.receiptList);
		WalletTransactionsFragment.receiptList.addAll(MockDepositor.adsData);
		updateUI();
		byte[] witness = CryptographicUtils.witnessReceipt(r);
		MockDepositor.ads.insert(witness);
		byte[] newRoot = MockDepositor.ads.commitment();
//		logger.info( "...NEW ADS ROOT: "+Utils.byteArrayAsHexString(newRoot));
		byte[] sig = CryptographicSignature.sign(newRoot, MockDepositor.account.getPrivateKey());
		return request.toBuilder().setSignatureDepositor(ByteString.copyFrom(sig)).build();
	}


	
	public static boolean submitApprovedRequest(IssueReceiptRequest request) {
//		logger.info( "...submitting request to server: "+request);
		assert request.getSignatureDepositor().toByteArray() != null;
		assert request.getSignatureWarehouse().toByteArray() != null;
		SubmitRequest requestToSend = SubmitRequest.newBuilder()
				.setIssueReceipt(request)
				.build();
		SubmitResponse response = MockDepositor.blockingStub.submit(requestToSend);
		boolean accepted = response.getAccepted();
//		logger.info("...accepted? - "+accepted);
		return accepted;
	}

	private static boolean submitApprovedRequest(TransferReceiptRequest request) {
		logger.info("...submitting request to server: ");
		SubmitRequest requestToSend = SubmitRequest.newBuilder()
				.setTransferReceipt(request)
				.build();
		SubmitResponse response = MockDepositor.blockingStub.submit(requestToSend);
		boolean accepted = response.getAccepted();
		logger.info("...accepted? - ");
		return accepted;
	}

	private TransferReceiptRequest approveTransferRequestAndApply(TransferReceiptRequest request) {
		logger.info( "...approving transfer request: "+request);
		Receipt r = request.getReceipt();
		byte[] witness = CryptographicUtils.witnessReceipt(r);
		if(request.getCurrentOwnerId().equals(this.account.getIdAsString())) {
			logger.info( "...removing receipt");
			this.adsData.remove(r);
			this.ads.delete(witness);
			byte[] newRoot = this.ads.commitment();
			logger.info( "...NEW ADS ROOT: "+Utils.byteArrayAsHexString(newRoot));
			byte[] sig = CryptographicSignature.sign(newRoot, this.account.getPrivateKey());
			return request.toBuilder().setSignatureCurrentOwner(ByteString.copyFrom(sig)).build();
		}
		logger.info( "...adding receipt");
		this.ads.insert(witness);
		this.adsData.add(r);
		byte[] newRoot = this.ads.commitment();
		logger.info( "...NEW ADS ROOT: "+Utils.byteArrayAsHexString(newRoot));
		byte[] sig = CryptographicSignature.sign(newRoot, this.account.getPrivateKey());
		return request.toBuilder().setSignatureNewOwner(ByteString.copyFrom(sig)).build();
	}

	
	private boolean checkCommitment(final byte[] commitment, final int commitmentNumber) {
		logger.info( "...checking commtiment : #"+commitmentNumber+
				" | "+Utils.byteArrayAsHexString(commitment));
		logger.info( "...asking for proof from the server");
		MPTDictionaryPartial mpt = this.getPath(Arrays.asList(this.adsKey), commitmentNumber);
		logger.info( "...checking proof");
		// check that the auth proof is correct
		try {
			logger.info( "...checking that mapping is correct");
			if(!Arrays.equals(mpt.get(this.adsKey), this.ads.commitment())){
				logger.warn( "...MAPPING DOES NOT MATCH");
				System.err.println("MAPPING DOES NOT MATCH");
				return false;
			}
			logger.info( "...checking that commitment matches");
			System.out.println(Utils.byteArrayAsHexString(mpt.commitment()));
			System.out.println(Utils.byteArrayAsHexString(commitment));
			if(!Arrays.equals(commitment, mpt.commitment())) {
				logger.warn( "...COMMITMENT DOES NOT MATCH");
				System.err.println("COMMITMENT DOES NOT MATCH");
			}
			logger.info( "...commitment accepted");
			return true;
		} catch (InsufficientAuthenticationDataException e) {
			e.printStackTrace();
			System.err.println("Error!");
			throw new RuntimeException("bad proof!");
		}
	}

	
}

package pki;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;


/**
 * This is class is responsible for providing 
 * the public keys of the various participants
 * 
 * Participants are identified and 
 * looked up their unique user ids 
 * (UUIDs). 
 * 
 * @author henryaspegren
 *
 */
public class PKIDirectory {
	
	private final Map<UUID, Account> lookupTable;
	private final List<UUID> uuids; 
	
	public PKIDirectory(List<InputStream> listOfFiles) {


		this.lookupTable = new HashMap<>();
		this.uuids = new ArrayList<>();
//
//        for(InputStream f : listOfFiles) {
//            if(f != null) {
//                Account a = Account.loafFromFile(f);
//                if(a != null) {
//                    this.uuids.add(a.getId());
//                    this.lookupTable.put(a.getId(), a);
//                }
//            }
//        }

	}



	private File createFileFromInputStream(InputStream inputStream) {

		try{
			File f = new File("new FilePath");
			OutputStream outputStream = new FileOutputStream(f);
			byte buffer[] = new byte[1024];
			int length = 0;

			while((length=inputStream.read(buffer)) > 0) {
				outputStream.write(buffer,0,length);
			}

			outputStream.close();
			inputStream.close();

			return f;
		}catch (IOException e) {
		    e.printStackTrace();
			//Logging exception
		}

		return null;
	}

	public Account getAccount(UUID id) {
		return this.lookupTable.get(id);
	}
	
	public Account getAccount(String uuidString) {
		UUID uuid = UUID.fromString(uuidString);
		return this.getAccount(uuid);
	}
	
	public Set<Account> getAllAccounts(){
		Set<Account> res = new HashSet<>();
		for(Account a : this.lookupTable.values()) {
			res.add(a);
		}
		return res;
	}
	
	public Account getAccount(int i) {
		if(0 <= i && i < this.uuids.size()) {
			return this.lookupTable.get(uuids.get(i));
		}
		return null;
	}
	
	public Set<UUID> getAllAccountIDs(){
		return this.lookupTable.keySet();
	}
	
	public static List<Account> generateRandomAccounts(int numberOfAccounts) {
		List<Account> accounts = new ArrayList<>();
		for(int i = 0; i < numberOfAccounts; i++) {
			String firstName = "Alice";
			String lastName = "Bob";
			Account account = new Account(firstName, lastName);
			accounts.add(account);
			System.out.println("generating account "+(i+1)+
					" - of - "+numberOfAccounts+"("+ "Alice Bob"+")");
		}
		return accounts;
	}
	
}

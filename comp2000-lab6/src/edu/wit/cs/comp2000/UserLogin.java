/**
 * A program to read in and modify password files using salted SHA-512 hashes
 */
package edu.wit.cs.comp2000;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;

public class UserLogin {

	static Map<String, Password> userTable = new Hashtable<String, Password>();	// stores all relevant info about authentication
	private static final Random RandomObj = new SecureRandom();
	private static ArrayList<String> users = new ArrayList<String>();

	/**
	 * Reads lines from the userFile, parses them, and adds the results to the userTable
	 * 
	 * @param userFile A File object to read from
	 * @throws FileNotFoundException 
	 *  
	 */
	public static void ReadFile(File userFile) throws FileNotFoundException {
	
		Scanner s = new Scanner(userFile);
		userTable.clear();
		while(s.hasNextLine()) {
			//Splits up each line into 3 parts and adds them to the corresponding ArrayList
			String[] stuff = s.nextLine().split(":");
			
			//Only add complete entries to the userTable 
			
			try {
			userTable.put(stuff[0], new Password(stuff[2],stuff[1]));
			}catch(ArrayIndexOutOfBoundsException e) {
				System.out.println("Error. Data is not formatted correctly.");
			}
		}
		
	}

	/**
	 * Iterates through all key values in userTable and outputs lines
	 * to userFile formatted like user:salt:hash
	 * 
	 * @param userFile A File object that the table should be written to
	 * @throws IOException 
	 */
	public static void WriteFile(File userFile) throws IOException {
		
		FileOutputStream os = new FileOutputStream(userFile, true);
	    byte[] strByte;
		String h;
	    for(String key : users) {
	    		h = "\n"+key+":"+userTable.get(key)+"\n";
	    		strByte = h.getBytes();
	    		os.write(strByte);
	    	}
		}
	
	

	
	/**
	 * Prompts the user for a username/password and attempts to add the user
	 * to the userTable. Fails if the user is already present in the table.
	 * 
	 * @param s A Scanner to read from the console
	 * @return boolean based on if the user credentials are added to table
	 */
	public static boolean AddUser(Scanner s) {
		
		System.out.print("Enter user to add: ");
		String new_user = s.next();
		
		if(userTable.containsKey(new_user))
			return false;
		
		String new_salt = genSalt();
		System.out.print("Enter a password: ");
		String pass = s.next();
		
		//Create new password object equal to the salt appended to the user input password
		Password p = new Password(genHash(new_salt+pass),new_salt);
		
		if(!userTable.containsKey(new_user)) {
			userTable.put(new_user,p);
			users.add(new_user);
			return true;
		}
		else
			return false;
	
	}
	private static String getHashedP(Password p) {
		return p.getSalt()+":"+p.getHash();
	}

	/**
	 * Prompts the user for a username/password and checks the userTable for
	 * the resulting combination
	 * 
	 * @param s A Scanner to read from the console
	 * @return boolean based on if the user credentials are accurate
	 */
	public static boolean Login(Scanner s) {
		
		System.out.print("Enter username: ");
		String user = s.next();
		
		System.out.print("Enter password: ");
		String password = s.next();
		
		if(userTable.containsKey(user)){
			return userTable.get(user).getHash().equals((genHash(userTable.get(user).getSalt()+password)));
				
		}
		else 
			return false;
	}


	/**
	 * Generates a salt value based on the SecureRandom object
	 * 
	 * @return Returns an 8-character string that represents a random value
	 */
	private static String genSalt() {
		// TODO Auto-generated method stub
		byte[] salt = new byte[8];
		RandomObj.nextBytes(salt);

		return byteArrayToStr(salt);
	}
	
	/**
	 * Converts an array of bytes to the corresponding hex String
	 * 
	 * @param b An array of bytes
	 * @return A String that represents the array of bytes in hex
	 */
	private static String byteArrayToStr(byte[] b) {
		StringBuffer hexHash =  new StringBuffer();
		for (int i = 0; i < b.length; i++)
		{
			String hexChar = Integer.toHexString(0xff & b[i]);
			if (hexChar.length() == 1)
			{
				hexHash.append('0');
			} // end if
			hexHash.append(hexChar);
		} // end for
		return hexHash.toString();
	}

	/**
	 * Generates a hash for a given String
	 * 
	 * @param p A String to calculate the hash from
	 * @return The hash value as a String
	 */
	private static String genHash(String p) {
		// Create the MessageDigest object
		MessageDigest myDigest = null;
		try {
			myDigest = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			System.err.println("SHA-512 not available");
			System.exit(1);
		}
		// Update the object with the hash of ‘someStringToHash’
		myDigest.update(p.getBytes());
		
		// Get the SHA-512 hash from the object
		byte hashCode[] = myDigest.digest();

		return byteArrayToStr(hashCode);
	}

	
	public static void main(String[] args) throws IOException {

		Scanner s = new Scanner(System.in);

		System.out.print("Enter user file: ");
		File userFile = new File(s.nextLine());
		ReadFile(userFile);
		
		while (true) {
			System.out.print ("Would you like to (L)og in, (A)dd a new user, or (Q)uit? ");
			String choice = s.nextLine().substring(0);

			switch (choice) {
			case "L":
				if (Login(s))
					System.out.println("Login successful.");
				else
					System.out.println("Username and password did not match.");
				break;
			case "A":
				if (AddUser(s)) {
					System.out.println("User successfully added.");
					break;
				}
				else
					System.out.println("User not added.");
				break;
			case "Q":
				WriteFile(userFile);
				s.close();
				System.out.println("Exiting.");
				System.exit(0);
			}
		}

	}

}

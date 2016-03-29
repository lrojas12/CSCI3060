/**
 * This software is the back-end design of the Watermelon Banking System.
 * It is to be ran as a console application.
 * Input(s): File input only (<\old_master_accounts> <\merged_transaction_files>).
 * Output(s): File output only (new_current_bank_accounts_file and
 *        new_master_bank_accounts_file).
 *
 * Main.java
 * 
 * @author Luisa Rojas
 * @author Truyen Truong
 * @author Danesh Parthipan
 */

import java.io.*;
import java.util.*;

public class Main {
	
  public static List<String> transferFile = new ArrayList<String>();
  public static List<String> masterAccounts = new ArrayList<String>();
  public static List<String> currentAccounts = new ArrayList<String>();
  public static List<User> userAccounts = new ArrayList<User>();
  public static User currUser;

  /**
   * Reads in the old master bank accounts file and stores it.
   *
   * @param fileName    file from which the old master bank accounts file is being read from
   * @param arrayList   list to which the old master bank accounts file is being stored in
   */
  public static void storeFile(String fileName, List<String> arrayList) {

  	// Creates a buffered reader for the file based on file name given.
  	try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      String line;
      // Reads in the file line by line
      while ((line = br.readLine()) != null) {
      	// Stores the line based on list to store in.
        arrayList.add(line);
      }
    } catch (IOException e) {
      System.err.println("ERROR: Could not read in file.");
    }
  }

  public static void main(String[] args) {

  	currUser = new User();

    if (args.length != 2) {

      System.err.println("ERROR: Must be 2 arguments: <old_master_accounts> <transaction_file>");

    } else {

      String oldMasterFileName = args[0];
      String transferFileName = args[1];

      // Read in old master bank accounts file.
      storeFile(oldMasterFileName, masterAccounts);

      // Read in transaction file.
      storeFile(transferFileName, transferFile);

      
      System.out.println("\nMaster Bank Accounts File\n");
      for (int i=0; i<masterAccounts.size(); i++) {
      	System.out.println(masterAccounts.get(i));
      }

      // Create a User List based on masterAccounts for easy access to variables
      Utilities.getAllMasterAccounts(masterAccounts);

      // Update the master bank accounts file.
      UpdateMaster.updateMaster();

      System.out.println("\nMaster Bank Accounts File\n");
      for (int i=0; i<userAccounts.size(); i++) {
      	System.out.println(userAccounts.get(i).getName() + " " + userAccounts.get(i).getBalance() + " " + userAccounts.get(i).getStatus() + " " + userAccounts.get(i).getPlan());
      }
    }
  }
}
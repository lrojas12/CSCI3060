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

  public static String oldMasterFileName;
  public static String transactionFileName;
  public static List<String> transactionFile = new ArrayList<String>();
  public static List<String> masterAccounts = new ArrayList<String>();
  public static List<String> currentAccounts = new ArrayList<String>();
  public static List<User> userAccounts = new ArrayList<User>();
  public static User currUser;

  public static void main(String[] args) {
 
  	currUser = new User();

    if (args.length != 2) {

      System.err.println("ERROR: User input must be 2 arguments: <old_master_accounts> <transaction_file>");

    } else {
      // Get master bank accounts file name.
      oldMasterFileName = args[0];
      // Get transaction file name.
      transactionFileName = args[1];

      // Read in old master bank accounts file.
      Utilities.storeFile(oldMasterFileName, masterAccounts);

      // Read in transaction file.
      Utilities.storeFile(transactionFileName, transactionFile);

      // Create a User List based on masterAccounts for easy access to variables
      Utilities.getAllMasterAccounts(masterAccounts);

      // Update the master bank accounts file.
      UpdateMaster.updateMaster(oldMasterFileName);
    }
  }
}
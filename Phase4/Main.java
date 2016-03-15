import java.io.*;
import java.util.*;

public class Main {
	
  public static List<String> mergedTransferFiles = new ArrayList<String>();
  public static List<String> oldMasterAccounts = new ArrayList<String>();
  public static List<String> newMasterAccounts = new ArrayList<String>();

  /*
   * Reads in the old master bank accounts file and stores it.
   */
  public static void storeFile(String fileName, List<String> arrayList) {

  	// Creates a buffered reader for the old master bank accounts file
  	try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      String line;
      // Reads in the file line by line
      while ((line = br.readLine()) != null) {
      	// Stores the line.
        arrayList.add(line);
      }
    } catch (IOException e) {
      System.err.println("ERROR: Could not read old master bank accounts file.");
    }
  }

  /*
   * Reads in, merges and then stores the transaction files.
   */
  public static void mergeFiles(String[] array) {

    // Loops over every transaction file.
  	for (int i=0; i<array.length; i++) {
      storeFile(array[i], mergedTransferFiles);
    }
  }

  public static void main(String[] args) {

    if (args.length < 2 || args[1].equals("transaction_file_*.tra")) {

      System.err.println("ERROR: At least 2 arguments are needed: <old_master_accounts> <transaction_files>");

    } else {

      String oldMasterFileName = args[0];
      //System.out.println("inputMaster: " + oldMasterFileName);

      String[] transferFileNames = Arrays.copyOfRange(args, 1, args.length);

      /*for (int i = 0; i < transferFileNames.length; i++) {
        System.out.println(transferFileNames[i]);
      }*/

      // Read in old master bank accounts file.
      storeFile(oldMasterFileName, oldMasterAccounts);

      // Read in and merge all transaction files.
      mergeFiles(transferFileNames);

      
      System.out.println("\nMaster Bank Accounts File\n");
      for (int i=0; i<oldMasterAccounts.size(); i++) {
      	System.out.println(oldMasterAccounts.get(i));
      }

      System.out.println("\nMerged Transfer Files\n");
      for (int i=0; i<mergedTransferFiles.size(); i++) {
      	System.out.println(mergedTransferFiles.get(i));
      }
      
    }
  }
}
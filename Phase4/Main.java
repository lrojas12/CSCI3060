import java.io.*;
import java.util.*;

public class Main {
	
  public static List<String> mergedTransferFiles = new ArrayList<String>();
  public static List<String> masterAccounts = new ArrayList<String>();
//public static List<String> newMasterAccounts = new ArrayList<String>();

  /*
   * Reads in the old master bank accounts file and stores it.
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

  /*
   * Merges and stores all transaction files.
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
      String[] transferFileNames = Arrays.copyOfRange(args, 1, args.length);

      // Read in old master bank accounts file.
      storeFile(oldMasterFileName, masterAccounts);

      // Read in and merge all transaction files.
      mergeFiles(transferFileNames);

      /*
      System.out.println("\nMaster Bank Accounts File\n");
      for (int i=0; i<masterAccounts.size(); i++) {
      	System.out.println(masterAccounts.get(i));
      }

      System.out.println("\nMerged Transfer Files\n");
      for (int i=0; i<mergedTransferFiles.size(); i++) {
      	System.out.println(mergedTransferFiles.get(i));
      }
      */
    }
  }
}
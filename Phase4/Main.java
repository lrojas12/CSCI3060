import java.io.*;
import java.util.*;

public class Main {
	
  public static String oldMasterFileName;
  public static String[] transferFileNames;
  public static List<String> mergedTransferFiles = new ArrayList<String>();
  public static List<String> oldMasterAccounts = new ArrayList<String>();
  public static List<String> newMasterAccounts = new ArrayList<String>();

  /*
   * Reads in the old master bank accounts file and stores it.
   */
  public static void storeMasterFile() {

  	// Creates a buffered reader for the old master bank accounts file
  	try (BufferedReader br = new BufferedReader(new FileReader(oldMasterFileName))) {
      String line;
      // Reads in the file line by line
      while ((line = br.readLine()) != null) {
      	// Stores the line.
        oldMasterAccounts.add(line);
      }
    } catch (IOException e) {
      System.err.println("ERROR: Trouble reading in old master bank accounts file.");
    }
  }

  /*
   * Reads in, merges and then stores the transaction files.
   */
  public static void mergeTransferFiles() {

    // Loops over every transaction file.
  	for (int i=0; i<transferFileNames.length; i++) {

      // Creates a buffered read for the transaction files.
      try (BufferedReader br = new BufferedReader(new FileReader(transferFileNames[i]))) {
        String line;
        // Reads in the files line by line
        while ((line = br.readLine()) != null) {
          // Stores the line.
          mergedTransferFiles.add(line);
        }
      } catch (IOException e) {
        System.err.println("ERROR: Trouble reading in transaction files.");
      }
    }
  }

  public static void main(String[] args) {

    if (args.length < 2 || args[1].equals("transaction_file_*.tra")) {

      System.err.println("ERROR: At least 2 arguments are needed: <old_master_accounts> <transaction_files>");

    } else {

      oldMasterFileName = args[0];
      //System.out.println("inputMaster: " + oldMasterFileName);

      transferFileNames = Arrays.copyOfRange(args, 1, args.length);

      /*for (int i = 0; i < transferFileNames.length; i++) {
        System.out.println(transferFileNames[i]);
      }*/

      // Read in old master bank accounts file.
      storeMasterFile();

      // Read in and merge all transaction files.
      mergeTransferFiles();

      System.out.println("\n\nMaster Bank Accounts File\n");

      for (int i=0; i<oldMasterAccounts.size(); i++) {
      	System.out.println(oldMasterAccounts.get(i));
      }

      System.out.println("\n\nMerged Transfer Files\n");
      for (int i=0; i<mergedTransferFiles.size(); i++) {
      	System.out.println(mergedTransferFiles.get(i));
      }
    }
  }
}
import java.io.*;
import java.util.*;

public class Main {
	
  public static String oldMasterFileName;
  public static String[] transferFileNames;
  public static List<String> mergedTransferFiles = new ArrayList<String>();
  public static List<String> oldMasterAccounts = new ArrayList<String>();
  public static List<String> newMasterAccounts = new ArrayList<String>();

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
      try (BufferedReader br = new BufferedReader(new FileReader(oldMasterFileName))) {
        String line;
        while ((line = br.readLine()) != null) {
          oldMasterAccounts.add(line);
        }
      } catch (IOException e) {
        System.err.println("ERROR: Trouble reading in old master bank accounts file.");
      }

      // Read in and merge all transaction files.
      for (int i=0; i<transferFileNames.length; i++) {
      	try (BufferedReader br = new BufferedReader(new FileReader(transferFileNames[i]))) {
          String line;
          while ((line = br.readLine()) != null) {
            mergedTransferFiles.add(line);
          }
        } catch (IOException e) {
          System.err.println("ERROR: Trouble reading in transaction files.");
        }
      }
    }
  }
}
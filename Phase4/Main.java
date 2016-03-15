import java.io.*;
import java.util.*;

public class Main {
	
	public static String oldMasterFileName;
	public static String[] transferFileNames;
	public static List<String> merged_transfer_files = new ArrayList<String>();
	public static List<String> old_master_accounts = new ArrayList<String>();
	public static List<String> new_master_accounts = new ArrayList<String>();

	public static void main(String[] args) {

		if (args.length < 2 || args[1].equals("transaction_file_*.tra")) {

			System.out.println("ERROR: At least 2 arguments are needed: <old_master_accounts> <transaction_files>");

		} else {

			oldMasterFileName = args[0];
			System.out.println("inputMaster: " + oldMasterFileName);

			transferFileNames = Arrays.copyOfRange(args, 1, args.length);

			for (int i = 0; i < transferFileNames.length; i++) {
				System.out.println(transferFileNames[i]);
			}
		}
	}
}
import java.io.*;
import java.util.*;

public class Main {
	
	public static String inputMaster;
	public static String[] transferFiles;

	public static void main(String[] args) {

		if (args.length < 2 || args[1].equals("transaction_file_*.tra")) {

			System.out.println("ERROR: At least 2 arguments are needed: <old_master_accounts> <transaction_files>");

		} else {

			inputMaster = args[0];
			System.out.println("inputMaster: " + inputMaster);

			transferFiles = Arrays.copyOfRange(args, 1, args.length);

			for (int i = 0; i < transferFiles.length; i++) {
				System.out.println(transferFiles[i]);
			}

		}
	}
}
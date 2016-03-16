import java.io.*;
import java.util.*;

public class WatermelonHelper {

	public static String flag, accHolderT, accNumT, accBalanceT, misc;
	public static String accNumM, accHolderM, accStatus, accBalanceM, numTran, accPlan;

	public static void tokenizeTransaction(String line) {
		
		flag = line.substring(0, 2);
		accHolderT = line.substring(3, 23).trim();
		accNumT = line.substring(24, 29);
		accBalanceT = line.substring(30, 38);
		misc = line.substring(40);
	}

	public static void tokenizeMaster(String line) {
		
		accNumM = line.substring(0, 5);
		accHolderM = line.substring(6, 26).trim();
		accStatus = line.substring(27, 28);
		accBalanceM = line.substring(29, 37);
		numTran = line.substring(38, 42);
		accPlan = line.substring(43);
	}

	public static boolean isAdmin(char accMode) {
		if (accMode == 'A') {
			return true;
		} else if (accMode == 'S') {
			return false;
		} else {
			System.err.println("ERROR: Unable to retrieve account mode.");
			return false;
			// Skip to the next line
		}
	}

	public static void getAllMasterAccounts(List<String> masterList) {
		for (int i = 0; i < masterList.size(); i++) {
			String line = masterList.get(i);
			tokenizeMaster(line);
			User user = new User(accHolderM.trim(), Integer.parseInt(accNumM),
				Float.valueOf(accBalanceM).floatValue(), accStatus.charAt(0),
				Integer.parseInt(numTran), accPlan.charAt(0));
			Main.userAccounts.add(user);
		}
	}

	public static void rewriteMasterFile() {
		// Output masterAccounts to a master_bank_accounts_file.txt
	}

	public static void rewriteCurrentFile() {
		// Output currentAccounts to a current_bank_accounts_file.txt
	}

	public static boolean isNumberUnique(int accNum) {
		return true;
	}
}
import java.io.*;
import java.util.*;

public class UpdateMaster {

	public static void updateMaster() {

		// For each line in the merged transfer files list
		for (int i = 0; i < mergedTransferFiles.size(); i++) {
			// Tokenize it
        	WatermelonHelper.tokenizeTransaction(mergedTransferFiles.get(i));

        	switch(WatermelonHelper.flag) {

        	// Withdrawal
			case "01":
				withdrawal();
				break;

			// Transfer
			case "02":
				transfer();
				break;

			// Paybill
			case "03":
				paybill();
				break;
			
			// Deposit
			case "04":
				deposit();
				break;
			
			// Create
			case "05":
				create();
				break;
			
			// Delete
			case "06":
				delete();
				break;
			
			// Disable
			case "07":
				disable();
				break;
			
			// Changle Plan
			case "08":
				changePlan();
				break;

			// Enable
			case "09":
				enable();
				break;

			// Login
			case "10":
				break;
			
			// Logout
			case "00":
				break;

			default:

		}
	}

	public static void withdrawal() {

	}

	public static void transfer() {

	}

	public static void paybill() {

	}

	public static void deposit() {

	}

	public static void create() {

	}

	public static void delete() {

	}

	public static void disable() {

	}

	public static void changePlan() {

	}

	public static void enable() {

	}
}
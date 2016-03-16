import java.io.*;
import java.util.*;

public class UpdateMaster {

	public static void updateMaster() {

		// For each line in the merged transfer files list
		for (int i = 0; i < Main.mergedTransferFiles.size(); i++) {
			// Tokenize it
        	WatermelonHelper.tokenizeTransaction(Main.mergedTransferFiles.get(i));

        	switch(WatermelonHelper.flag) {

        	// Withdrawal
			case "01":
				withdrawal(Integer.parseInt(WatermelonHelper.accNumT),
					Float.valueOf(WatermelonHelper.accBalanceT).floatValue());
				break;

			// Transfer
			case "02":
				//transfer();
				break;

			// Paybill
			case "03":
				//paybill();
				break;
			
			// Deposit
			case "04":
				//deposit();
				break;
			
			// Create
			case "05":
				//create();
				break;
			
			// Delete
			case "06":
				//delete();
				break;
			
			// Disable
			case "07":
				//disable();
				break;
			
			// Changle Plan
			case "08":
				//changePlan();
				break;

			// Enable
			case "09":
				//enable();
				break;

			// Login
			case "10":
				//login();
				break;
			
			// Logout
			case "00":
				//logout();
				break;

			default:

			}
		}
	}

	public static void withdrawal(int accNum, float amount) {
		// If standard, deduct fee
		System.out.println("Withdrawal transaction.");
	}

	public static void transfer(int accNumF, int accNumT, float amount, boolean admin) {
		// If standard, deduct fee
		System.out.println("Transfer transaction.");
	}

	public static void paybill(int accNum, float amount, boolean admin) {
		// If standard, deduct fee
		System.out.println("Paybill transaction.");
	}

	public static void deposit(int accNum, float amount, boolean admin) {
		// If standard, deduct fee
		System.out.println("Deposit transaction.");
	}

	public static void create(String newAccHolder, int newAccNum, float initBalance) {
		// Check newAccNum is unique
		// A and N are default for status and plan
		System.out.println("Logout transaction.");
	}

	public static void delete(int accNum) {
		System.out.println("Delete transaction.");
	}

	public static void disable(int accNum) {
		System.out.println("Disable transaction.");
	}

	public static void changeplan(int accNum, char plan) {
		System.out.println("Change Plan transaction.");
	}

	public static void enable(int accNum) {
		System.out.println("Enable transaction.");
	}

	public static void login(int accNum) {
		for (int i = 0; i < Main.userAccounts.size(); i++) {
			if (Main.userAccounts.get(i).getNum() == accNum) {
				Main.currUser = Main.userAccounts.get(i);
			}
		}

		System.out.println("Login transaction.");
	}

	public static void logout() {
		System.out.println("Logout transaction.");
	}
}
import java.io.*;
import java.util.*;

public class WatermelonHelper {

	public static String flag, accHolderT, accNumT, accBalanceT, misc;
	public static String accNumM, accHolderM, accStatus, accBalanceM, numTran, accPlan;

	public static void tokenizeTransaction(String line) {
		
		flag = line.substring(0, 2);
		accHolderT = line.substring(3, 23);
		accNumT = line.substring(24, 29);
		accBalanceT = line.substring(30, 38);
		misc = line.substring(40);

		/*
		System.out.println("Flag: " + flag);
		System.out.println("Account Holder: " + accHolderT);
		System.out.println("Account Number: " + accNumT);
		System.out.println("Balance: " + accBalanceT);
		System.out.println("Misc.: " + misc);
		*/
	}

	public static void tokenizeMaster(String line) {
		
		accNumM = line.substring(0, 5);
		accHolderM = line.substring(7, 26);
		accStatus = line.substring(27, 28);
		accBalanceM = line.substring(29, 37);
		numTran = line.substring(39, 43);
		accPlan = line.substring(44);

		/*
		System.out.println("Account Number: " + flag);
		System.out.println("Account Holder: " + accHolder);
		System.out.println("Status: " + accNum);
		System.out.println("Balance: " + accBalance);
		System.out.println("Misc.: " + misc);
		*/
	}

	public static bool isAdmin(String accHolder) {
		
	}
}
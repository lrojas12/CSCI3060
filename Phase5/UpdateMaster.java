/**
 * This class will contain methods related to the real changes that need to be
 * made to the "old" master accounts file in order to update it into the "new"
 * master accounts file.
 *
 * UpdateMaster.java
 * 
 * @author Luisa Rojas
 * @author Truyen Truong
 * @author Danesh Parthipan
 */

import java.io.*;
import java.util.*;

public class UpdateMaster {

  /**
   * Updates the master bank accounts file based on the merged transaction files
   * and the transaction's specific constraints.
   */
  public static void updateMaster(String oldMasterFileName) {

    // For each line in the merged transfer files list.
    for (int i = 0; i < Main.transactionFile.size(); i++) {
      // Tokenize it.
      Utilities.tokenizeTransaction(Main.transactionFile.get(i));

      // Chooses transaction based off flag.
      switch(Utilities.flag) {

      // Withdrawal
      case "01":
        withdrawal(Integer.parseInt(Utilities.accNumT),
                   Float.valueOf(Utilities.accBalanceT).floatValue(),
                   Utilities.isAdmin(Main.currUser.getPlan()));
        break;

      // Transfer
      case "02":
        
        int accNumF = Integer.parseInt(Utilities.accNumT);

        i++;
        Utilities.tokenizeTransaction(Main.transactionFile.get(i));
        
        transfer(accNumF, Integer.parseInt(Utilities.accNumT),
        	     Float.valueOf(Utilities.accBalanceT).floatValue(),
                 Utilities.isAdmin(Main.currUser.getPlan()));
        break;

      // Paybill
      case "03":
        paybill(Integer.parseInt(Utilities.accNumT),
                Float.valueOf(Utilities.accBalanceT).floatValue(),
                Utilities.isAdmin(Main.currUser.getPlan()));
        break;
			
      // Deposit
      case "04":
        deposit(Integer.parseInt(Utilities.accNumT),
                Float.valueOf(Utilities.accBalanceT).floatValue(),
                Utilities.isAdmin(Main.currUser.getPlan()));
        break;
			
      // Create
      case "05":
        create(Utilities.accHolderT,
        	   Integer.parseInt(Utilities.accNumT),
               Float.valueOf(Utilities.accBalanceT).floatValue());
        break;
			
      // Delete
      case "06":
        delete(Integer.parseInt(Utilities.accNumT));
        break;
			
      // Disable
      case "07":
        disable(Integer.parseInt(Utilities.accNumT));
        break;
			
      // Changle Plan
      case "08":
        changeplan(Integer.parseInt(Utilities.accNumT),
        	       Utilities.misc.charAt(0));
        break;

      // Enable
      case "09":
        enable(Integer.parseInt(Utilities.accNumT));
        break;

      // Login
      case "10":
      	if (Utilities.accNumT.trim().length() > 0) {
        	login(Integer.parseInt(Utilities.accNumT));
    	}
        break;
			
      // Logout
      case "00":
        logout();
        break;

      default:

      }
    }

    // Rewrites the master bank accounts file.
    Utilities.rewriteMasterFile(Main.oldMasterFileName);

    // Rewrites the current bank accounts file.
    Utilities.rewriteCurrentFile();
  }

  /**
   * Updates master bank account file using withdrawal transaction.
   *
   * @param accNum   account number being withdrawn from
   * @param amount   amount being withdrawn
   * @param admin    if current logged in is admin
   * @return         withdrawal if deposit succeeded, false if deposit failed
   */
  public static boolean withdrawal(int accNum, float amount, boolean admin) {
		
    int accIndex = Utilities.getAccIndex(accNum);
    float accBalance = Main.userAccounts.get(accIndex).getBalance();
    int accTranNum = Main.userAccounts.get(accIndex).getNumTran();

    // If current logged in user is admin.
    if (admin) {
      // Check if balance is valid.
      if (accBalance >= amount) {
        //  Change account balance with amount.
      	Main.userAccounts.get(accIndex).setBalance(accBalance-amount);
      	return true;
      }
    // If current logged in user is standard.
    } else {
      if (Main.currUser.getPlan() == 'S') {
        // Check if balance is valid with fee.
      	if (accBalance >= amount+0.05) {
          // Change account balance with amount and fee.
      	  Main.userAccounts.get(accIndex).setBalance(accBalance-amount-(float)0.05);
          // Increase number of transactions for that account.
          Main.userAccounts.get(accIndex).setNumTran(accTranNum+1);  

      	  return true;
        }
      } else if (Main.currUser.getPlan() == 'N') {
        // Check if balance is valid with fee.
      	if (Main.userAccounts.get(accIndex).getBalance() >= amount+0.10) {
          // Change account balance with amount and fee.
      	  Main.userAccounts.get(accIndex).setBalance(accBalance-amount-(float)0.10);
          // Increase number of transactions for that account.
          Main.userAccounts.get(accIndex).setNumTran(accTranNum+1);

      	 return true;
        }
      } else {
        // Error message for invalid payment plan in withdrawal
        System.err.println("ERROR <<withdrawal>>: Unable to get payment plan information.");
      }
    }

    // Returns false if transaction is not performed.
    return false;
  }

  /**
   * Updates master bank account file using transfer transaction.
   *
   * @param accNumF   account number being transfered from
   * @param accNumT   account number being transfered to
   * @param amount    amount being transfer
   * @param admin     if current logged in is admin
   */
  public static void transfer(int accNumF, int accNumT, float amount, boolean admin) {
    // Executes withdrawal and checks for success.
    if (withdrawal(accNumF, amount, admin)) {
      // Executes deposit and checks for success.
      if(!deposit(accNumT, amount, true)) {

        System.err.println("ERROR <<transfer>>: There was an issue depositing the funds into the destination account.");

        // Amount and fee added together for non-student.
      	if (Main.currUser.getPlan() == 'N') {
      		amount += 0.10;
        // Amount and fee added together for student.
      	} else if (Main.currUser.getPlan() == 'S') {
      		amount += 0.05;
      	}

        // Deposits back into sender account if transaction failed.
      	deposit(accNumF, amount, true);

        // If current logged in account is not admin, reverses the number of transactions for the sender account.
        if (!admin) {
          int accIndexF = Utilities.getAccIndex(accNumF);

          Main.userAccounts.get(accIndexF).setNumTran(
               Main.userAccounts.get(accIndexF).getNumTran()-1);
        }
      }
    } else {
      System.err.println("ERROR <<transfer>>: There was an issue withdrawing the funds from the origin account.");
    }
  }

  /**
   * Updates master bank account file using paybill transaction.
   *
   * @param accNum   account number paying the bill
   * @param amount   amount being payed
   * @param admin    if current logged in is admin
   */
  public static void paybill(int accNum, float amount, boolean admin) {
    if (!withdrawal(accNum, amount, admin)) {
      System.err.println("ERROR <<paybill>>: There was an issue withdrawing the funds from the origin account.");
    }
  }

  /**
   * Updates master bank account file using deposit transaction.
   *
   * @param accNum   account number depositing money
   * @param amount   amount being deposited
   * @param admin    if current logged in is admin
   * @return         true if deposit succeeded, false if deposit failed
   */
  public static boolean deposit(int accNum, float amount, boolean admin) {
		
    int accIndex = Utilities.getAccIndex(accNum);
    float accBalance = Main.userAccounts.get(accIndex).getBalance();
    int accTranNum = Main.userAccounts.get(accIndex).getNumTran();

    // If current logged in user is admin.
    if (admin) {
      // Check if amount is valid.
      if (accBalance + amount < 100000.00 && accBalance + amount >= 0.0) {
        // Change account balance with amount.
      	Main.userAccounts.get(accIndex).setBalance(accBalance+amount); 
      	return true;
      }

    // If current logged in user is standard.
    } else {
      if (Main.currUser.getPlan() == 'S') {
        // Check if amount is valid with fee.
      	if (accBalance + amount-(float)0.05 < 100000.00 &&
      		  accBalance + amount-(float)0.05 >= 0.0) {

          // Change account balance with amount and fee.
      	  Main.userAccounts.get(accIndex).setBalance(accBalance+amount-(float)0.05);
          // Increase number of transactions for that account.
      	  Main.userAccounts.get(accIndex).setNumTran(accTranNum+1);

      	  return true;
        }
      } else if (Main.currUser.getPlan() == 'N') {
        // Check if amount is valid with fee.
      	if (Main.userAccounts.get(accIndex).getBalance()+amount-(float)0.10 < 100000.00 &&
      		  Main.userAccounts.get(accIndex).getBalance()+amount-(float)0.10 >= 0.0) {

          // Change amount balance with amount and fee.
      	  Main.userAccounts.get(accIndex).setBalance(accBalance+amount-(float)0.10); 
          // Increase number of transactions for that account.
          Main.userAccounts.get(accIndex).setNumTran(accTranNum+1); 

      	  return true;  
        }
      } else {
        // Error message for invalid payment plan.
        System.err.println("ERROR <<deposit>>: Unable to get payment plan information.");
      }
    }
    // Returns false if transaction is not performed.
    return false;
  }

  /**
   * Updates master bank account file using create transaction.
   *
   * @param newAccHolder   new account holder name
   * @param newAccNum      new account number
   * @param initBalance    initial balance
   */
  public static void create(String newAccHolder, int newAccNum, float initBalance) {

    // Check if name is unique, account number is unique and initial balance is valid.
    if (Utilities.isNameUnique(newAccHolder) && Utilities.isNumberUnique(newAccNum) &&
    	  initBalance >= 0 && initBalance < 100000.00) {

      // Create new account with initial values.
      User newUser = new User(newAccHolder, newAccNum, initBalance, 'A', 0, 'N');
      // Add new account to account list.
      Main.userAccounts.add(Main.userAccounts.size()-1, newUser);
    } else {
      System.err.println("ERROR <<create>>: The constraints inputted (name, account number, balance) are incorrect.");
    }
  }

  /**
   * Updates master bank account file using delete transaction.
   *
   * @param accNum   account number being deleted
   */
  public static void delete(int accNum) {

    int accIndex = Utilities.getAccIndex(accNum);

    if (accIndex == -1) {
      System.err.println("ERROR <<delete>>: The account to be deleted does not exist.");
      return;
    }

    // Delete account.
    Main.userAccounts.remove(accIndex);
  }

  /**
   * Updates master bank account file using disable transaction.
   *
   * @param accNum   account number being disabled
   */
  public static void disable(int accNum) {

    int accIndex = Utilities.getAccIndex(accNum);

    if (accIndex == -1) {
      System.err.println("ERROR <<disable>>: The account to be disabled does not exist.");
      return;
    }

    // Disable account if account is active.
    if (Main.userAccounts.get(accIndex).getStatus() == 'A') {
      Main.userAccounts.get(accIndex).setStatus('D');
    } else {
      System.err.println("ERROR <<disable>>: The account to be disabled already is.");
    }
  }

  /**
   * Updates master bank account file using changeplan transaction.
   *
   * @param accNum   account number changing transaction payment plan
   * @param plan     plan being changed to
   */
  public static void changeplan(int accNum, char plan) {

    int accIndex = Utilities.getAccIndex(accNum);

    // Check that the account exists
    if (accIndex == -1) { // If the getAccIndex returns zero, then the account does not exist.
      System.err.println("ERROR <<changeplan>>: The account changing plans does not exist.");
      return;
    }

    // Check that the plan inputted in the function is not the same as the current plan.
    if (Main.userAccounts.get(accIndex).getPlan() == plan) {
      System.err.println("ERROR <<changeplan>>: The plan inputted is the current plan.");
      return;
    }

    // Check that the plan inputted in the function is either N or S.
    if (plan != 'N' && plan != 'S') {
      System.err.println("ERROR <<changeplan>>: The plan inputted is not valid.");
      return;
    } 
    
    // Change payment plan to given payment plan.
    Main.userAccounts.get(accIndex).setPlan(plan);
  }

  /**
   * Updates master bank account file using enable transaction.
   *
   * @param accNum   account number being enabled
   */
  public static void enable(int accNum) {

    int accIndex = Utilities.getAccIndex(accNum);

      if (accIndex == -1) {
        System.err.println("ERROR <<enable>>: The account to be enabled in does not exist.");
        return;
      }

    // Enable account if account is disabled.
    if (Main.userAccounts.get(accIndex).getStatus() == 'D') {
      Main.userAccounts.get(accIndex).setStatus('A');
    } else {
      System.err.println("ERROR <<enable>>: The account to be enabled already is.");
    }
  }

  /**
   * Updates master bank account file using login transaction.
   *
   * @param accNum   account being logged in to
   */
  public static void login(int accNum) {

    if (Utilities.misc.equals("S ")) {

      int accIndex = Utilities.getAccIndex(accNum);

      if (accIndex == -1) {
        System.err.println("ERROR <<login>>: The account to be logged in does not exist.");
        return;
      }

      Main.currUser = Main.userAccounts.get(accIndex);

    } else if (Utilities.misc.equals("A ")) {
      Main.currUser = new User('A');
    } else {
      // The mode should be either S or A
      System.err.println("ERROR <<login>>: The mode to be logged in as is not valid.");
    }
  }

  /**
   * Updates master bank account file using logout transaction.
   */
  public static void logout() {
    Main.currUser = new User();
  }
}
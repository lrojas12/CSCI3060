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
    for (int i = 0; i < Main.transferFile.size(); i++) {
      // Tokenize it.
      Utilities.tokenizeTransaction(Main.transferFile.get(i));

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
        Utilities.tokenizeTransaction(Main.transferFile.get(i));
        
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

    Utilities.rewriteMasterFile(Main.oldMasterFileName);
    Utilities.rewriteCurrentFile();
  }

  /**
   * Updates master bank account file using withdrawal transaction.
   *
   * @param accNum   account number being withdrawn from
   * @param amount   amount being withdrawn
   * @param admin    if current logged in is admin
   */
  public static boolean withdrawal(int accNum, float amount, boolean admin) {
		
    int accIndex = Utilities.getAccIndex(accNum);

    if (admin) {
      	if (Main.userAccounts.get(accIndex).getBalance() >= amount) {

      	  Main.userAccounts.get(accIndex).setBalance(Main.userAccounts.get(accIndex).getBalance()-amount);
      	  return true;
      }
    } else {
      if (Main.currUser.getPlan() == 'S') {
      	if (Main.userAccounts.get(accIndex).getBalance() >= amount+0.05) {

      	  Main.userAccounts.get(accIndex).setBalance(Main.userAccounts.get(accIndex).getBalance()-amount-(float)0.05);
          Main.userAccounts.get(accIndex).setNumTran(Main.userAccounts.get(accIndex).getNumTran()+1);  

      	  return true;
        }
      } else if (Main.currUser.getPlan() == 'N') {
      	if (Main.userAccounts.get(accIndex).getBalance() >= amount+0.10) {

      	  Main.userAccounts.get(accIndex).setBalance(Main.userAccounts.get(accIndex).getBalance()-amount-(float)0.10);
          Main.userAccounts.get(accIndex).setNumTran(Main.userAccounts.get(accIndex).getNumTran()+1);

      	 return true;
        }
      } else {
        System.err.println("ERROR: Unable to get payment plan information.");
      }
    }

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

    if (withdrawal(accNumF, amount, admin)) {
      if(!deposit(accNumT, amount, true)) {
      	if (Main.currUser.getPlan() == 'N') {
      		amount += 0.10;
      	} else if (Main.currUser.getPlan() == 'S') {
      		amount += 0.05;
      	}
      	
      	deposit(accNumF, amount, true);
        if (!admin) {

          Main.userAccounts.get(Utilities.getAccIndex(accNumF)).setNumTran(
               Main.userAccounts.get(Utilities.getAccIndex(accNumF)).getNumTran()-1);
        }
      }
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
    withdrawal(accNum, amount, admin);
  }

  /**
   * Updates master bank account file using deposit transaction.
   *
   * @param accNum   account number depositing money
   * @param amount   amount being deposited
   * @param admin    if current logged in is admin
   */
  public static boolean deposit(int accNum, float amount, boolean admin) {
		
    int accIndex = Utilities.getAccIndex(accNum);

    if (admin) {
      if (Main.userAccounts.get(accIndex).getBalance() + amount < 100000.00 &&
      	  Main.userAccounts.get(accIndex).getBalance() + amount >= 0.0) {

      	Main.userAccounts.get(accIndex).setBalance(Main.userAccounts.get(accIndex).getBalance()+amount); 
      	return true;
      }
    } else {
      if (Main.currUser.getPlan() == 'S') {
      	if (Main.userAccounts.get(accIndex).getBalance() + amount-(float)0.05 < 100000.00 &&
      		  Main.userAccounts.get(accIndex).getBalance() + amount-(float)0.05 >= 0.0) {

      	  Main.userAccounts.get(accIndex).setBalance(Main.userAccounts.get(accIndex).getBalance()+amount-(float)0.05);  
      	  Main.userAccounts.get(accIndex).setNumTran(Main.userAccounts.get(accIndex).getNumTran()+1);
      	  return true;
        }
      } else if (Main.currUser.getPlan() == 'N') {
      	if (Main.userAccounts.get(accIndex).getBalance() + amount-(float)0.10 < 100000.00 &&
      		  Main.userAccounts.get(accIndex).getBalance() + amount-(float)0.10 >= 0.0) {

      	  Main.userAccounts.get(accIndex).setBalance(Main.userAccounts.get(accIndex).getBalance()+amount-(float)0.10); 
          Main.userAccounts.get(accIndex).setNumTran(Main.userAccounts.get(accIndex).getNumTran()+1); 
      	  return true;  
        }
      } else {
        System.err.println("ERROR: Unable to get payment plan information.");
      }
    }

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
    // Check newAccNum is unique
    // A and N are default for status and plan

    if (Utilities.isNameUnique(newAccHolder) && Utilities.isNumberUnique(newAccNum) &&
    	  initBalance >= 0 && initBalance < 100000.00) {

      User newUser = new User(newAccHolder, newAccNum, initBalance, 'A', 0, 'N');
      Main.userAccounts.add(Main.userAccounts.size()-1, newUser);
    }
  }

  /**
   * Updates master bank account file using delete transaction.
   *
   * @param accNum   account number being deleted
   */
  public static void delete(int accNum) {

    int accIndex = Utilities.getAccIndex(accNum);

    Main.userAccounts.remove(accIndex);
  }

  /**
   * Updates master bank account file using disable transaction.
   *
   * @param accNum   account number being disabled
   */
  public static void disable(int accNum) {

    int accIndex = Utilities.getAccIndex(accNum);

    if (Main.userAccounts.get(accIndex).getStatus() == 'A') {
      Main.userAccounts.get(accIndex).setStatus('D');
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

    if (Main.userAccounts.get(accIndex).getPlan() != plan &&
       (plan == 'N' || plan == 'S')) {
      Main.userAccounts.get(accIndex).setPlan(plan);
    }
  }

  /**
   * Updates master bank account file using enable transaction.
   *
   * @param accNum   account number being enabled
   */
  public static void enable(int accNum) {

    int accIndex = Utilities.getAccIndex(accNum);

    if (Main.userAccounts.get(accIndex).getStatus() == 'D') {
      Main.userAccounts.get(accIndex).setStatus('A');
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

      Main.currUser = Main.userAccounts.get(accIndex);

    } else if (Utilities.misc.equals("A ")) {
      Main.currUser = new User();
    } else {
      System.err.println("ERROR: Problem with login information.");
    }
  }

  /**
   * Updates master bank account file using logout transaction.
   */
  public static void logout() {
    Main.currUser = new User();
  }
}
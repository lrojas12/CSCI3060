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
  public static void updateMaster() {

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
        //transfer();
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
        //changePlan();
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
  }

  /**
   * Updates master bank account file using withdrawal transaction.
   *
   * @param accNum   account number being withdrawn from
   * @param amount   amount being withdrawn
   * @param admin    if current logged in is admin
   */
  public static void withdrawal(int accNum, float amount, boolean admin) {
		
    System.out.println("Withdrawal transaction.");
		
    if (admin == true) {
      for (int i=0; i<Main.userAccounts.size(); i++) {
      	if (Main.userAccounts.get(i).getNum() == accNum &&
      		Main.userAccounts.get(i).getBalance() >= amount) {
      	  Main.userAccounts.get(i).setBalance(Main.userAccounts.get(i).getBalance()-amount);  
      	  return;
      	}
      }
    } else {
      if (Main.currUser.getPlan() == 'S') {
        for (int i=0; i<Main.userAccounts.size(); i++) {
      	  if (Main.userAccounts.get(i).getNum() == accNum &&
      	  	  Main.userAccounts.get(i).getBalance() >= amount+0.05) {
      	    Main.userAccounts.get(i).setBalance(Main.userAccounts.get(i).getBalance()-amount-(float)0.05);  
      	    return;
      	  }
        }
      } else if (Main.currUser.getPlan() == 'N') {
        for (int i=0; i<Main.userAccounts.size(); i++) {
      	  if (Main.userAccounts.get(i).getNum() == accNum &&
      	  	  Main.userAccounts.get(i).getBalance() >= amount+0.10) {
      	    Main.userAccounts.get(i).setBalance(Main.userAccounts.get(i).getBalance()-amount-(float)0.10);
      	    return;
      	  }
        }
      } else {
        // Error
      }
    }
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
  	System.out.println("Transfer transaction.");
    // If standard, deduct fee
    withdrawal(accNumF, amount, admin);
    deposit(accNumT, amount, true); // No fees charged
  }

  /**
   * Updates master bank account file using paybill transaction.
   *
   * @param accNum   account number paying the bill
   * @param amount   amount being payed
   * @param admin    if current logged in is admin
   */
  public static void paybill(int accNum, float amount, boolean admin) {
  	System.out.println("Paybill transaction.");
    withdrawal(accNum, amount, admin);
  }

  /**
   * Updates master bank account file using deposit transaction.
   *
   * @param accNum   account number depositing money
   * @param amount   amount being deposited
   * @param admin    if current logged in is admin
   */
  public static void deposit(int accNum, float amount, boolean admin) {
		
    System.out.println("Deposit transaction.");

    if (admin == true) {
      for (int i=0; i<Main.userAccounts.size(); i++) {
      	if (Main.userAccounts.get(i).getNum() == accNum && 
      		Main.userAccounts.get(i).getBalance() + amount < 100000.00 &&
      		Main.userAccounts.get(i).getBalance() + amount >= 0.0) {
      	  Main.userAccounts.get(i).setBalance(Main.userAccounts.get(i).getBalance()+amount); 
      	  return;
      	}
      }
    } else {
      if (Main.currUser.getPlan() == 'S') {
        for (int i=0; i<Main.userAccounts.size(); i++) {
      	  if (Main.userAccounts.get(i).getNum() == accNum && 
      		  Main.userAccounts.get(i).getBalance() + amount-(float)0.05 < 100000.00 &&
      		  Main.userAccounts.get(i).getBalance() + amount-(float)0.05 >= 0.0) {
      	    Main.userAccounts.get(i).setBalance(Main.userAccounts.get(i).getBalance()+amount-(float)0.05);  
      	    Main.userAccounts.get(i).setNumTran(Main.userAccounts.get(i).getNumTran()+1);
      	    return;
      	  }
        }
      } else if (Main.currUser.getPlan() == 'N') {
        for (int i=0; i<Main.userAccounts.size(); i++) {
      	  if (Main.userAccounts.get(i).getNum() == accNum && 
      		  Main.userAccounts.get(i).getBalance() + amount-(float)0.10 < 100000.00 &&
      		  Main.userAccounts.get(i).getBalance() + amount-(float)0.10 >= 0.0) {
      	    Main.userAccounts.get(i).setBalance(Main.userAccounts.get(i).getBalance()+amount-(float)0.10); 
            Main.userAccounts.get(i).setNumTran(Main.userAccounts.get(i).getNumTran()+1); 
      	    return;  
      	  }
        }
      } else {
        System.err.println("ERROR: Problem reading in current user plan.");
      }
    }
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
    System.out.println("Create transaction.");

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
    System.out.println("Delete transaction.");

    for (int i=0; i<Main.userAccounts.size(); i++) {
      if (Main.userAccounts.get(i).getNum() == accNum) {
        Main.userAccounts.remove(i);
        return;
      }
    }
  }

  /**
   * Updates master bank account file using disable transaction.
   *
   * @param accNum   account number being disabled
   */
  public static void disable(int accNum) {
    System.out.println("Disable transaction.");

    for (int i=0; i<Main.userAccounts.size(); i++) {
      if (Main.userAccounts.get(i).getNum() == accNum &&
      	Main.userAccounts.get(i).getStatus() == 'A') {
        Main.userAccounts.get(i).setStatus('D');
        return;
      }
    }
  }

  /**
   * Updates master bank account file using changeplan transaction.
   *
   * @param accNum   account number changing transaction payment plan
   * @param plan     plan being changed to
   */
  public static void changeplan(int accNum, char plan) {
    System.out.println("Change Plan transaction.");

    for (int i=0; i<Main.userAccounts.size(); i++) {
      if (Main.userAccounts.get(i).getNum() == accNum &&
      	  Main.userAccounts.get(i).getPlan() != plan) {
        Main.userAccounts.get(i).setPlan(plan);
        return;
      }
    }
  }

  /**
   * Updates master bank account file using enable transaction.
   *
   * @param accNum   account number being enabled
   */
  public static void enable(int accNum) {
    System.out.println("Enable transaction.");

    for (int i=0; i<Main.userAccounts.size(); i++) {
      if (Main.userAccounts.get(i).getNum() == accNum &&
      	Main.userAccounts.get(i).getStatus() == 'D') {
        Main.userAccounts.get(i).setStatus('A');
        return;
      }
    }
  }

  /**
   * Updates master bank account file using login transaction.
   *
   * @param accNum   account being logged in to
   */
  public static void login(int accNum) {

    if (Utilities.misc.equals("S ")) {
      for (int i = 0; i < Main.userAccounts.size(); i++) {
        if (Main.userAccounts.get(i).getNum() == accNum) {
          Main.currUser = Main.userAccounts.get(i);
        }
      }
    } else if (Utilities.misc.equals("A ")) {
      Main.currUser = new User();
    } else {
      System.err.println("ERROR: Problem with login information.");
    }
		
    // Update actual User value with currUser copy

    System.out.println("Login transaction.");
  }

  /**
   * Updates master bank account file using logout transaction.
   */
  public static void logout() {
    Main.currUser = new User();
    System.out.println("Logout transaction.");
  }
}
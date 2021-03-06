import java.io.*;
import java.util.*;

/**
 * @author Luisa Rojas
 * @author Truyen Truong
 * @author Danesh Parthipan
 */
public class UpdateMaster {

  /**
   * Updates the master bank accounts file based on the merged transaction files
   * and the transaction's specific constraints.
   */
  public static void updateMaster() {

    // For each line in the merged transfer files list.
    for (int i = 0; i < Main.mergedTransferFiles.size(); i++) {
      // Tokenize it.
      WatermelonHelper.tokenizeTransaction(Main.mergedTransferFiles.get(i));

      // Chooses transaction based off flag.
      switch(WatermelonHelper.flag) {

      // Withdrawal
      case "01":
        withdrawal(Integer.parseInt(WatermelonHelper.accNumT),
                   Float.valueOf(WatermelonHelper.accBalanceT).floatValue(),
                   WatermelonHelper.isAdmin(Main.currUser.getPlan()));
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
        login(Integer.parseInt(WatermelonHelper.accNumT));
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
      // Deduct amount from balance
    } else {
      if (Main.currUser.getPlan() == 'S') {
        // Deduct amount and fee from balance
      } else if (Main.currUser.getPlan() == 'N') {
        // Deduct amount and fee from balance
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
    // If standard, deduct fee
    withdrawal(accNumF, amount, WatermelonHelper.isAdmin(Main.currUser.getPlan()));
    deposit(accNumT, amount, true); // No fees charged
    System.out.println("Transfer transaction.");
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
    System.out.println("Paybill transaction.");
  }

  /**
   * Updates master bank account file using deposit transaction.
   *
   * @param accNum   account number depositing money
   * @param amount   amount being deposited
   * @param admin    if current logged in is admin
   */
  public static void deposit(int accNum, float amount, boolean admin) {
		
    if (admin == true) {
      // Add amount to balance
    } else {
      if (Main.currUser.getPlan() == 'S') {
        // Add amount to balance and deduct fee
      } else if (Main.currUser.getPlan() == 'N') {
        // Add amount to balance and deduct fee
      } else {
        // Error
      }
    }

    System.out.println("Deposit transaction.");
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
    System.out.println("Logout transaction.");
  }

  /**
   * Updates master bank account file using delete transaction.
   *
   * @param accNum   account number being deleted
   */
  public static void delete(int accNum) {
    System.out.println("Delete transaction.");
  }

  /**
   * Updates master bank account file using disable transaction.
   *
   * @param accNum   account number being disabled
   */
  public static void disable(int accNum) {
    System.out.println("Disable transaction.");
  }

  /**
   * Updates master bank account file using changeplan transaction.
   *
   * @param accNum   account number changing transaction payment plan
   * @param plan     plan being changed to
   */
  public static void changeplan(int accNum, char plan) {
    System.out.println("Change Plan transaction.");
  }

  /**
   * Updates master bank account file using enable transaction.
   *
   * @param accNum   account number being enabled
   */
  public static void enable(int accNum) {
    System.out.println("Enable transaction.");
  }

  /**
   * Updates master bank account file using login transaction.
   *
   * @param accNum   account being logged in to
   */
  public static void login(int accNum) {

    if (WatermelonHelper.misc.equals("S ")) {
      for (int i = 0; i < Main.userAccounts.size(); i++) {
        if (Main.userAccounts.get(i).getNum() == accNum) {
          Main.currUser = Main.userAccounts.get(i);
        }
      }
    } else if (WatermelonHelper.misc.equals("A ")) {
      // ?????????
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
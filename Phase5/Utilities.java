/**
 * This class will contain every method needed to aid the rest of the classes
 * involved in the system.
 *
 * Utilities.java
 * 
 * @author Luisa Rojas
 * @author Truyen Truong
 * @author Danesh Parthipan
 */

import java.io.*;
import java.util.*;

public class Utilities {

  public static String flag, accHolderT, accNumT, accBalanceT, misc;
  public static String accNumM, accHolderM, accStatus, accBalanceM, numTran, accPlan;

  /**
   * Reads in the old master bank accounts file and stores it.
   *
   * @param fileName    file from which the old master bank accounts file is being read from
   * @param arrayList   list to which the old master bank accounts file is being stored in
   */
  public static void storeFile(String fileName, List<String> arrayList) {

    // Creates a buffered reader for the file based on file name given.
    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      String line;
      // Reads in the file line by line
      while ((line = br.readLine()) != null) {
        // Stores the line based on list to store in.
        arrayList.add(line);
      }
    } catch (IOException e) {
      System.err.println("ERROR: Could not read in file.");
    }
  }

  /**
   * Tokenizes a line in a bank transaction file.
   *
   * @param line   transaction line being tokenized
   */
  public static void tokenizeTransaction(String line) {
		
    flag = line.substring(0, 2);
    accHolderT = line.substring(3, 23).trim();
    accNumT = line.substring(24, 29);
    accBalanceT = line.substring(30, 38);
    misc = line.substring(39);
  }

  /**
   * Tokenizes a line in a master bank accounts file.
   *
   * @param line   master bank accounts file line being tokenized
   */
  public static void tokenizeMaster(String line) {
		
    accNumM = line.substring(0, 5);
    accHolderM = line.substring(6, 26).trim();
    accStatus = line.substring(27, 28);
    accBalanceM = line.substring(29, 37);
    numTran = line.substring(38, 42);
    accPlan = line.substring(43);
  }

  /**
   * Checks if the current account plan is an admin or is standard.
   *
   * @param accPlan   account plan being checked
   * @return          true if admin, otherwise false
   */
  public static boolean isAdmin(char accPlan) {
    if (accPlan == 'N' || accPlan == 'S') {
      return false;
    } else if (accPlan == ' ') {
      return true;
    } else {
      System.err.println("ERROR: Unable to retrieve account mode.");
      return false;
      // Skip to the next line
    }
  }

  /**
   * Tokenizes, and stores the master bank accounts file lines.
   *
   * @param masterList   list containing the not tokenized master bank account file lines
   */
  public static void getAllMasterAccounts(List<String> masterList) {
    for (int i = 0; i < masterList.size(); i++) {
      String line = masterList.get(i);

      // Tokenizes the master bank accounts file line.
      tokenizeMaster(line);

      // Stores the tokenized bank account.
      User user = new User(accHolderM.trim(), Integer.parseInt(accNumM),
                           Float.valueOf(accBalanceM).floatValue(), accStatus.charAt(0),
                           Integer.parseInt(numTran), accPlan.charAt(0));
      Main.userAccounts.add(user);
    }
  }

  /**
   * Rewrites the master bank accounts file.
   */
  public static void rewriteMasterFile(String fileName) {
    
    try {

      BufferedWriter bw = new BufferedWriter(new FileWriter(new File(fileName)));

      for(int i = 0; i < Main.userAccounts.size(); i++) {

        String paddedAccNum;
        String paddedAccHolder;
        String paddedBalance;
        String paddedNumTransac;

        paddedAccNum = Integer.toString(Main.userAccounts.get(i).getNum());
        while (paddedAccNum.length() < 5) {
          paddedAccNum = "0" + paddedAccNum;
        }

        paddedAccHolder = Main.userAccounts.get(i).getName();
        while (paddedAccHolder.length() < 20) {
          paddedAccHolder = paddedAccHolder + " ";
        }

        paddedBalance = String.format("%.2f", Main.userAccounts.get(i).getBalance()); 
        while (paddedBalance.length() < 8) {
          paddedBalance = "0" + paddedBalance;
        }
        
        
        paddedNumTransac = Integer.toString(Main.userAccounts.get(i).getNumTran());
        while (paddedNumTransac.length() < 4) {
          paddedNumTransac = "0" + paddedNumTransac;
        }

        String line = paddedAccNum + " " + paddedAccHolder + " " + 
                      Main.userAccounts.get(i).getStatus() + " " + paddedBalance +
                      " " + paddedNumTransac + " " + Main.userAccounts.get(i).getPlan() + "\n";

        bw.write(line);
      }
      bw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Rewrites the current bank accounts file.
   */
  public static void rewriteCurrentFile() {

    String fileName = "current_bank_accounts_file.txt";

    try {

      BufferedWriter bw = new BufferedWriter(new FileWriter(new File(fileName)));

      for(int i = 0; i < Main.userAccounts.size(); i++) {

        String paddedAccNum;
        String paddedAccHolder;
        String paddedBalance;

        paddedAccNum = Integer.toString(Main.userAccounts.get(i).getNum());
        while (paddedAccNum.length() < 5) {
          paddedAccNum = "0" + paddedAccNum;
        }

        paddedAccHolder = Main.userAccounts.get(i).getName();
        while (paddedAccHolder.length() < 20) {
          paddedAccHolder = paddedAccHolder + " ";
        }

        paddedBalance = String.format("%.2f", Main.userAccounts.get(i).getBalance()); 
        while (paddedBalance.length() < 8) {
          paddedBalance = "0" + paddedBalance;
        }

        String line = paddedAccNum + " " + paddedAccHolder + " " + 
                      Main.userAccounts.get(i).getStatus() + " " + paddedBalance + "\n";

        bw.write(line);
      }
      bw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Checks if an account holder name is unique.
   *
   * @param name   account number being checked for uniqueness
   * @return       true if unique, otherwise false
   */
  public static boolean isNameUnique(String name) {
    
    boolean isUnique = true;

    for (int i=0; i<Main.userAccounts.size(); i++) {
      if (Main.userAccounts.get(i).getName().equals(name)) {
        isUnique = false;
      }
    }

    return isUnique;
  }

  /**
   * Checks if an account number is unique.
   *
   * @param accNum   account number being checked for uniqueness
   * @return         true if unique, otherwise false
   */
  public static boolean isNumberUnique(int accNum) {
    
    boolean isUnique = true;

    for (int i=0; i<Main.userAccounts.size(); i++) {
      if (Main.userAccounts.get(i).getNum() == accNum) {
        isUnique = false;
      }
    }

    return isUnique;
  }

  public static int getAccIndex(int accNum) {
    for (int i=0; i<Main.userAccounts.size(); i++) {
      if (Main.userAccounts.get(i).getNum() == accNum) {
        return i;
      }
    }

    return 0;
  }
} 
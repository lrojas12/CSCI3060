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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
      System.err.println("ERROR <internal>: Could not read in file.");
    }
  }

  /**
   * Tokenizes a line in a bank transaction file.
   *
   * @param line   transaction line being tokenized
   */
  public static void tokenizeTransaction(String line) {

    String pattern = "[\\d]{2} [\\w\\p{Punct} ]{20} [\\d ]{5} [\\d ]{5}[\\. ][\\d ]{2} [A-Z ]{2}";
    
    Pattern r = Pattern.compile(pattern);

    Matcher m = r.matcher(line);
    
    if (!m.matches()) {
      System.err.println("FATAL ERROR: The format in the transaction file " + Main.transactionFileName + " is incorrect.");
      System.exit(-1);
    }

    flag = line.substring(0, 2);
    accHolderT = line.substring(3, 23).trim();
    accNumT = line.substring(24, 29);
    accBalanceT = line.substring(30, 38);
    misc = line.substring(39);
  }

  /**
   * Checks the master bank accounts file format and tokenizes its contents.
   *
   * @param line   master bank accounts file line being tokenized
   */
  public static void tokenizeMaster(String line) {
    
    String pattern = "[\\d]{5} [\\w\\p{Punct} ]{20} [AD] [\\d ]{5}[\\. ][\\d ]{2} [\\d]{4} [SN]";

    Pattern r = Pattern.compile(pattern);

    Matcher m = r.matcher(line);
    
    if (!m.matches()) {
      System.err.println("FATAL ERROR: The format in the master accounts file " + Main.oldMasterFileName + " is incorrect.");
      System.exit(-1);
    }

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
    } else if (accPlan == 'A') {
      return true;
    } else {
      System.err.println("ERROR <internal>: Unable to retrieve account mode.");
      return false;
    }
  }


  //// --------------- FIX THIISSS!!!!------------------
  /**
   * Tokenizes, and stores the master bank accounts file lines into an List of type User.
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
   *
   *@param fileName   string containing master bank accounts file name
   */
  public static void rewriteMasterFile(String fileName) {
    
    try {

      // Buffered writer to write to the master bank accounts file.
      BufferedWriter bw = new BufferedWriter(new FileWriter(new File(fileName)));

      // Runs for all accounts.
      for(int i = 0; i < Main.userAccounts.size(); i++) {

        String paddedAccNum;
        String paddedAccHolder;
        String paddedBalance;
        String paddedNumTransac;

        // Converts account number to string and pads with 0s at the beginning.
        paddedAccNum = Integer.toString(Main.userAccounts.get(i).getNum());
        while (paddedAccNum.length() < 5) {
          paddedAccNum = "0" + paddedAccNum;
        }

        // Pads account holder name with spaces at the end.
        paddedAccHolder = Main.userAccounts.get(i).getName();
        while (paddedAccHolder.length() < 20) {
          paddedAccHolder = paddedAccHolder + " ";
        }

        // Sets balance precision to 2, converts to a string and pads with 0s at the beginning.
        paddedBalance = String.format("%.2f", Main.userAccounts.get(i).getBalance()); 
        while (paddedBalance.length() < 8) {
          paddedBalance = "0" + paddedBalance;
        }
        
        // Converts number of transactions to a string and pads with 0s at the beginning.
        paddedNumTransac = Integer.toString(Main.userAccounts.get(i).getNumTran());
        while (paddedNumTransac.length() < 4) {
          paddedNumTransac = "0" + paddedNumTransac;
        }

        // Line to print master bank accounts file.
        String line = paddedAccNum + " " + paddedAccHolder + " " + 
                      Main.userAccounts.get(i).getStatus() + " " + paddedBalance +
                      " " + paddedNumTransac + " " + Main.userAccounts.get(i).getPlan() + "\n";

        // Write line to master bank accounts file.
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

    // Current bank accounts file name.
    String fileName = "current_bank_accounts_file.txt";

    try {

      // Buffered writer to write to the current bank accounts file.
      BufferedWriter bw = new BufferedWriter(new FileWriter(new File(fileName)));

      // Runs for all accounts.
      for(int i = 0; i < Main.userAccounts.size(); i++) {

        String paddedAccNum;
        String paddedAccHolder;
        String paddedBalance;

        // Converts account number to string and pads with 0s at the beginning.
        paddedAccNum = Integer.toString(Main.userAccounts.get(i).getNum());
        while (paddedAccNum.length() < 5) {
          paddedAccNum = "0" + paddedAccNum;
        }

        // Pads account holder name with spaces at the end.
        paddedAccHolder = Main.userAccounts.get(i).getName();
        while (paddedAccHolder.length() < 20) {
          paddedAccHolder = paddedAccHolder + " ";
        }

        // Sets balance precision to 2, converts to a string and pads with 0s at the beginning.
        paddedBalance = String.format("%.2f", Main.userAccounts.get(i).getBalance()); 
        while (paddedBalance.length() < 8) {
          paddedBalance = "0" + paddedBalance;
        }

        // Line to print master bank accounts file.
        String line = paddedAccNum + " " + paddedAccHolder + " " + 
                      Main.userAccounts.get(i).getStatus() + " " + paddedBalance + "\n";

        // Write line to current bank accounts file.
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

  /**
   * Gets the index that the account is at
   *
   * @param accNum   account number to get index for
   * @return         index the account is at
   */
  public static int getAccIndex(int accNum) {
    for (int i=0; i<Main.userAccounts.size(); i++) {
      if (Main.userAccounts.get(i).getNum() == accNum) {
        return i;
      }
    }

    return -1;
  }
} 
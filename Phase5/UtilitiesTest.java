/**
 * This class will test every method in the Utitilies.java class. The kinds of test
 * to be carried out will be statement, decision and loop coverage.
 * 
 * UtilitiesTest.java
 * 
 * @author Luisa Rojas
 * @author Truyen Truong
 * @author Danesh Parthipan
 */

import static org.junit.Assert.*;
import junit.framework.JUnit4TestAdapter;
import org.junit.Rule;
import org.junit.Test;
import java.io.*;
import java.util.*;
// import org.junit.contrib.java.lang.system.ExpectedSystemExit;

public class UtilitiesTest {
    
  //@Rule
  //public final ExpectedSystemExit exit = ExpectedSystemExit.none();

  /**
   * Executes storeFile with a unexistant file. Should error through the catch.
   */
  @Test
  public void storeFileTest1() {

    ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    System.setErr(new PrintStream(errContent));
    List<String> transactionFile = new ArrayList<String>();

    Utilities.storeFile("test.tra", transactionFile);
    String expectedOutput = "ERROR <internal>: Could not read in file.\n";

    assertEquals(expectedOutput, errContent.toString());
  }
  
  /**
   * Checks if storeFile actually reads in from the file. If it does, it can
   * be assumed that the lines previous to it were executed.
   */
  @Test
  public void storeFileTest2() {

    Main.transactionFile = new ArrayList<String>();

    Utilities.storeFile("transaction_file_storeFileTest2.tra", Main.transactionFile);

    assertEquals("10 Tarzan               00001          S ", Main.transactionFile.get(0));
  }
    
  /**
   * Tests for when a line is tokenized successfully. Checks if the misc
   * variable has the correct value in it. If it's correct, it can be
   * assumed that the lines previous to it were executed.
   */
  @Test
  public void tokenizeTransactionTest1() {
    String line = "10 Tarzan               00001          S ";
    
    Utilities.tokenizeTransaction(line);

    assertEquals("S ", Utilities.misc);
  }

  /**
   * Tests for when the format is incorrect and the proper error message
   * was printed.
   */
  /*@Test
  public void tokenizeTransactionTest2() {

    ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    System.setErr(new PrintStream(errContent));
    String line = "10 Tarzan               0001a          S ";
    String expectedOutput = "FATAL ERROR: The format in the transaction file transaction_file.tra is incorrect.\n";

    exit.expectSystemExitWithStatus(-1);
    Utilities.tokenizeTransaction(line);

    assertEquals(expectedOutput, errContent.toString());
  }*/

  /**
   * Tests for when a line is tokenized successfully. Checks if the accPlan
   * variable has the correct value in it. If it's correct, it can be
   * assumed that the lines previous to it were executed.
   */
  @Test
  public void tokenizeMasterTest1() {
    String line = "00001 Tarzan               A 01000.00 0000 N";

    Utilities.tokenizeMaster(line);

    assertEquals("N", Utilities.accPlan);
  }

  /**
   * Tests for when the format is incorrect and the proper error message
   * was printed.
   */
  /*@Test
  public void tokenizeMasterTest2() {

    ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    System.setErr(new PrintStream(errContent));
    String line = "00001 Tarzan               A 01000.00 0000 g";
    String expectedOutput = "FATAL ERROR: The format in the master_bank_accounts_file.txt is incorrect.\n";

    exit.expectSystemExitWithStatus(-1);
    Utilities.tokenizeTransaction(line);

    assertEquals(expectedOutput, errContent.toString());
  }*/

  /**
   * Tests when a standard user is logged in, if they are an admin.
   * (Should return false)
   */
  @Test
  public void isAdminTest1() {

    assertEquals(false, Utilities.isAdmin('N'));
  }

  /**
   * Tests when an admin is logged in, if they are an admin.
   * (Should return true)
   */
  @Test
  public void isAdminTest2() {

    assertEquals(true, Utilities.isAdmin('A'));
  }

  /**
   * Tests when an invalid mode is checked for admin.
   * Should return the correct error message.s
   */
  @Test
  public void isAdminTest3() {

    assertEquals(false, Utilities.isAdmin('R'));
  }
  
  /**
   * For the getAllMasterAccounts function, it was also tested for
   * loop coverage. The loop coverage also serves as statement
   * coverage in this case.
   */

  /**
   * Tests the getAllMasterAccounts for zero times.
   * (When the list is empty)
   */
  @Test
  public void getAllMasterAccountsLoopTestZero() {

    List<String> masterList = new ArrayList<String>();

    List<User> userAccounts = new ArrayList<User>();

    Utilities.getAllMasterAccounts(masterList);

    assertEquals(0, userAccounts.size());
  }

  /**
   * Tests the getAllMasterAccounts for one time.
   * (When the list has one account in it)
   */
  @Test
  public void getAllMasterAccountsLoopTestOne() {

    List<String> masterList = new ArrayList<String>();
    masterList.add("00001 Tarzan               A 01000.00 0000 N");

    List<User> userAccounts = new ArrayList<User>();
    userAccounts.add(new User("Tarzan", 1, (float)1000.0, 'A', 0, 'N'));

    Utilities.getAllMasterAccounts(masterList);

    assertEquals(1, userAccounts.size());
  }

  /**
   * Tests the getAllMasterAccounts for two times.
   * (When the list has two accounts in it)
   */
  @Test
  public void getAllMasterAccountsLoopTestTwo() {

    List<String> masterList = new ArrayList<String>();
    masterList.add("00001 Tarzan               A 01000.00 0000 N");
    masterList.add("00002 Wayne                A 01000.00 0000 N");

    List<User> userAccounts = new ArrayList<User>();
    userAccounts.add(new User("Tarzan", 1, (float)1000.0, 'A', 0, 'N'));
    userAccounts.add(new User("Wayne", 2, (float)1000.0, 'A', 0, 'N'));

    Utilities.getAllMasterAccounts(masterList);

    assertEquals(2, userAccounts.size());
  }

  /**
   * Tests the getAllMasterAccounts for many times.
   * (When the list has more than two accounts in it)
   */
  @Test
  public void getAllMasterAccountsLoopTestMany() {

    List<String> masterList = new ArrayList<String>();
    masterList.add("00001 Tarzan               A 01000.00 0000 N");
    masterList.add("00002 Wayne                A 01000.00 0000 N");
    masterList.add("00003 Bruce                A 01000.00 0000 N");

    List<User> userAccounts = new ArrayList<User>();
    userAccounts.add(new User("Tarzan", 1, (float)1000.0, 'A', 0, 'N'));
    userAccounts.add(new User("Wayne", 2, (float)1000.0, 'A', 0, 'N'));
    userAccounts.add(new User("Bruce", 3, (float)1000.0, 'A', 0, 'N'));

    Utilities.getAllMasterAccounts(masterList);

    assertEquals(3, userAccounts.size());
  }
  
  /**
   * Tests if the printed out line is as expected. It can be assumed
   * that if the line is correct, then all lines were executed.
   */
  @Test
  public void rewriteMasterFileTest1() {

    String line;
    Main.userAccounts = new ArrayList<User>();
    Main.userAccounts.add(new User("Tarzan", 1, (float)1000.0, 'A', 0, 'N'));

    Utilities.rewriteMasterFile("master_bank_accounts_file_test.txt");

    try (BufferedReader br = new BufferedReader(new FileReader("master_bank_accounts_file_test.txt"))) {
      line = br.readLine();

      assertEquals("00001 Tarzan               A 01000.00 0000 N", line);
    } catch (IOException e) {
      System.err.println("ERROR <internal>: Could not read in file.");
    }
  }

  /**
   * Tests if the printed out line is as expected. It can be assumed
   * that if the line is correct, then all lines were executed.
   */
  @Test
  public void rewriteCurrentFileTest1() {
    String line;
    Main.userAccounts = new ArrayList<User>();
    Main.userAccounts.add(new User("Tarzan", 1, (float)1000.0, 'A', 0, 'N'));

    Utilities.rewriteCurrentFile();

    try (BufferedReader br = new BufferedReader(new FileReader("current_bank_accounts_file.txt"))) {
      line = br.readLine();

      assertEquals("00001 Tarzan               A 01000.00", line);
    } catch (IOException e) {
      System.err.println("ERROR <internal>: Could not read in file.");
    }
  }

  /**
   * Tests for when a name is not unique.
   * (Should return false)
   */
  @Test
  public void isNameUniqueTest1() {

    List<User> userAccounts = new ArrayList<User>();
    userAccounts.add(new User("Tarzan", 1, (float)1000.0, 'A', 0, 'N'));

    String name = "Tarzan";

    assertEquals(false, Utilities.isNameUnique(name));
  }

  /**
   * Tests for when a name is unique.
   * (Should return true)
   */
  @Test
  public void isNameUniqueTest2() {

    List<User> userAccounts = new ArrayList<User>();
    userAccounts.add(new User("Tarzan", 1, (float)1000.0, 'A', 0, 'N'));

    String name = "Luisa";

    assertEquals(true, Utilities.isNameUnique(name));
  }

  /**
   * Tests for when an account number is not unique.
   * (Should return false)
   */
  @Test
  public void isNumberUniqueTest1() {

    List<User> userAccounts = new ArrayList<User>();
    userAccounts.add(new User("Tarzan", 1, (float)1000.0, 'A', 0, 'N'));

    int accNum = 1;

    assertEquals(false, Utilities.isNumberUnique(accNum));
  }

  /**
   * Tests for when an account number is unique.
   * (Should return true)
   */
  @Test
  public void isNumberUniqueTest2() {

    List<User> userAccounts = new ArrayList<User>();
    userAccounts.add(new User("Tarzan", 1, (float)1000.0, 'A', 0, 'N'));

    int accNum = 20;

    assertEquals(true, Utilities.isNumberUnique(accNum));
  }

  /**
   * Tests for when the account number does exist.
   * (Should return the proper index)
   */
  @Test
  public void getAccIndexTest1() {
    
    Main.userAccounts = new ArrayList<User>();
    Main.userAccounts.add(new User("Tarzan", 1, (float)1000.0, 'A', 0, 'N'));

    assertEquals(0, Utilities.getAccIndex(1));
  }

  /**
   * Tests for when the account number does not exist.
   * (Should return -1)
   */
  @Test
  public void getAccIndexTest2() {
    Main.userAccounts = new ArrayList<User>();
    Main.userAccounts.add(new User("Tarzan", 1, (float)1000.0, 'A', 0, 'N'));

    assertEquals(-1, Utilities.getAccIndex(2));
  }

  public static junit.framework.Test suite(){
    return new JUnit4TestAdapter(UtilitiesTest.class);
  }
}
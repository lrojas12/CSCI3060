/**
 * This class will test every method in the Main.java class. The kinds of test
 * to be carried out will be statement, decision and loop coverage.
 * 
 * UpdateMasterTest.java
 * 
 * @author Luisa Rojas
 * @author Truyen Truong
 * @author Danesh Parthipan
 */

import static org.junit.Assert.*;
import junit.framework.JUnit4TestAdapter;
import org.junit.*;
import java.io.*;
import java.util.*;
import java.lang.Object.*;
import java.text.DecimalFormat;

public class UpdateMasterTest {

  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
  
  /**
   * At the switch statement, check for cases 01 (withdrawal), 10 (login) and 00 (logout)
   */
  @Test
  public void updateMasterTest1() {
    
    Main.oldMasterFileName = "master_bank_accounts_file_test.txt";
    Main.transactionFile =  new ArrayList<String>();
    // case 10 and 01
    Main.transactionFile.add("10 Tarzan               00001          S ");
    Main.transactionFile.add("01 Tarzan               00001 00020.00   ");
    Main.transactionFile.add("00 Tarzan               00001          S ");

    Main.userAccounts =  new ArrayList<User>();
    Main.userAccounts.add(new User("Tarzan", 1, (float)1000.00, 'A', 0, 'N'));

    UpdateMaster.updateMaster();

    assertEquals(979.90, Main.userAccounts.get(0).getBalance(), 0.02);
  }

  /**
   * At the switch statement, check for case 02 (transfer)
   */
  @Test
  public void updateMasterTest2() {
    
    Main.oldMasterFileName = "master_bank_accounts_file_test.txt";
    Main.transactionFile =  new ArrayList<String>();
    // case 02
    Main.transactionFile.add("10 Tarzan               00001          S ");
    Main.transactionFile.add("02 Tarzan               00001 00020.00   ");
    Main.transactionFile.add("02 Wayne                00002 00020.00   ");

    Main.userAccounts =  new ArrayList<User>();
    Main.userAccounts.add(new User("Tarzan", 1, (float)1000.00, 'A', 0, 'N'));
    Main.userAccounts.add(new User("Wayne", 2, (float)1000.00, 'A', 0, 'N'));

    UpdateMaster.updateMaster();
    assertEquals(979.90, Main.userAccounts.get(0).getBalance(), 0.04);
    assertEquals(1020.00, Main.userAccounts.get(1).getBalance(), 0.04);
  }

  /**
   * At the switch statement, check for case 03 (paybill)
   */
  @Test
  public void updateMasterTest3() {
    
    Main.oldMasterFileName = "master_bank_accounts_file_test.txt";
    Main.transactionFile =  new ArrayList<String>();
    // case 03
    Main.transactionFile.add("10 Tarzan               00001          S ");
    Main.transactionFile.add("03 Tarzan               00001 00100.00 EC");

    Main.userAccounts =  new ArrayList<User>();
    Main.userAccounts.add(new User("Tarzan", 1, (float)1000.00, 'A', 0, 'N'));

    UpdateMaster.updateMaster();
    assertEquals(899.90, Main.userAccounts.get(0).getBalance(), 0.04);
  }

  /**
   * At the switch statement, check for case 04 (deposit)
   */
  @Test
  public void updateMasterTest4() {
    
    Main.oldMasterFileName = "master_bank_accounts_file_test.txt";
    Main.transactionFile =  new ArrayList<String>();
    // case 04
    Main.transactionFile.add("10 Tarzan               00001          S ");
    Main.transactionFile.add("04 Tarzan               00001 00020.00   ");  

    Main.userAccounts =  new ArrayList<User>();
    Main.userAccounts.add(new User("Tarzan", 1, (float)1000.00, 'A', 0, 'N'));

    UpdateMaster.updateMaster();
    assertEquals(1019.90, Main.userAccounts.get(0).getBalance(), 0.04);
  }

  /**
   * At the switch statement, check for case 05 (create)
   */
  @Test
  public void updateMasterTest5() {
    
    Main.oldMasterFileName = "master_bank_accounts_file_test.txt";
    Main.transactionFile =  new ArrayList<String>();
    // case 05
    Main.transactionFile.add("10                                     A ");
    Main.transactionFile.add("05 LuisaRojas0123456789 00020 00500.00   ");  

    Main.userAccounts =  new ArrayList<User>();
    Main.userAccounts.add(new User("Tarzan", 1, (float)1000.00, 'A', 0, 'N'));
    Main.userAccounts.add(new User("END_OF_FILE", 99999, (float)0000.00, 'D', 0, 'N'));

    UpdateMaster.updateMaster();
    assertEquals("LuisaRojas0123456789", Main.userAccounts.get(1).getName());
  }

  /**
   * At the switch statement, check for case 06 (delete)
   */
  @Test
  public void updateMasterTest6() {
    
    Main.oldMasterFileName = "master_bank_accounts_file_test.txt";
    Main.transactionFile =  new ArrayList<String>();
    // case 06
    Main.transactionFile.add("10                                     A ");
    Main.transactionFile.add("06 Bruce                00003            ");  

    Main.userAccounts =  new ArrayList<User>();
    Main.userAccounts.add(new User("Tarzan", 1, (float)1000.00, 'A', 0, 'N'));
    Main.userAccounts.add(new User("Bruce", 3, (float)1000.00, 'A', 0, 'N'));
    Main.userAccounts.add(new User("Kent", 4, (float)1000.00, 'A', 0, 'N'));

    UpdateMaster.updateMaster();
    assertEquals("Kent", Main.userAccounts.get(1).getName());
  }

  /**
   * At the switch statement, check for case 07 (disable)
   */
  @Test
  public void updateMasterTest7() {
    
    Main.oldMasterFileName = "master_bank_accounts_file_test.txt";
    Main.transactionFile =  new ArrayList<String>();
    // case 07
    Main.transactionFile.add("10                                     A ");
    Main.transactionFile.add("07 Kent                 00004            ");  

    Main.userAccounts =  new ArrayList<User>();
    Main.userAccounts.add(new User("Kent", 4, (float)1000.00, 'A', 0, 'N'));

    UpdateMaster.updateMaster();
    assertEquals('D', Main.userAccounts.get(0).getStatus());
  }

  /**
   * At the switch statement, check for case 08 (changeplan)
   */
  @Test
  public void updateMasterTest8() {
    
    Main.oldMasterFileName = "master_bank_accounts_file_test.txt";
    Main.transactionFile =  new ArrayList<String>();
    // case 08
    Main.transactionFile.add("10                                     A ");
    Main.transactionFile.add("08 Ramond               00008            ");  

    Main.userAccounts =  new ArrayList<User>();
    Main.userAccounts.add(new User("Ramond", 8, (float)1000.00, 'A', 0, 'N'));
    UpdateMaster.updateMaster();
    assertEquals('S', Main.userAccounts.get(0).getPlan());
  }

  /**
   * At the switch statement, check for case 09 (enable)
   */
  @Test
  public void updateMasterTest9() {
    
    Main.oldMasterFileName = "master_bank_accounts_file_test.txt";
    Main.currUser = new User('A');

    Main.transactionFile =  new ArrayList<String>();
    // case 09
    Main.transactionFile.add("09 Harvey               00006            ");  

    Main.userAccounts =  new ArrayList<User>();
    Main.userAccounts.add(new User("Harvey", 6, (float)200.00, 'D', 0, 'N'));

    UpdateMaster.updateMaster();
    assertEquals('A', Main.userAccounts.get(0).getStatus());
  }

  /**
   * Tests for when the account number does not exist.
   * (Should return false)
   */
  @Test
  public void withdrawalTest1() {

    assertEquals(false, UpdateMaster.withdrawal(0, (float)20.00, true));
  }

  /**
   * Tests for when withdrawing when logged in as admin.
   * (Should return true)
   */
  @Test
  public void withdrawalTest2() {
    
    Main.currUser = new User('A');

    Main.userAccounts =  new ArrayList<User>();
    Main.userAccounts.add(new User("Tarzan", 1, (float)1000.00, 'A', 0, 'S'));

    assertEquals(true, UpdateMaster.withdrawal(1, (float)20.00, true));
  }

  /**
   * Tests for when withdrawing when logged in as student standard.
   * (Should return true)
   */
  @Test
  public void withdrawalTest3() {

    Main.userAccounts =  new ArrayList<User>();
    Main.userAccounts.add(new User("Tarzan", 1, (float)1000.00, 'A', 0, 'S'));

    Main.currUser = Main.userAccounts.get(0);

    assertEquals(true, UpdateMaster.withdrawal(1, (float)20.00, false));
  }

  /**
   * Tests for when withdrawing when logged in as non-student standard.
   * (Should return true)
   */
  @Test
  public void withdrawalTest4() {

    Main.userAccounts =  new ArrayList<User>();
    Main.userAccounts.add(new User("Tarzan", 1, (float)1000.00, 'A', 0, 'N'));

    Main.currUser = Main.userAccounts.get(0);

    assertEquals(true, UpdateMaster.withdrawal(1, (float)500.00, false));
  }

  /**
   * Tests for when there is an invalid payment plan.
   * (Should return false)
   */
  @Test
  public void withdrawalTest5() {

    Main.userAccounts =  new ArrayList<User>();
    Main.userAccounts.add(new User("Tarzan", 1, (float)1000.00, 'A', 0, 'F'));

    Main.currUser = Main.userAccounts.get(0);

    assertEquals(false, UpdateMaster.withdrawal(1, (float)500.00, false));
  }

  /**
   * Tests for success when logged in as non-student standard and that
   * the number of transactions for that account did not change.
   */
  @Test
  public void transferTest1() {
    Main.userAccounts =  new ArrayList<User>();
    Main.userAccounts.add(new User("Tarzan", 1, (float)1000.00, 'A', 0, 'N'));
    Main.userAccounts.add(new User("Wayne", 2, (float)99999.99, 'A', 0, 'N'));

    Main.currUser = Main.userAccounts.get(0);

    UpdateMaster.transfer(1, 2, (float)100.00, false);

    assertEquals((float)1000.00, Main.userAccounts.get(0).getBalance(), 0.02);
    assertEquals(0, Main.userAccounts.get(0).getNumTran());
  }

  /**
   * Tests for success when logged in as student standard.
   */
  @Test
  public void transferTest2() {
    Main.userAccounts =  new ArrayList<User>();
    Main.userAccounts.add(new User("Tarzan", 1, (float)1000.00, 'A', 0, 'S'));
    Main.userAccounts.add(new User("Wayne", 2, (float)99999.99, 'A', 0, 'S'));

    Main.currUser = Main.userAccounts.get(0);

    UpdateMaster.transfer(1, 2, (float)100.00, false);

    assertEquals((float)1000.00, Main.userAccounts.get(0).getBalance(), 0.02);
  }

  /**
   * Tests when the withdrawing portion failed.
   */
  @Test
  public void transferTest3() {
    ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    System.setErr(new PrintStream(errContent));

    Main.userAccounts = new ArrayList<User>();
    Main.userAccounts.add(new User("Tarzan", 1, (float)1000.0, 'A', 0, 'N'));
    Main.userAccounts.add(new User("Wayne", 2, (float)1000.0, 'A', 0, 'N'));

    UpdateMaster.transfer(1, 2, (float)1001.00, true);

    String expectedOutput = "ERROR <<transfer>>: There was an issue withdrawing the funds from the origin account.\n";

    assertEquals(expectedOutput, errContent.toString());
  }

  /**
   * Tests for when the paybill does not succeed.
   */
  @Test
  public void paybillTest1() {
    ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    System.setErr(new PrintStream(errContent));

    Main.userAccounts = new ArrayList<User>();
    Main.userAccounts.add(new User("Tarzan", 1, (float)1000.0, 'A', 0, 'N'));

    UpdateMaster.paybill(1, (float)2000.00, true);

    String expectedOutput = "ERROR <<paybill>>: There was an issue withdrawing the funds from the origin account.\n";

    assertEquals(expectedOutput, errContent.toString());
  }

  /**
   * Tests for when the account to deposit into does not exist.
   * (Should return false)
   */
  @Test
  public void depositTest1() {

    Main.userAccounts =  new ArrayList<User>();
    Main.userAccounts.add(new User("Tarzan", 1, (float)1000.00, 'A', 0, 'S'));

    assertEquals(false, UpdateMaster.deposit(0, (float)20.00, true));
  }

  /**
   * Tests for success when depositing while logged in an admin account.
   * (Should return true)
   */
  @Test
  public void depositTest2() {

    Main.currUser = new User('A');

    Main.userAccounts =  new ArrayList<User>();
    Main.userAccounts.add(new User("Tarzan", 1, (float)1000.00, 'A', 0, 'S'));

    assertEquals(true, UpdateMaster.deposit(1, (float)20.00, true));
  }

  /**
   * Tests for success when depositing when logged in as a student standard account.
   * (Should return true)
   */
  @Test
  public void depositTest3() {

    Main.userAccounts =  new ArrayList<User>();
    Main.userAccounts.add(new User("Tarzan", 1, (float)1000.00, 'A', 0, 'S'));

    Main.currUser = Main.userAccounts.get(0);

    assertEquals(true, UpdateMaster.deposit(1, (float)20.00, false));
  }

  /**
   * Tests for success when depositing when logged in as a non-student standard account.
   * (Should return true)
   */
  @Test
  public void depositTest4() {

    Main.userAccounts =  new ArrayList<User>();
    Main.userAccounts.add(new User("Tarzan", 1, (float)1000.00, 'A', 0, 'N'));

    Main.currUser = Main.userAccounts.get(0);

    assertEquals(true, UpdateMaster.deposit(1, (float)20.00, false));
  }
  
  /**
   * Tests for when the account plan is not valid.
   * (Should return false)
   */
  @Test
  public void depositTest5() {

    Main.userAccounts =  new ArrayList<User>();
    Main.userAccounts.add(new User("Tarzan", 1, (float)1000.00, 'A', 0, 'F'));

    Main.currUser = Main.userAccounts.get(0);

    assertEquals(false, UpdateMaster.deposit(1, (float)20.00, false));
  }

  /**
   * Tests for success when all constraints are met, and the account is created.
   */
  @Test
  public void createTest1() {
    Main.userAccounts = new ArrayList<User>();
    Main.userAccounts.add(new User("Tarzan", 1, (float)1000.0, 'A', 0, 'N'));

    UpdateMaster.create("Bob", 2, (float)1000.00);

    assertEquals(2, Main.userAccounts.size());
  }

  /**
   * Tests for when a constraint fails and the account is not created.
   */
  @Test
  public void createTest2() {
    ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    System.setErr(new PrintStream(errContent));

    Main.userAccounts = new ArrayList<User>();
    UpdateMaster.create("Tarzan", 1, (float)100000.00);

    String expectedOutput = "ERROR <<create>>: The constraints inputted (name, account number, balance) are incorrect.\n";

    assertEquals(expectedOutput, errContent.toString());
    assertEquals(0, Main.userAccounts.size());
  }

  /**
   * Tests for when the account to delete does not exist.
   */
  @Test
  public void deleteTest1() {
    ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    System.setErr(new PrintStream(errContent));

    UpdateMaster.delete(0);

    String expectedOutput = "ERROR <<delete>>: The account to be deleted does not exist.\n";

    assertEquals(expectedOutput, errContent.toString());
  }

  /**
   * Tests for when the account to delete is successfully deleted.
   */
  @Test
  public void deleteTest2() {

    Main.userAccounts = new ArrayList<User>();
    Main.userAccounts.add(new User("Tarzan", 1, (float)1000.0, 'A', 0, 'N'));

    UpdateMaster.delete(1);

    assertEquals(0, Main.userAccounts.size());
  }

  /**
   * Tests for when the account to be disabled does not exist.
   */
  @Test
  public void disableTest1() {
    ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    System.setErr(new PrintStream(errContent));

    UpdateMaster.disable(0);

    String expectedOutput = "ERROR <<disable>>: The account to be disabled does not exist.\n";

    assertEquals(expectedOutput, errContent.toString());
  }

  /**
   * Tests for when the account to be disabled is successfully disabled.
   */
  @Test
  public void disableTest2() {
    Main.userAccounts = new ArrayList<User>();
    Main.userAccounts.add(new User("Tarzan", 1, (float)1000.0, 'A', 0, 'N'));

    UpdateMaster.disable(1);

    assertEquals('D', Main.userAccounts.get(0).getStatus());
  }

  /**
   * Tests for when the account to be disabled is already disabled.
   */
  @Test
  public void disableTest3() {
    ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    System.setErr(new PrintStream(errContent));

    Main.userAccounts = new ArrayList<User>();
    Main.userAccounts.add(new User("Tarzan", 1, (float)1000.0, 'D', 0, 'N'));

    UpdateMaster.disable(1);

    String expectedOutput = "ERROR <<disable>>: The account to be disabled already is.\n";


    assertEquals(expectedOutput, errContent.toString());
  }

  /**
   * Tests for when the account to change plans does not exist.
   */
  @Test
  public void changeplanTest1() {
    
    ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    System.setErr(new PrintStream(errContent));

    UpdateMaster.changeplan(0);

    String expectedOutput = "ERROR <<changeplan>>: The account changing plans does not exist.\n";

    assertEquals(expectedOutput, errContent.toString());
  }

  /**
   * Tests for when the account is changed from student to non-student.
   */
  @Test
  public void changeplanTest2() {
    
    Main.currUser = new User();

    Main.transactionFile =  new ArrayList<String>();
    Main.transactionFile.add("10                                     A ");
    Main.transactionFile.add("08 Tarzan               00001            ");

    Main.userAccounts =  new ArrayList<User>();
    Main.userAccounts.add(new User("Tarzan", 1, (float)1000.00, 'A', 0, 'S'));

    UpdateMaster.changeplan(1);
    assertEquals('N', Main.userAccounts.get(0).getPlan());
  }

  /**
   * Tests for when the account is changed from non-student to student.
   */
  @Test
  public void changeplanTest3() {
    
    Main.currUser = new User();

    Main.transactionFile =  new ArrayList<String>();
    Main.transactionFile.add("10                                     A ");
    Main.transactionFile.add("08 Tarzan               00001            ");

    Main.userAccounts =  new ArrayList<User>();
    Main.userAccounts.add(new User("Tarzan", 1, (float)1000.00, 'A', 0, 'N'));

    UpdateMaster.changeplan(1);
    assertEquals('S', Main.userAccounts.get(0).getPlan());
  }

  /**
   * Tests for when the account to be enabled does not exist.
   */
  @Test
  public void enableTest1() {
    ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    System.setErr(new PrintStream(errContent));

    UpdateMaster.enable(0);

    String expectedOutput = "ERROR <<enable>>: The account to be enabled in does not exist.\n";

    assertEquals(expectedOutput, errContent.toString());
  }

  /**
   * Tests for when a disabled account is successfully enabled.
   */
  @Test
  public void enableTest2() {
    Main.userAccounts = new ArrayList<User>();
    Main.userAccounts.add(new User("Tarzan", 1, (float)1000.0, 'D', 0, 'N'));

    UpdateMaster.enable(1);

    assertEquals('A', Main.userAccounts.get(0).getStatus());
  }

  /**
   * Tests for when trying to enable and account that is already enabled.
   */
  @Test
  public void enableTest3() {
    ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    System.setErr(new PrintStream(errContent));

    Main.userAccounts = new ArrayList<User>();
    Main.userAccounts.add(new User("Tarzan", 1, (float)1000.0, 'A', 0, 'N'));

    UpdateMaster.enable(1);

    String expectedOutput = "ERROR <<enable>>: The account to be enabled already is.\n";

    assertEquals(expectedOutput, errContent.toString());
  }

  /**
   * Tests for the account to login to does not exist.
   */
  @Test
  public void loginTest1() {
    ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    System.setErr(new PrintStream(errContent));

    Main.userAccounts = new ArrayList<User>();
    Main.userAccounts.add(new User("Tarzan", 1, (float)1000.0, 'A', 0, 'N'));
    Utilities.misc = "S ";

    UpdateMaster.login(0);
    
    String expectedOutput = "ERROR <<login>>: The account to be logged in does not exist.\n";

    assertEquals(expectedOutput, errContent.toString());
  }

  /**
   * Tests for successfully logging into a standard account.
   */
  @Test 
  public void loginTest2() {

    Main.userAccounts = new ArrayList<User>();
    Main.userAccounts.add(new User("Tarzan", 1, (float)1000.0, 'A', 0, 'N'));
    Utilities.misc = "S ";

    UpdateMaster.login(1);

    assertEquals('N', Main.currUser.getPlan());
  }

  /**
   * Tests for successfully logging into an admin account.
   */
  @Test 
  public void loginTest3() {
    Main.currUser = new User("Tarzan", 1, (float)1000.0, 'A', 0, 'N');
    Utilities.misc = "A ";

    UpdateMaster.login(0);

    assertEquals('A', Main.currUser.getPlan());
  }

  /**
   * Tests for when the there is an invalid mode to log in as.
   */
  @Test
  public void loginTest4() {
    ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    System.setErr(new PrintStream(errContent));

    Utilities.misc = "  ";
    UpdateMaster.login(0);
    
    String expectedOutput = "ERROR <<login>>: The mode to be logged in as is not valid.\n";

    assertEquals(expectedOutput, errContent.toString());
  }

  /**
   * Tests for sucessfully logout.
   */
  @Test
  public void logoutTest1() {
    
    Main.currUser = new User("Tarzan", 1, (float)1000.0, 'A', 0, 'N');

    UpdateMaster.logout();

    assertEquals((new User()).getPlan(), Main.currUser.getPlan());
  }

  public static junit.framework.Test suite(){
     return new JUnit4TestAdapter(UpdateMasterTest.class);
  }
}
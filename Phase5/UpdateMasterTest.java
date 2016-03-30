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
    
    Main.oldMasterFileName = "master_bank_accounts_file_tests.txt";
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
    
    Main.oldMasterFileName = "master_bank_accounts_file_tests.txt";
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
    
    Main.oldMasterFileName = "master_bank_accounts_file_tests.txt";
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
    
    Main.oldMasterFileName = "master_bank_accounts_file_tests.txt";
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
    
    Main.oldMasterFileName = "master_bank_accounts_file_tests.txt";
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
    
    Main.oldMasterFileName = "master_bank_accounts_file_tests.txt";
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
    
    Main.oldMasterFileName = "master_bank_accounts_file_tests.txt";
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
    
    Main.oldMasterFileName = "master_bank_accounts_file_tests.txt";
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
    
    Main.oldMasterFileName = "master_bank_accounts_file_tests.txt";
    Main.currUser = new User('A');

    Main.transactionFile =  new ArrayList<String>();
    // case 09
    Main.transactionFile.add("09 Harvey               00006            ");  

    Main.userAccounts =  new ArrayList<User>();
    Main.userAccounts.add(new User("Harvey", 6, (float)200.00, 'D', 0, 'N'));

    UpdateMaster.updateMaster();
    assertEquals('A', Main.userAccounts.get(0).getStatus());
  }

  @Test
  public void withdrawalTest1() {

  //  withdrawal(int accNum, float amount, boolean admin) {

    assertEquals(false, UpdateMaster.withdrawal(0, (float)20.00, true));
  }

  @Test
  public void withdrawalTest2() {
    
    Main.currUser = new User('A');

    Main.userAccounts =  new ArrayList<User>();
    Main.userAccounts.add(new User("Tarzan", 1, (float)1000.00, 'A', 0, 'S'));

    assertEquals(true, UpdateMaster.withdrawal(1, (float)20.00, true));
  }

  @Test
  public void withdrawalTest3() {

    Main.userAccounts =  new ArrayList<User>();
    Main.userAccounts.add(new User("Tarzan", 1, (float)1000.00, 'A', 0, 'S'));

    Main.currUser = Main.userAccounts.get(0);

    assertEquals(true, UpdateMaster.withdrawal(1, (float)20.00, false));
  }

  @Test
  public void withdrawalTest4() {

    Main.userAccounts =  new ArrayList<User>();
    Main.userAccounts.add(new User("Tarzan", 1, (float)1000.00, 'A', 0, 'F'));

    Main.currUser = Main.userAccounts.get(0);

    assertEquals(false, UpdateMaster.withdrawal(1, (float)500.00, false));
  }

  @Test
  public void transferTest1() {
    Main.userAccounts =  new ArrayList<User>();
    Main.userAccounts.add(new User("Tarzan", 1, (float)1000.00, 'A', 0, 'N'));
    Main.userAccounts.add(new User("Wayne", 2, (float)99999.99, 'A', 0, 'N'));

    Main.currUser = Main.userAccounts.get(0);

    UpdateMaster.transfer(1, 2, (float)100.00, false);

    assertEquals((float)1000.00, Main.userAccounts.get(0).getBalance(), 0.02);
  }

  @Test
  public void transferTest2() {
    Main.userAccounts =  new ArrayList<User>();
    Main.userAccounts.add(new User("Tarzan", 1, (float)1000.00, 'A', 0, 'S'));
    Main.userAccounts.add(new User("Wayne", 2, (float)99999.99, 'A', 0, 'S'));

    Main.currUser = Main.userAccounts.get(0);

    UpdateMaster.transfer(1, 2, (float)100.00, false);

    assertEquals((float)1000.00, Main.userAccounts.get(0).getBalance(), 0.02);
  }

  @Test
  public void transferTest3() {
    Main.userAccounts =  new ArrayList<User>();
    Main.userAccounts.add(new User("Tarzan", 1, (float)1000.00, 'A', 0, 'N'));
    Main.userAccounts.add(new User("Wayne", 2, (float)99999.99, 'A', 0, 'N'));

    Main.currUser = Main.userAccounts.get(0);

    UpdateMaster.transfer(1, 2, (float)100.00, false);

    assertEquals(0, Main.userAccounts.get(0).getNumTran());
  }

  @Test
  public void transferTest4() {
    ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    System.setErr(new PrintStream(errContent));

    Main.userAccounts = new ArrayList<User>();
    Main.userAccounts.add(new User("Tarzan", 1, (float)1000.0, 'A', 0, 'N'));
    Main.userAccounts.add(new User("Wayne", 2, (float)1000.0, 'A', 0, 'N'));

    UpdateMaster.transfer(1, 2, (float)1001.00, true);

    String expectedOutput = "ERROR <<transfer>>: There was an issue withdrawing the funds from the origin account.\n";

    assertEquals(expectedOutput, errContent.toString());
  }

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

  @Test
  public void depositTest1() {

    assertEquals(false, UpdateMaster.deposit(0, (float)20.00, true));
  }

  @Test
  public void depositTest2() {

    Main.currUser = new User('A');

    Main.userAccounts =  new ArrayList<User>();
    Main.userAccounts.add(new User("Tarzan", 1, (float)1000.00, 'A', 0, 'S'));

    assertEquals(true, UpdateMaster.deposit(1, (float)20.00, true));
  }

  @Test
  public void depositTest3() {

    Main.userAccounts =  new ArrayList<User>();
    Main.userAccounts.add(new User("Tarzan", 1, (float)1000.00, 'A', 0, 'S'));

    Main.currUser = Main.userAccounts.get(0);

    assertEquals(true, UpdateMaster.deposit(1, (float)20.00, false));
  }

  @Test
  public void depositTest4() {

    Main.userAccounts =  new ArrayList<User>();
    Main.userAccounts.add(new User("Tarzan", 1, (float)1000.00, 'A', 0, 'N'));

    Main.currUser = Main.userAccounts.get(0);

    assertEquals(true, UpdateMaster.deposit(1, (float)20.00, false));
  }

  @Test
  public void depositTest5() {

    Main.userAccounts =  new ArrayList<User>();
    Main.userAccounts.add(new User("Tarzan", 1, (float)1000.00, 'A', 0, 'F'));

    Main.currUser = Main.userAccounts.get(0);

    assertEquals(false, UpdateMaster.deposit(1, (float)20.00, false));
  }

  @Test
  public void createTest1() {
    Main.userAccounts = new ArrayList<User>();
    Main.userAccounts.add(new User("Tarzan", 1, (float)1000.0, 'A', 0, 'N'));

    UpdateMaster.create("Bob", 2, (float)1000.00);

    assertEquals(2, Main.userAccounts.size());
  }

  @Test
  public void createTest2() {
    ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    System.setErr(new PrintStream(errContent));

    UpdateMaster.create("Tarzan", 1, (float)100000.00);

    String expectedOutput = "ERROR <<create>>: The constraints inputted (name, account number, balance) are incorrect.\n";

    assertEquals(expectedOutput, errContent.toString());
  }

  @Test
  public void deleteTest1() {
    ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    System.setErr(new PrintStream(errContent));

    UpdateMaster.delete(0);

    String expectedOutput = "ERROR <<delete>>: The account to be deleted does not exist.\n";

    assertEquals(expectedOutput, errContent.toString());
  }

  @Test
  public void deleteTest2() {

    Main.userAccounts = new ArrayList<User>();
    Main.userAccounts.add(new User("Tarzan", 1, (float)1000.0, 'A', 0, 'N'));

    UpdateMaster.delete(1);

    assertEquals(0, Main.userAccounts.size());
  }

  @Test
  public void disableTest1() {
    ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    System.setErr(new PrintStream(errContent));

    UpdateMaster.disable(0);

    String expectedOutput = "ERROR <<disable>>: The account to be disabled does not exist.\n";

    assertEquals(expectedOutput, errContent.toString());
  }

  @Test
  public void disableTest2() {
    Main.userAccounts = new ArrayList<User>();
    Main.userAccounts.add(new User("Tarzan", 1, (float)1000.0, 'A', 0, 'N'));

    UpdateMaster.disable(1);

    assertEquals('D', Main.userAccounts.get(0).getStatus());
  }

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

  @Test
  public void changeplanTest1() {
    
    ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    System.setErr(new PrintStream(errContent));

    UpdateMaster.changeplan(0);

    String expectedOutput = "ERROR <<changeplan>>: The account changing plans does not exist.\n";

    assertEquals(expectedOutput, errContent.toString());
  }

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

  @Test
  public void enableTest1() {
    ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    System.setErr(new PrintStream(errContent));

    UpdateMaster.enable(0);

    String expectedOutput = "ERROR <<enable>>: The account to be enabled in does not exist.\n";

    assertEquals(expectedOutput, errContent.toString());
  }

  @Test
  public void enableTest2() {
    Main.userAccounts = new ArrayList<User>();
    Main.userAccounts.add(new User("Tarzan", 1, (float)1000.0, 'D', 0, 'N'));

    UpdateMaster.enable(1);

    assertEquals('A', Main.userAccounts.get(0).getStatus());
  }

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

  @Test 
  public void loginTest2() {
    Main.currUser = new User("Tarzan", 1, (float)1000.0, 'A', 0, 'N');
    Utilities.misc = "A ";

    UpdateMaster.login(0);

    assertEquals('A', Main.currUser.getPlan());
  }

  @Test
  public void loginTest3() {
    ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    System.setErr(new PrintStream(errContent));

    Utilities.misc = "  ";
    UpdateMaster.login(0);
    
    String expectedOutput = "ERROR <<login>>: The mode to be logged in as is not valid.\n";

    assertEquals(expectedOutput, errContent.toString());
  }

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
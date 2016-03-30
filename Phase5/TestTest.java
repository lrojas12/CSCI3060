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

// Taken from Stack example
public class TestTest {
    
    @Test
    public void updateMasterTest1() {
    	
    	Main.currUser = new User();

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

    @Test
    public void updateMasterTest2() {
    	
    	Main.currUser = new User();

    	Main.transactionFile =  new ArrayList<String>();
    	// case 00
    	Main.transactionFile.add("10 Tarzan               00001          S ");
    	Main.transactionFile.add("00 Tarzan               00001          S ");

    	Main.userAccounts =  new ArrayList<User>();
    	Main.userAccounts.add(new User("Tarzan", 1, (float)1000.00, 'A', 0, 'N'));

    	UpdateMaster.updateMaster();
    	assertEquals(1000.00, Main.userAccounts.get(0).getBalance(), 0.02);
    }

    @Test
    public void updateMasterTest3() {
    	
    	Main.currUser = new User();

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

    @Test
    public void updateMasterTest4() {
    	
    	Main.currUser = new User();

    	Main.transactionFile =  new ArrayList<String>();
    	// case 03
    	Main.transactionFile.add("10 Tarzan               00001          S ");
    	Main.transactionFile.add("03 Tarzan               00001 00100.00 EC");

    	Main.userAccounts =  new ArrayList<User>();
    	Main.userAccounts.add(new User("Tarzan", 1, (float)1000.00, 'A', 0, 'N'));

    	UpdateMaster.updateMaster();
    	assertEquals(899.90, Main.userAccounts.get(0).getBalance(), 0.04);
    }

    @Test
    public void updateMasterTest5() {
    	
    	Main.currUser = new User();

    	Main.transactionFile =  new ArrayList<String>();
    	// case 04
    	Main.transactionFile.add("10 Tarzan               00001          S ");
    	Main.transactionFile.add("04 Tarzan               00001 00020.00   ");  

    	Main.userAccounts =  new ArrayList<User>();
    	Main.userAccounts.add(new User("Tarzan", 1, (float)1000.00, 'A', 0, 'N'));

    	UpdateMaster.updateMaster();
    	assertEquals(1019.90, Main.userAccounts.get(0).getBalance(), 0.04);
    }

    // DOESNT WORK
    /*@Test
    public void updateMasterTest6() {
    	
    	Main.currUser = new User();

    	Main.transactionFile =  new ArrayList<String>();
    	// case 05
    	Main.transactionFile.add("10                                      A ");
    	Main.transactionFile.add("05 LuisaRojas0123456789 00020 00500.00    ");  

    	Main.userAccounts =  new ArrayList<User>();

    	UpdateMaster.updateMaster();
    	assertEquals("LuisaRojas0123456789", Main.userAccounts.get(0).getName());
    }

    public static junit.framework.Test suite(){
       return new JUnit4TestAdapter(UpdateMasterTest.class);
    }*/

    @Test
    public void transferTest1() {
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
    public void transferTest2() {
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));

        Main.userAccounts = new ArrayList<User>();
        Main.userAccounts.add(new User("Tarzan", 1, (float)1000.0, 'A', 0, 'N'));
        Main.userAccounts.add(new User("Wayne", 2, (float)99999.99, 'A', 0, 'N'));

        UpdateMaster.transfer(1, 2, (float)100.00, true);

        String expectedOutput = "ERROR <<transfer>>: There was an issue depositing the funds into the destination account.\n";

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
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));

        UpdateMaster.deposit(0, (float)20.00, true);

        String expectedOutput = "ERROR <<deposit>>: The account to be deposited into does not exist.\n";

        assertEquals(expectedOutput, errContent.toString());
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
}
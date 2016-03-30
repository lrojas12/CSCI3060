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

public class UpdateMasterTest {

	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    
    /**
     * At the switch statement, check for case 01 (withdrawal) and case 10 (login)
     */
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

    /**
     * At the switch statement, check for case 00 (logout)
     */
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

    /**
     * At the switch statement, check for case 02 (transfer)
     */
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

    /**
     * At the switch statement, check for case 03 (paybill)
     */
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

    /**
     * At the switch statement, check for case 04 (deposit)
     */
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

    /**
     * At the switch statement, check for case 05 (create)
     */
    @Test
    public void updateMasterTest6() {
    	
    	Main.currUser = new User();

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
    public void updateMasterTest7() {
    	
    	Main.currUser = new User();

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
    public void updateMasterTest8() {
    	
    	Main.currUser = new User();

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
    public void updateMasterTest9() {
    	
    	Main.currUser = new User();

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
    public void updateMasterTest10() {
    	
    	Main.currUser = new User();

    	Main.transactionFile =  new ArrayList<String>();
    	// case 09
    	Main.transactionFile.add("10                                     A ");
    	Main.transactionFile.add("09 Harvey               00006            ");  

    	Main.userAccounts =  new ArrayList<User>();
    	Main.userAccounts.add(new User("Harvey", 6, (float)200.00, 'D', 0, 'N'));

    	UpdateMaster.updateMaster();
    	assertEquals('A', Main.userAccounts.get(0).getStatus());
    }

	@Test
    public void withdrawalTest() {
    	
    	Main.currUser = new User();

    	Main.transactionFile =  new ArrayList<String>();
    	Main.transactionFile.add("10 Tarzan               00001          S ");
    	Main.transactionFile.add("01 Tarzan               00001 00020.00   ");

    	Main.userAccounts =  new ArrayList<User>();
    	Main.userAccounts.add(new User("Tarzan", 1, (float)1000.00, 'A', 0, 'S'));

    	UpdateMaster.withdrawal(1, (float)20.00, false);
    	assertEquals(979.95, Main.userAccounts.get(0).getBalance(), 0.02);
    }

	@Test
    public void withdrawalTest() {
    	
    	Main.currUser = new User();

    	Main.transactionFile =  new ArrayList<String>();
    	Main.transactionFile.add("10                                     A ");
    	Main.transactionFile.add("01 Tarzan               00001 00020.00   ");

    	Main.userAccounts =  new ArrayList<User>();
    	Main.userAccounts.add(new User("Tarzan", 1, (float)1000.00, 'A', 0, 'S'));

    	UpdateMaster.withdrawal(1, (float)20.00, false);
    	// No fee gets charged since an admin is performing the transaction
    	assertEquals(980.00, Main.userAccounts.get(0).getBalance(), 0.02);
    }

   	@Test
    public void changeplanTest() {
    	
    	Main.currUser = new User();

    	Main.transactionFile =  new ArrayList<String>();
    	Main.transactionFile.add("10                                     A ");
    	Main.transactionFile.add("08 Tarzan               00001            ");

    	Main.userAccounts =  new ArrayList<User>();
    	Main.userAccounts.add(new User("Tarzan", 1, (float)1000.00, 'A', 0, 'S'));

    	UpdateMaster.changeplan(1, 'N');
    	assertEquals('N', Main.userAccounts.get(0).getPlan());
    }

   	@Test
    public void changeplanTest() {
    	
    	Main.currUser = new User();

    	Main.transactionFile =  new ArrayList<String>();
    	Main.transactionFile.add("10                                     A ");
    	Main.transactionFile.add("08 Tarzan               00001            ");

    	Main.userAccounts =  new ArrayList<User>();
    	Main.userAccounts.add(new User("Tarzan", 1, (float)1000.00, 'A', 0, 'N'));

    	UpdateMaster.changeplan(1, 'N');
    	assertEquals('S', Main.userAccounts.get(0).getPlan());
    }

    public static junit.framework.Test suite(){
       return new JUnit4TestAdapter(UpdateMasterTest.class);
    }
}
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

// Taken from Stack example
public class UtilitiesTest {
    
	/*@Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();
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

    @Test
    public void storeFileTest2() {

        Main.transactionFile = new ArrayList<String>();

        Utilities.storeFile("transaction_file_storeFileTest2.tra", Main.transactionFile);

    	assertEquals("10 Tarzan               00001          S ", Main.transactionFile.get(0));
    }
    
    @Test
    public void tokenizeTransactionTest1() {
    	String line = "10 Tarzan               00001          S ";

    	Utilities.tokenizeTransaction(line);

    	assertEquals("S ", Utilities.misc);
    }

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

    @Test
    public void tokenizeMasterTest1() {
    	String line = "00001 Tarzan               A 01000.00 0000 N";

    	Utilities.tokenizeMaster(line);

    	assertEquals("N", Utilities.accPlan);
    }

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

    @Test
    public void isAdminTest1() {

    	assertEquals(false, Utilities.isAdmin('N'));
	}

    @Test
    public void isAdminTest2() {

    	assertEquals(true, Utilities.isAdmin('A'));
	}

    @Test
    public void isAdminTest3() {

    	assertEquals(false, Utilities.isAdmin('R'));
	}

	@Test
	public void getAllMasterAccountsLoopTestZero() {

		List<String> masterList = new ArrayList<String>();

		List<User> userAccounts = new ArrayList<User>();

		Utilities.getAllMasterAccounts(masterList);

		assertEquals(0, userAccounts.size());
	}

	@Test
	public void getAllMasterAccountsLoopTestOne() {

		List<String> masterList = new ArrayList<String>();
		masterList.add("00001 Tarzan               A 01000.00 0000 N");

		List<User> userAccounts = new ArrayList<User>();
		userAccounts.add(new User("Tarzan", 1, (float)1000.0, 'A', 0, 'N'));

		Utilities.getAllMasterAccounts(masterList);

		assertEquals(1, userAccounts.size());
	}

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

	@Test
	public void isNameUniqueTest1() {
		String name = "Tarzan";

		assertEquals(false, Utilities.isNameUnique(name));
	}

	@Test
	public void isNameUniqueTest2() {
		String name = "Luisa";

		assertEquals(true, Utilities.isNameUnique(name));
	}

	@Test
	public void isNumberUniqueTest1() {
		int accNum = 1;

		assertEquals(false, Utilities.isNumberUnique(accNum));
	}

	@Test
	public void isNumberUniqueTest2() {
		int accNum = 20;

		assertEquals(true, Utilities.isNumberUnique(accNum));
	}

	@Test
	public void getAccIndexTest1() {
		Main.userAccounts = new ArrayList<User>();
		Main.userAccounts.add(new User("Tarzan", 1, (float)1000.0, 'A', 0, 'N'));
		

		assertEquals(0, Utilities.getAccIndex(1));
	}

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
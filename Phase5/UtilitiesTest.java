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

        Utilities.storeFile("test.txt", transactionFile);
        String expectedOutput = "ERROR <internal>: Could not read in file.\n";

    	assertEquals(expectedOutput, errContent.toString());
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

		assertEquals(userAccounts.size(), 0);
	}

	@Test
	public void getAllMasterAccountsLoopTestOne() {

		List<String> masterList = new ArrayList<String>();
		masterList.add("00001 Tarzan               A 01000.00 0000 N");

		List<User> userAccounts = new ArrayList<User>();
		userAccounts.add(new User("Tarzan", 1, (float)1000.0, 'A', 0, 'N'));

		Utilities.getAllMasterAccounts(masterList);

		assertEquals(userAccounts.size(), 1);
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

		assertEquals(userAccounts.size(), 2);
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

		assertEquals(userAccounts.size(), 3);
	}

    public static junit.framework.Test suite(){
       return new JUnit4TestAdapter(UtilitiesTest.class);
    }
}



/**
 * STATEMENT COVERAGE
 * 
 * Causes every statement in the program to be executed at least
 * once, giving us confidence that every statement is at least
 * capable of executing correctly.get
 * 
 * System: Make a test case for each statement in the program,
 * independent of the others.
 *
 * Test must simply cause the statement to be
 * run, ignoring its actions and sub-statements (but still must check that
 * result of test is correct)
 *
 * Completion criterion: A test case for every statement
 */

/**
 * DECISION COVERAGE
 *
 * Causes every decision (if, switch, while, etc.) in the program
 * to be made both ways (or every possible way for switch).
 *
 * System: Design a test case to exercise each decision in the
 * program each way (true / false).
 * 
 * Completion criterion: A test case for each side of each decision.
 */

/**
 * LOOP COVERAGE
 * 
 * This method makes tests to exercise each loop in the program
 * in four different states :
 * - execute body zero times (do not enter loop)
 * - execute body once (i.e., do not repeat)
 * - execute body twice (i.e., repeat once)
 * - execute body many times
 * 
 * Usually used as an enhancement of a statement, block,
 * decision or condition coverage method
 *
 * System: Devise test cases to exercise each loop with zero, one,
 * two and many repetitions
 *
 * Completion criterion: A test for each of these cases for each loop
 */
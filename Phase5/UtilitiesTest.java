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
import org.junit.Test;

// Taken from Stack example
public class UtilitiesTest {
    
    @Test
    public void storeFileTest1() {
    	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String expectedOutput = "ERROR <internal>: Could not read in file.";
    	Utilities.storeFile("test.txt", transactionFile);

    	assertEquals(outContent.toString(), expectedOutput);

    }
    
    @Test
    public void isAdmintest1() {

    	assertEquals(false, Utilities.isAdmin('N'));
	}

    @Test
    public void isAdmintest2() {

    	assertEquals(true, Utilities.isAdmin(' '));
	}

    @Test
    public void isAdmintest3() {

    	assertEquals(false, Utilities.isAdmin('R'));
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
 * capable of executing correctly
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
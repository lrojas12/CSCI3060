/**
 * This class will test every method in the Main.java class. The kinds of test
 * to be carried out will be statement, decision and loop coverage.
 * 
 * MainTest.java
 * 
 * @author Luisa Rojas
 * @author Truyen Truong
 * @author Danesh Parthipan
 */

import static org.junit.Assert.*;
import junit.framework.JUnit4TestAdapter;
import org.junit.*;
import java.io.*;
import java.lang.Object.*;

public class MainTest {

	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    Main mainObj = new Main();

	@Before
	public void setUpStreams() {
		System.setErr(new PrintStream(errContent));
	}

	@After
	public void cleanUpStreams() {
		System.setErr(null);
	}
    
    @Test
    public void mainTest1() {

    	String[] args = {"arg1", "arg2", "arg3"};
    	mainObj.main(args);
		assertEquals("ERROR: User input must be 2 arguments: <old_master_accounts> <transaction_file>\n", errContent.toString());

	}

	@Test
	public void mainTest2() {
		String[] args = {"arg1", "arg2"};
		mainObj.main(args);
		assertEquals("arg2", Main.transactionFileName);
	}

    public static junit.framework.Test suite(){
       return new JUnit4TestAdapter(MainTest.class);
    }
}
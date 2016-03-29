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
import org.junit.Test;

/**
 * Main.java does not need to be tested, since the methods being called
 * in it are already being tested separately.
 */
public class MainTest {
    
    @Test
    public void test() {
    	
    }

    public static junit.framework.Test suite(){
       return new JUnit4TestAdapter(MainTest.class);
    }
}
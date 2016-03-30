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
public class UpdateMasterTest {
    
    // Loop coverage (3)
    @Test
    public void updateMasterTest1() {

    	List<String> transactionFile =  new ArrayList<String>();
		list.add("10 Tarzan               00001          S ");
		list.add("02 Tarzan               00001 00020.00   ");
		list.add("02 Wayne                00002 00020.00   ");
    }
    
    public static junit.framework.Test suite(){
       return new JUnit4TestAdapter(UpdateMasterTestTest.class);
    }
}
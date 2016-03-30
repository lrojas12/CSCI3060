/**
 * This class will test the loop coverage using an for. The method
 * contained in it will allow said loop to 
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
public class UpdateMasterTestLoop0 {
    
    // Loop coverage (0)
    @Test
    public void updateMasterTest() {

        Main mainObj = new Main();

    	List<String> mainObj.transactionFile =  new ArrayList<String>();
    }

    public static junit.framework.Test suite(){
       return new JUnit4TestAdapter(UpdateMasterTestTest.class);
    }
}
/**
 * This class will test every method in the UserTest.java class. The kinds of test
 * to be carried out will be statement, decision and loop coverage.
 * 
 * UserTest.java
 * 
 * @author Luisa Rojas
 * @author Truyen Truong
 * @author Danesh Parthipan
 */

import static org.junit.Assert.*;
import junit.framework.JUnit4TestAdapter;
import org.junit.Test;

public class UserTest {
    
    @Test
    public void userConstructorTest1() {
      User testUser = new User('A');

      assertEquals('A', testUser.getPlan());
  }

  @Test
    public void userConstructorTest2() {
      User testUser = new User("Tarzan", 1, (float)1000.00, 'A', 0, 'N');

      assertEquals("Tarzan", testUser.getName());
      assertEquals(1, testUser.getNum());
      assertEquals(1000.00, testUser.getBalance(), 0.02);
      assertEquals('A', testUser.getStatus());
      assertEquals('N', testUser.getPlan());
  }
    
    public static junit.framework.Test suite(){
       return new JUnit4TestAdapter(UserTest.class);
    }
}
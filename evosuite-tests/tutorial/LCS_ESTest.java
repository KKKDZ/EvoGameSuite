/*
 * This file was automatically generated by EvoSuite
 * Wed Apr 26 08:43:40 GMT 2023
 */

package tutorial;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.util.NoSuchElementException;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.util.SystemInUtil;
import org.junit.runner.RunWith;
import tutorial.LCS;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class LCS_ESTest extends LCS_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      String string0 = LCS.findLengthOfLCS("no:,TK-H,R0L%kbk%", "no:,TK-H,R0L%kbk%", 10, 10);
      assertEquals("no:,TK-H,R\u0000", string0);
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      // Undeclared exception!
      try { 
        LCS.findLengthOfLCS("_54v", "", 60, 60);
        fail("Expecting exception: StringIndexOutOfBoundsException");
      
      } catch(StringIndexOutOfBoundsException e) {
      }
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      // Undeclared exception!
      try { 
        LCS.findLengthOfLCS("r=E", (String) null, 1817, 2155);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("tutorial.LCS", e);
      }
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      // Undeclared exception!
      try { 
        LCS.findLengthOfLCS("~[SaIJJj<", "~[SaIJJj<", (-161), 1916);
        fail("Expecting exception: NegativeArraySizeException");
      
      } catch(NegativeArraySizeException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("tutorial.LCS", e);
      }
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      // Undeclared exception!
      try { 
        LCS.findLengthOfLCS("%eh5pL}Q5", "", 0, (-1));
        fail("Expecting exception: ArrayIndexOutOfBoundsException");
      
      } catch(ArrayIndexOutOfBoundsException e) {
         //
         // -1
         //
         verifyException("tutorial.LCS", e);
      }
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      String string0 = LCS.findLengthOfLCS("*%!_owy", "+\"N |Ucd3,r|Mo", 3, 3);
      assertEquals("\u0000", string0);
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      LCS.findLengthOfLCS((String) null, (String) null, 3320, 0);
      // Undeclared exception!
      LCS.findLengthOfLCS("", "ndQ}D>)_3~rT", 3320, 0);
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      String string0 = LCS.findLengthOfLCS("\nSequence2: ", "\nSequence2: ", 5, 1);
      assertEquals("\n\u0000", string0);
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      String[] stringArray0 = new String[6];
      SystemInUtil.addInputLine("JB^hS");
      SystemInUtil.addInputLine("G'-:a%R");
      LCS.main(stringArray0);
      assertEquals(6, stringArray0.length);
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      String[] stringArray0 = new String[5];
      // Undeclared exception!
      try { 
        LCS.main(stringArray0);
        fail("Expecting exception: NoSuchElementException");
      
      } catch(NoSuchElementException e) {
         //
         // No line found
         //
         verifyException("java.util.Scanner", e);
      }
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      LCS lCS0 = new LCS();
  }
}

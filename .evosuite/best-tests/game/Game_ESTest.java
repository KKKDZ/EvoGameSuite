/*
 * This file was automatically generated by EvoSuite
 * Tue Apr 25 15:24:05 GMT 2023
 */

package game;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import game.Game;
import java.util.List;
import java.util.NoSuchElementException;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.util.SystemInUtil;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class Game_ESTest extends Game_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      SystemInUtil.addInputLine("2");
      // Undeclared exception!
      try { 
        Game.main((String[]) null);
        fail("Expecting exception: NoSuchElementException");
      
      } catch(NoSuchElementException e) {
         //
         // No line found
         //
         verifyException("java.util.Scanner", e);
      }
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      SystemInUtil.addInputLine("1");
      String[] stringArray0 = new String[7];
      SystemInUtil.addInputLine("");
      // Undeclared exception!
      try { 
        Game.main(stringArray0);
        fail("Expecting exception: NoSuchElementException");
      
      } catch(NoSuchElementException e) {
         //
         // No line found
         //
         verifyException("java.util.Scanner", e);
      }
  }

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      String[] stringArray0 = new String[6];
      SystemInUtil.addInputLine("Ggw");
      // Undeclared exception!
      try { 
        Game.main(stringArray0);
        fail("Expecting exception: NoSuchElementException");
      
      } catch(NoSuchElementException e) {
         //
         // No line found
         //
         verifyException("java.util.Scanner", e);
      }
  }

  @Test(timeout = 4000)
  public void test3()  throws Throwable  {
      List<String> list0 = Game.listClassesInTutorialFolder();
      assertEquals(2, list0.size());
  }

  @Test(timeout = 4000)
  public void test4()  throws Throwable  {
      SystemInUtil.addInputLine("4");
      // Undeclared exception!
      try { 
        Game.main((String[]) null);
        fail("Expecting exception: NoSuchElementException");
      
      } catch(NoSuchElementException e) {
         //
         // No line found
         //
         verifyException("java.util.Scanner", e);
      }
  }

  @Test(timeout = 4000)
  public void test5()  throws Throwable  {
      SystemInUtil.addInputLine("0");
      // Undeclared exception!
      try { 
        Game.main((String[]) null);
        fail("Expecting exception: NoSuchElementException");
      
      } catch(NoSuchElementException e) {
         //
         // No line found
         //
         verifyException("java.util.Scanner", e);
      }
  }

  @Test(timeout = 4000)
  public void test6()  throws Throwable  {
      Game game0 = new Game();
  }
}

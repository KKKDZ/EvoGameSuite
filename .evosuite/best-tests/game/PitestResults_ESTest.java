/*
 * This file was automatically generated by EvoSuite
 * Tue Apr 25 15:28:38 GMT 2023
 */

package game;

import org.junit.Test;
import static org.junit.Assert.*;
import game.PitestResults;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class PitestResults_ESTest extends PitestResults_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      PitestResults pitestResults0 = new PitestResults();
      pitestResults0.setTotalMutants((-1559));
      int int0 = pitestResults0.getTotalMutants();
      assertEquals((-1559), int0);
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      PitestResults pitestResults0 = new PitestResults();
      pitestResults0.setSurvivedMutants(2202);
      int int0 = pitestResults0.getSurvivedMutants();
      assertEquals(2202, int0);
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      PitestResults pitestResults0 = new PitestResults();
      pitestResults0.setMutationScore((-3127.786482929));
      double double0 = pitestResults0.getMutationScore();
      assertEquals((-3127.786482929), double0, 0.01);
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      PitestResults pitestResults0 = new PitestResults();
      pitestResults0.setKilledMutants(1);
      int int0 = pitestResults0.getKilledMutants();
      assertEquals(1, int0);
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      PitestResults pitestResults0 = new PitestResults();
      pitestResults0.setSurvivedMutants((-1559));
      int int0 = pitestResults0.getSurvivedMutants();
      assertEquals((-1559), int0);
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      PitestResults pitestResults0 = new PitestResults();
      pitestResults0.setMutationScore(1.0);
      double double0 = pitestResults0.getMutationScore();
      assertEquals(1.0, double0, 0.01);
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      PitestResults pitestResults0 = new PitestResults();
      pitestResults0.setKilledMutants((-1));
      int int0 = pitestResults0.getKilledMutants();
      assertEquals((-1), int0);
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      PitestResults pitestResults0 = new PitestResults();
      pitestResults0.setTotalMutants(2401);
      int int0 = pitestResults0.getTotalMutants();
      assertEquals(2401, int0);
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      PitestResults pitestResults0 = new PitestResults();
      double double0 = pitestResults0.getMutationScore();
      assertEquals(0.0, double0, 0.01);
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      PitestResults pitestResults0 = new PitestResults();
      int int0 = pitestResults0.getSurvivedMutants();
      assertEquals(0, int0);
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      PitestResults pitestResults0 = new PitestResults();
      int int0 = pitestResults0.getKilledMutants();
      assertEquals(0, int0);
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      PitestResults pitestResults0 = new PitestResults();
      int int0 = pitestResults0.getTotalMutants();
      assertEquals(0, int0);
  }
}

package examples_tests.user_tests;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.util.EmptyStackException;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;
import examples.Stack;
import examples_tests.evosuite_scaffolding.*;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class Stack_userMade extends Stack_ESTest_scaffolding {

    @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      Stack<String> stack0 = new Stack<String>();
      assertTrue(stack0.isEmpty());
      
      stack0.push("");
      stack0.push((String) null);
      stack0.pop();
      assertFalse(stack0.isEmpty());
  }

}
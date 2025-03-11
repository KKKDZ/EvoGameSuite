package examples_tests.user_tests;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;

import java.util.Arrays;
import java.util.EmptyStackException;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;
import examples.Stack;
import examples_tests.evosuite_scaffolding.*;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, 
useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class Travelling_Salesman_userMade extends Travelling_Salesman_ESTest_scaffolding {

}

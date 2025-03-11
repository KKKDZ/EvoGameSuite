package game.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;

@Service
public class TestSuiteService {

    // Restituisce il template fisso per la test suite utente della classe data.
    public String getTestSuiteTemplate(String selectedClass) {
        return "package examples_tests.user_tests;\n"
                + "import org.junit.Test;\n"
                + "import static org.junit.Assert.*;\n"
                + "import static org.evosuite.runtime.EvoAssertions.*;\n"
                + "import java.util.EmptyStackException;\n"
                + "import org.evosuite.runtime.EvoRunner;\n"
                + "import org.evosuite.runtime.EvoRunnerParameters;\n"
                + "import org.junit.runner.RunWith;\n"
                + "import examples.Stack;\n\n"
                + "import examples_tests.evosuite_scaffolding.*;\n\n"
                + "@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)\n"
                + "public class " + selectedClass + "_userMade extends " + selectedClass + "_ESTest_scaffolding {\n\n"
                + "    // Write your test cases here...\n\n";
    }

    // Salva la test suite dell'utente combinando il template fisso e i test inseriti dall'utente.
    public void saveUserTestSuite(String selectedClass, String userTests) throws IOException {
        String template = getTestSuiteTemplate(selectedClass);
        String fullContent = template + "\n\n" + userTests+ "\n\n}";
        String targetDir = "src/test/java/examples_tests/user_tests";
        Path dirPath = Paths.get(targetDir);
        if (!Files.exists(dirPath)) {
            Files.createDirectories(dirPath);
        }
        String filename = selectedClass + "_userMade.java";
        Path filePath = Paths.get(targetDir, filename);
        Files.write(filePath, fullContent.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    // Resetta il file della test suite utente riportandolo al template base, per permetterne il riutilizzo.
    public void resetUserTestSuite(String selectedClass) throws IOException {
        String targetDir = "src/test/java/examples_tests/user_tests";
        Path dirPath = Paths.get(targetDir);
        if (!Files.exists(dirPath)) {
            Files.createDirectories(dirPath);
        }
        String filename = selectedClass + "_userMade.java";
        Path filePath = Paths.get(targetDir, filename);
        String template = getTestSuiteTemplate(selectedClass);
        Files.write(filePath, template.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
}

package game;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EvoSuiteRunner {

    public static ProcessBuilder createEvosuiteProcessBuilder(int difficultyLevel, String selectedClass, String manualConfigString) {
        ProcessBuilder evosuiteProcessBuilder;
        // Usa il package "examples" per le classi da testare
        switch (difficultyLevel) {
            case 1:
                evosuiteProcessBuilder = new ProcessBuilder("java", "-jar", "evosuite-1.0.6.jar", "-class", "examples." + selectedClass, "-projectCP", "target/classes");
                break;
            case 2:
                evosuiteProcessBuilder = new ProcessBuilder("java", "-jar", "evosuite-1.0.6.jar", "-class", "examples." + selectedClass, "-projectCP", "target/classes", "-Dassertion_strategy=ALL");
                break;
            case 3:
                evosuiteProcessBuilder = new ProcessBuilder("java", "-jar", "evosuite-1.0.6.jar", "-class", "examples." + selectedClass, "-projectCP", "target/classes", "-Dsearch_budget=100", "-Dglobal_timeout=1000", "-Dcriterion=BRANCH:LINE");
                break;
            case 4:
                evosuiteProcessBuilder = new ProcessBuilder("java", "-jar", "evosuite-1.0.6.jar", "-class", "examples." + selectedClass, "-projectCP", "target/classes", "-Dsearch_budget=200", "-Dglobal_timeout=2000", "-Dalgorithm=STEADY_STATE_GA");
                break;
            case 5:
            case 6:
                List<String> command = new ArrayList<>(Arrays.asList("java", "-jar", "evosuite-1.0.6.jar", "-class", "examples." + selectedClass, "-projectCP", "target/classes"));
                String[] manualConfigArgs = manualConfigString.split("\\s+");
                Collections.addAll(command, manualConfigArgs);
                evosuiteProcessBuilder = new ProcessBuilder(command);
                break;
            default:
                evosuiteProcessBuilder = new ProcessBuilder("java", "-jar", "evosuite-1.0.6.jar", "-class", "examples." + selectedClass, "-projectCP", "target/classes", "-Dsearch_budget=100", "-Dglobal_timeout=1000", "-Dassertion_strategy=MUTATION", "-Dalgorithm=SPEA2", "-Dcriterion=METHODNOEXCEPTION", "-Dpopulation=100");
                break;
        }
        return evosuiteProcessBuilder;
    }

    // Esegue EvoSuite reindirizzando l'output in un log per poter visualizzare il progresso
    public static void runEvosuite(ProcessBuilder evosuiteProcessBuilder) throws IOException, InterruptedException {
        File logFile = new File("logs/evosuite.log");
        logFile.getParentFile().mkdirs();
        evosuiteProcessBuilder.redirectOutput(logFile);
        evosuiteProcessBuilder.redirectErrorStream(true);
        Process evosuiteProcess = evosuiteProcessBuilder.start();
        evosuiteProcess.waitFor();
    }

    public static void copyGeneratedEvosuiteTests(String selectedClass) throws IOException {
        String evosuiteTestFolder = "src/test/java/examples_tests/evosuite";
        File folder = new File(evosuiteTestFolder);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    
        // First file: main test (_ESTest)
        String evosuiteTestFilename = evosuiteTestFolder + "/" + selectedClass + "_ESTest.java";
        Path source = Paths.get("evosuite-tests/examples/" + selectedClass + "_ESTest.java");
        
        // Second file: scaffolding
        String evosuiteTestFilename2 = evosuiteTestFolder + "/" + selectedClass + "_ESTest_scaffolding.java";
        Path source2 = Paths.get("evosuite-tests/examples/" + selectedClass + "_ESTest_scaffolding.java");
    
        // Copy and modify first file
        if (Files.exists(source)) {
            Path target = Paths.get(evosuiteTestFilename);
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
            // Legge il contenuto, sostituisce la dichiarazione di package e riscrive il file
            String content = new String(Files.readAllBytes(target));
            // Sostituisce "package examples;" con "package examples_tests.evosuite;"
            content = content.replaceFirst("package\\s+examples\\s*;", "package examples_tests.evosuite;");
            Files.write(target, content.getBytes());
        } else {
            System.out.println("EvoSuite failed to generate test cases.");
            return;
        }
    
        // Copy and modify second file (scaffolding)
        if (Files.exists(source2)) {
            Path target2 = Paths.get(evosuiteTestFilename2);
            Files.copy(source2, target2, StandardCopyOption.REPLACE_EXISTING);
            String content2 = new String(Files.readAllBytes(target2));
            content2 = content2.replaceFirst("package\\s+examples\\s*;", "package examples_tests.evosuite;");
            Files.write(target2, content2.getBytes());
        } else {
            System.out.println("EvoSuite failed to generate test scaffolding.");
        }
    }
    
}

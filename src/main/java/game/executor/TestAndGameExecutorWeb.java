package game.executor;

import game.PitestResults;
import game.PitestResultsParser;
import game.dto.GameResult;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestAndGameExecutorWeb extends PitestResultsParser {

    public static void runTests(String selectedClass) throws IOException, InterruptedException {
        createReportDirectories();
        runTestsInternal(selectedClass);
    }

    private static void runTestsInternal(String selectedClass) throws IOException, InterruptedException {
        String mvnScript = getMvnScript();
        runMvnTest(mvnScript);
        runMutationCoverage(mvnScript, selectedClass);
        runEvoSuiteCoverage(selectedClass);
    }

    public static String getMvnScript() {
        String os = System.getProperty("os.name").toLowerCase();
        return os.contains("win") ? "./mvnw.cmd" : "./mvnw";
    }

    private static void createReportDirectories() throws IOException {
        Files.createDirectories(Paths.get("target/user-pit-reports"));
        Files.createDirectories(Paths.get("target/evosuite-pit-reports"));
        Files.createDirectories(Paths.get("target/user-evosuite-reports"));
        Files.createDirectories(Paths.get("target/evosuite-evosuite-reports"));
    }

    private static void runMvnTest(String mvnScript) throws IOException, InterruptedException {
        ProcessBuilder testRunner = new ProcessBuilder(mvnScript, "test");
        testRunner.inheritIO();
        Process runTests = testRunner.start();
        runTests.waitFor();
    }

    private static void runMutationCoverage(String mvnScript, String selectedClass) throws IOException, InterruptedException {
        // Esegui PIT per i test utente
        runProcess(mvnScript, "org.pitest:pitest-maven:mutationCoverage", selectedClass, "_userMade", "user-test");
        // Esegui PIT per i test EvoSuite
        runProcess(mvnScript, "org.pitest:pitest-maven:mutationCoverage", selectedClass, "_ESTest", "evosuite-test");
    }

    private static void runProcess(String mvnScript, String command, String selectedClass, String testNameSuffix, String profile)
        throws IOException, InterruptedException {
        List<String> commandList = new ArrayList<>();
        commandList.add(mvnScript);
        commandList.add(command);
        // Usa il package "examples" per la classe da testare
        commandList.add("-DtargetClasses=examples." + selectedClass);
        // Seleziona il package corretto per i test in base al profilo
        if ("user-test".equals(profile)) {
            commandList.add("-DtargetTests=examples_tests.user_tests." + selectedClass + testNameSuffix);
        } else if ("evosuite-test".equals(profile)) {
            commandList.add("-DtargetTests=examples_tests.evosuite." + selectedClass + testNameSuffix);
        }
        
        String outputDirectory = "";
        if (profile.equals("user-test")) {
            outputDirectory = "target/user-jacoco-reports";
        } else if (profile.equals("evosuite-test")) {
            outputDirectory = "target/evosuite-jacoco-reports";
        }
        commandList.add("-Djacoco.destfile=" + outputDirectory + "/jacoco-ut.exec");

        if (!profile.isEmpty()) {
            commandList.add("-P");
            commandList.add(profile);
        }

        ProcessBuilder processBuilder = new ProcessBuilder(commandList);
        processBuilder.inheritIO();
        Process process = processBuilder.start();
        process.waitFor();
    }


    private static void runEvoSuiteCoverage(String selectedClass) throws IOException, InterruptedException {
        deleteESStats();
    
        String projectRoot = System.getProperty("user.dir");
        String separator = System.getProperty("path.separator");
        String classPath = projectRoot + "/target/classes" + separator + projectRoot + "/target/test-classes";
    
        // Copertura per i test EvoSuite
        ProcessBuilder evosuiteRunner = new ProcessBuilder(
                "java", "-Djava.awt.headless=true", "-jar", "evosuite-1.0.6.jar",
                "-measureCoverage",
                "-class", "examples." + selectedClass,
                "-Djunit=examples_tests.evosuite." + selectedClass + "_ESTest",
                "-projectCP", classPath,
                "-Doutput_variables=TARGET_CLASS,criterion,Coverage,Size,Length"
        );
        evosuiteRunner.inheritIO();
        Process runEvoSuite = evosuiteRunner.start();
        runEvoSuite.waitFor();
    
        Path source = Paths.get("evosuite-report/statistics.csv");
        Path destination = Paths.get("target/evosuite-evosuite-reports/statistics.csv");
        Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
    
        deleteESStats();
    
        // Copertura per i test utente
        ProcessBuilder userRunner = new ProcessBuilder(
                "java", "-Djava.awt.headless=true", "-jar", "evosuite-1.0.6.jar",
                "-measureCoverage",
                "-class", "examples." + selectedClass,
                "-Djunit=examples_tests.user_tests." + selectedClass + "_userMade",
                "-projectCP", classPath,
                "-Doutput_variables=TARGET_CLASS,criterion,Coverage,Size,Length"
        );
        userRunner.inheritIO();
        Process runUserEvoSuite = userRunner.start();
        runUserEvoSuite.waitFor();
    
        Path userDestination = Paths.get("target/user-evosuite-reports/statistics.csv");
        Files.copy(source, userDestination, StandardCopyOption.REPLACE_EXISTING);
    }
        

    private static void deleteESStats() throws IOException {
        Path filePath = Paths.get("evosuite-report/statistics.csv");
        Files.deleteIfExists(filePath);
    }

    // Recupera il percorso dell'ultimo report PIT generato oppure restituisce una stringa vuota se non esiste
    private static String getLatestReportPath(String reportsFolderPath) {
        File reportsFolder = new File(reportsFolderPath);
        String[] reportDirectories = reportsFolder.list((current, name) -> new File(current, name).isDirectory());
        if (reportDirectories == null || reportDirectories.length == 0) {
            return "";
        }
        Arrays.sort(reportDirectories);
        String latestReportDirectory = reportDirectories[reportDirectories.length - 1];
        return reportsFolderPath + "/" + latestReportDirectory + "/mutations.xml";
    }

    // Legge la coverage dal file CSV generato da EvoSuite
    public static double parseCoverageFromCSV(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            return 0;
        }
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        double coverage = 0;
        while ((line = reader.readLine()) != null) {
            String[] values = line.split(",");
            if (values.length >= 3) {
                String coverageStr = values[2].trim();
                try {
                    coverage = Double.parseDouble(coverageStr);
                    break;
                } catch (NumberFormatException e) {
                    // Continua alla riga successiva
                }
            }
        }
        reader.close();
        return coverage;
    }

    // Elabora i report PIT ed EvoSuite e restituisce un GameResult dettagliato
    public static GameResult getResults(String selectedClass) throws IOException {
        String userTestReportPath = getLatestReportPath("target/user-pit-reports");
        String evosuiteTestReportPath = getLatestReportPath("target/evosuite-pit-reports");
    
        PitestResults userTestResults;
        PitestResults evosuiteTestResults;
        if (userTestReportPath.isEmpty()) {
            userTestResults = new PitestResults();
        } else {
            userTestResults = PitestResultsParser.parseResults(userTestReportPath);
        }
        if (evosuiteTestReportPath.isEmpty()) {
            evosuiteTestResults = new PitestResults();
        } else {
            evosuiteTestResults = PitestResultsParser.parseResults(evosuiteTestReportPath);
        }
    
        String userEvoSuiteReportPath = "target/user-evosuite-reports/statistics.csv";
        String evosuiteEvoSuiteReportPath = "target/evosuite-evosuite-reports/statistics.csv";
    
        double userCoverage = parseCoverageFromCSV(userEvoSuiteReportPath);
        double evosuiteCoverage = parseCoverageFromCSV(evosuiteEvoSuiteReportPath);
    
        // Moltiplichiamo per 100 per ottenere una percentuale
        userCoverage = userCoverage * 100;
        evosuiteCoverage = evosuiteCoverage * 100;
    
        String moreCovWin = (userCoverage < evosuiteCoverage) ? "EvoSuite" : "User";
    
        int totalSurUser = userTestResults.getSurvivedMutants() + userTestResults.getNoCoverageMutants();
        int userPoints = (userTestResults.getKilledMutants() * 10) - totalSurUser;
        int evosuitePoints = (evosuiteTestResults.getKilledMutants() * 10) - evosuiteTestResults.getSurvivedMutants();
    
        if (userTestResults.getKilledMutants() == 0 && userTestResults.getSurvivedMutants() == 0) {
            userPoints = 0;
        } else {
            if ("User".equals(moreCovWin)) {
                userPoints += 20;
            } else {
                evosuitePoints += 20;
            }
        }
    
        GameResult result = new GameResult();
        result.setUserPoints(userPoints);
        result.setEvosuitePoints(evosuitePoints);
        result.setWinner((evosuitePoints > userPoints) ? "EvoSuite" : (userPoints > evosuitePoints) ? "User" : "Draw");
    
        // Imposta informazioni dettagliate per la test suite utente
        result.setUserKilledMutants(userTestResults.getKilledMutants());
        result.setUserSurvivedMutants(userTestResults.getSurvivedMutants());
        result.setUserNoCoverageMutants(userTestResults.getNoCoverageMutants());
        result.setUserMutationScore(userTestResults.getMutationScore());
        result.setUserCoverage(userCoverage);
    
        // Imposta informazioni dettagliate per la test suite EvoSuite
        result.setEvosuiteKilledMutants(evosuiteTestResults.getKilledMutants());
        result.setEvosuiteSurvivedMutants(evosuiteTestResults.getSurvivedMutants());
        result.setEvosuiteNoCoverageMutants(evosuiteTestResults.getNoCoverageMutants());
        result.setEvosuiteMutationScore(evosuiteTestResults.getMutationScore());
        result.setEvosuiteCoverage(evosuiteCoverage);
    
        return result;
    }    
}

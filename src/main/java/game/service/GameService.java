package game.service;

import org.springframework.stereotype.Service;
import game.dto.ConfigParameters;
import game.dto.GameResult;

@Service
public class GameService {

    // Restituisce la lista delle classi disponibili nella cartella "examples"
    public java.util.List<String> listAvailableClasses() {
        java.util.List<String> classNames = new java.util.ArrayList<>();
        java.io.File folder = new java.io.File("src/main/java/examples");
        if (!folder.exists()) {
            folder = new java.io.File("examples");
        }
        java.io.File[] files = folder.listFiles((dir, name) -> name.endsWith(".java") || name.endsWith(".class"));
        if (files != null) {
            for (java.io.File file : files) {
                String className = file.getName().replace(".java", "").replace(".class", "");
                classNames.add(className);
            }
        }
        return classNames;
    }

    // Costruisce la stringa di opzioni per la configurazione manuale
    private String buildEvoSuiteOptions(ConfigParameters config) {
        StringBuilder sb = new StringBuilder();
        if (config.getAlgorithm() != null && !config.getAlgorithm().isEmpty()) {
            sb.append("-Dalgorithm=").append(config.getAlgorithm()).append(" ");
        }
        if (config.getTestCriterion() != null && !config.getTestCriterion().isEmpty()) {
            sb.append("-Dcriterion=").append(config.getTestCriterion()).append(" ");
        }
        if (config.getGenerationStrategy() != null && !config.getGenerationStrategy().isEmpty()) {
            sb.append("-Dstrategy=").append(config.getGenerationStrategy()).append(" ");
        }
        if (config.getSearchBudget() != null && config.getSearchBudget() > 0) {
            sb.append("-Dsearch_budget=").append(config.getSearchBudget()).append(" ");
        }
        if (config.getGlobalTimeout() != null && config.getGlobalTimeout() > 0) {
            sb.append("-Dglobal_timeout=").append(config.getGlobalTimeout()).append(" ");
        }
        return sb.toString().trim();
    }

    // Esegue l'intero flusso di test basandosi sulla configurazione (predefined o manual)
    public GameResult runGame(ConfigParameters config) {
        GameResult result = new GameResult();
        try {
            String selectedClass = config.getSelectedClass();
            int difficultyLevel;
            String manualConfigString;
            if ("predefined".equalsIgnoreCase(config.getConfigurationMethod())) {
                // Mappa la scelta predefinita a un livello di difficoltà
                String option = config.getPredefinedConfigOption();
                if ("Easy".equalsIgnoreCase(option)) {
                    difficultyLevel = 1;
                } else if ("Standard".equalsIgnoreCase(option)) {
                    difficultyLevel = 2;
                } else if ("Intermediate".equalsIgnoreCase(option)) {
                    difficultyLevel = 3;
                } else if ("Hard".equalsIgnoreCase(option)) {
                    difficultyLevel = 4;
                } else if ("Detailed".equalsIgnoreCase(option)) {
                    difficultyLevel = 5;
                } else {
                    difficultyLevel = 1; // fallback
                }
                manualConfigString = "";
            } else {
                // Modalità manuale: usiamo un livello fisso (6) e costruiamo la stringa di opzioni
                difficultyLevel = 6;
                manualConfigString = buildEvoSuiteOptions(config);
            }

            // Avvia EvoSuite e copia i test generati (usando il package "examples")
            ProcessBuilder evosuitePB = game.EvoSuiteRunner.createEvosuiteProcessBuilder(difficultyLevel, selectedClass, manualConfigString);
            game.EvoSuiteRunner.runEvosuite(evosuitePB);
            game.EvoSuiteRunner.copyGeneratedEvosuiteTests(selectedClass);

            // Esegue l'intero flusso di test (utilizzando TestAndGameExecutorWeb)
            game.executor.TestAndGameExecutorWeb.runTests(selectedClass);

            // Recupera e restituisce i risultati
            result = game.executor.TestAndGameExecutorWeb.getResults(selectedClass);
        } catch (Exception e) {
            e.printStackTrace();
            result.setWinner("Errore: " + e.getMessage());
        }
        return result;
    }
}

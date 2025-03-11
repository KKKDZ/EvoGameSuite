package game.dto;

public class ConfigParameters {
    private String selectedClass;
    private String configurationMethod; // "predefined" o "manual"
    
    // Per configurazione predefinita: useremo una stringa (es. "Easy", "Standard", etc.)
    private String predefinedConfigOption;
    
    // Per configurazione manuale
    private String algorithm;
    private String testCriterion;
    private String generationStrategy;
    private Integer searchBudget;
    private Integer globalTimeout;

    // Getters e setters
    public String getSelectedClass() {
        return selectedClass;
    }
    public void setSelectedClass(String selectedClass) {
        this.selectedClass = selectedClass;
    }
    public String getConfigurationMethod() {
        return configurationMethod;
    }
    public void setConfigurationMethod(String configurationMethod) {
        this.configurationMethod = configurationMethod;
    }
    public String getPredefinedConfigOption() {
        return predefinedConfigOption;
    }
    public void setPredefinedConfigOption(String predefinedConfigOption) {
        this.predefinedConfigOption = predefinedConfigOption;
    }
    public String getAlgorithm() {
        return algorithm;
    }
    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }
    public String getTestCriterion() {
        return testCriterion;
    }
    public void setTestCriterion(String testCriterion) {
        this.testCriterion = testCriterion;
    }
    public String getGenerationStrategy() {
        return generationStrategy;
    }
    public void setGenerationStrategy(String generationStrategy) {
        this.generationStrategy = generationStrategy;
    }
    public Integer getSearchBudget() {
        return searchBudget;
    }
    public void setSearchBudget(Integer searchBudget) {
        this.searchBudget = searchBudget;
    }
    public Integer getGlobalTimeout() {
        return globalTimeout;
    }
    public void setGlobalTimeout(Integer globalTimeout) {
        this.globalTimeout = globalTimeout;
    }
}

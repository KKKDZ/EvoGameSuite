package game.dto;

public class GameResult {
    private int userPoints;
    private int evosuitePoints;
    private String winner;
    
    // Dettagli per la test suite utente
    private int userKilledMutants;
    private int userSurvivedMutants;
    private int userNoCoverageMutants;
    private double userMutationScore;
    private double userCoverage;
    
    // Dettagli per la test suite EvoSuite
    private int evosuiteKilledMutants;
    private int evosuiteSurvivedMutants;
    private int evosuiteNoCoverageMutants;
    private double evosuiteMutationScore;
    private double evosuiteCoverage;

    // Getters e setters per punteggi e vincitore
    public int getUserPoints() {
        return userPoints;
    }
    public void setUserPoints(int userPoints) {
        this.userPoints = userPoints;
    }
    public int getEvosuitePoints() {
        return evosuitePoints;
    }
    public void setEvosuitePoints(int evosuitePoints) {
        this.evosuitePoints = evosuitePoints;
    }
    public String getWinner() {
        return winner;
    }
    public void setWinner(String winner) {
        this.winner = winner;
    }
    
    // Getters e setters per la test suite utente
    public int getUserKilledMutants() {
        return userKilledMutants;
    }
    public void setUserKilledMutants(int userKilledMutants) {
        this.userKilledMutants = userKilledMutants;
    }
    public int getUserSurvivedMutants() {
        return userSurvivedMutants;
    }
    public void setUserSurvivedMutants(int userSurvivedMutants) {
        this.userSurvivedMutants = userSurvivedMutants;
    }
    public int getUserNoCoverageMutants() {
        return userNoCoverageMutants;
    }
    public void setUserNoCoverageMutants(int userNoCoverageMutants) {
        this.userNoCoverageMutants = userNoCoverageMutants;
    }
    public double getUserMutationScore() {
        return userMutationScore;
    }
    public void setUserMutationScore(double userMutationScore) {
        this.userMutationScore = userMutationScore;
    }
    public double getUserCoverage() {
        return userCoverage;
    }
    public void setUserCoverage(double userCoverage) {
        this.userCoverage = userCoverage;
    }
    
    // Getters e setters per la test suite EvoSuite
    public int getEvosuiteKilledMutants() {
        return evosuiteKilledMutants;
    }
    public void setEvosuiteKilledMutants(int evosuiteKilledMutants) {
        this.evosuiteKilledMutants = evosuiteKilledMutants;
    }
    public int getEvosuiteSurvivedMutants() {
        return evosuiteSurvivedMutants;
    }
    public void setEvosuiteSurvivedMutants(int evosuiteSurvivedMutants) {
        this.evosuiteSurvivedMutants = evosuiteSurvivedMutants;
    }
    public int getEvosuiteNoCoverageMutants() {
        return evosuiteNoCoverageMutants;
    }
    public void setEvosuiteNoCoverageMutants(int evosuiteNoCoverageMutants) {
        this.evosuiteNoCoverageMutants = evosuiteNoCoverageMutants;
    }
    public double getEvosuiteMutationScore() {
        return evosuiteMutationScore;
    }
    public void setEvosuiteMutationScore(double evosuiteMutationScore) {
        this.evosuiteMutationScore = evosuiteMutationScore;
    }
    public double getEvosuiteCoverage() {
        return evosuiteCoverage;
    }
    public void setEvosuiteCoverage(double evosuiteCoverage) {
        this.evosuiteCoverage = evosuiteCoverage;
    }
}

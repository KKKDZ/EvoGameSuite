package game;

// This class will store and provide access to the PIT mutation coverage results in a structured way
public class PitestResults {
    private int totalMutants;
    private int killedMutants;
    private int survivedMutants;
    private int noCoverageMutants;
    private double mutationScore;

    // Getters and setters
    public int getTotalMutants() {
        return totalMutants;
    }

    public void setTotalMutants(int totalMutants) {
        this.totalMutants = totalMutants;
    }

    public int getKilledMutants() {
        return killedMutants;
    }

    public void setKilledMutants(int killedMutants) {
        this.killedMutants = killedMutants;
    }

    public int getSurvivedMutants() {
        return survivedMutants;
    }

    public void setSurvivedMutants(int survivedMutants) {
        this.survivedMutants = survivedMutants;
    }

    // noCoverageMutants field
    public int getNoCoverageMutants() {
        return noCoverageMutants;
    }

    public void setNoCoverageMutants(int noCoverageMutants) {
        this.noCoverageMutants = noCoverageMutants;
    }

    public double getMutationScore() {
        return mutationScore;
    }

    public void setMutationScore(double mutationScore) {
        this.mutationScore = mutationScore;
    }
}

package game;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "test_results")
public class TestResult {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String username;
    private int killedMutants;
    private int survivedMutants;
    private double mutationScore;
    private LocalDateTime testDate;

    public TestResult() {}

    public TestResult(String username, int killedMutants, int survivedMutants, double mutationScore, LocalDateTime testDate) {
        this.username = username;
        this.killedMutants = killedMutants;
        this.survivedMutants = survivedMutants;
        this.mutationScore = mutationScore;
        this.testDate = testDate;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public int getKilledMutants() { return killedMutants; }
    public void setKilledMutants(int killedMutants) { this.killedMutants = killedMutants; }

    public int getSurvivedMutants() { return survivedMutants; }
    public void setSurvivedMutants(int survivedMutants) { this.survivedMutants = survivedMutants; }

    public double getMutationScore() { return mutationScore; }
    public void setMutationScore(double mutationScore) { this.mutationScore = mutationScore; }

    public LocalDateTime getTestDate() { return testDate; }
    public void setTestDate(LocalDateTime testDate) { this.testDate = testDate; }
}

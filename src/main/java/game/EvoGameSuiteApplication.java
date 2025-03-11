package game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EvoGameSuiteApplication {
    public static void main(String[] args) {
        SpringApplication.run(EvoGameSuiteApplication.class, args);
        System.out.println("✅ Spring Boot è attivo su http://localhost:8080");
    }
}

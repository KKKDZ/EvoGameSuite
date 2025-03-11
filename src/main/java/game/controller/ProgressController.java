package game.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class ProgressController {

    @GetMapping("/game/progress")
    public String getProgress() {
        try {
            return new String(Files.readAllBytes(Paths.get("logs/evosuite.log")));
        } catch (Exception e) {
            return "Nessun progresso disponibile.";
        }
    }
}

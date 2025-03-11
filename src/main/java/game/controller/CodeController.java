package game.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
public class CodeController {

    @GetMapping("/game/code")
    public String showCode(@RequestParam("className") String className, Model model) {
        try {
            // Costruisci il percorso del file sorgente
            String filePath = "src/main/java/examples/" + className + ".java";
            String code = new String(Files.readAllBytes(Paths.get(filePath)));
            model.addAttribute("code", code);
        } catch (Exception e) {
            model.addAttribute("code", "Errore nel leggere il file: " + e.getMessage());
        }
        return "codeView"; // Visualizza il template codeView.html
    }
}

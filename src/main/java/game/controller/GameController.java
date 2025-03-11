package game.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import game.dto.ConfigParameters;
import game.service.GameService;

import java.util.List;

@Controller
@RequestMapping("/game")
@SessionAttributes("config")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    // Mostra la pagina di configurazione (start.html)
    @GetMapping("/start")
    public String start(Model model) {
        List<String> availableClasses = gameService.listAvailableClasses();
        model.addAttribute("availableClasses", availableClasses);
        if (!model.containsAttribute("config")) {
            model.addAttribute("config", new ConfigParameters());
        }
        return "start";
    }

    // Riceve la configurazione e reindirizza alla pagina per la scrittura della test suite utente
    @PostMapping("/run")
    public String configure(@ModelAttribute("config") ConfigParameters config, Model model) {
        return "redirect:/game/testsuite/edit?className=" + config.getSelectedClass();
    }
}

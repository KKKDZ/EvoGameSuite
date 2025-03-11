package game.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import game.dto.ConfigParameters;
import game.dto.GameResult;
import game.service.GameService;
import game.service.TestSuiteService;

@Controller
@RequestMapping("/game/testsuite")
@SessionAttributes("config")
public class TestSuiteController {

    private final TestSuiteService testSuiteService;
    private final GameService gameService;

    public TestSuiteController(TestSuiteService testSuiteService, GameService gameService) {
        this.testSuiteService = testSuiteService;
        this.gameService = gameService;
    }

    // Mostra la pagina per l'editing della test suite utente
    @GetMapping("/edit")
    public String editTestSuite(@RequestParam("className") String className, Model model) {
        String template = testSuiteService.getTestSuiteTemplate(className);
        model.addAttribute("className", className);
        model.addAttribute("template", template);
        return "editTestSuite";
    }

    // Salva la test suite utente (non esegue ancora i test) e reindirizza alla pagina di conferma
    @PostMapping("/save")
    public String saveTestSuite(@RequestParam("className") String className,
                                @RequestParam("userTests") String userTests,
                                @ModelAttribute("config") ConfigParameters config,
                                Model model) {
        try {
            testSuiteService.saveUserTestSuite(className, userTests);
            return "redirect:/game/testsuite/confirm?className=" + className;
        } catch (Exception e) {
            model.addAttribute("message", "Errore nel salvataggio: " + e.getMessage());
            return "redirect:/game/start";
        }
    }

    // Mostra la pagina di conferma in cui l'utente può avviare l'esecuzione dei test
    @GetMapping("/confirm")
    public String confirmExecution(@RequestParam("className") String className, Model model) {
        model.addAttribute("className", className);
        model.addAttribute("message", "La test suite utente è stata salvata. Clicca sul pulsante qui sotto per avviare l'esecuzione dei test.");
        return "confirmExecution";
    }

    // Avvia l'esecuzione dei test e reindirizza alla pagina dei risultati
    @PostMapping("/execute")
    public String executeTests(@RequestParam("className") String className,
                               @ModelAttribute("config") ConfigParameters config,
                               Model model) {
        try {
            GameResult result = gameService.runGame(config);
            // Dopo l'esecuzione, resetta la test suite utente per renderla riutilizzabile
            testSuiteService.resetUserTestSuite(className);
            model.addAttribute("result", result);
            return "result";
        } catch (Exception e) {
            model.addAttribute("message", "Errore durante l'esecuzione: " + e.getMessage());
            return "redirect:/game/start";
        }
    }
}

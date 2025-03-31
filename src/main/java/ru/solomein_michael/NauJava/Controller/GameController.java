package ru.solomein_michael.NauJava.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.solomein_michael.NauJava.Game.Game;
import ru.solomein_michael.NauJava.Repository.GameRepository;

import java.util.List;


@Controller
@RequestMapping("/games")
public class GameController {
    @Autowired
    private GameRepository gameRepository;

    @GetMapping("/view")
    public String getGames(Model model) {
        List<Game> games = gameRepository.findAll();
        model.addAttribute("games", games);
        return "gameList";
    }

    @GetMapping("/findAllByGameId")
    public List<Game> findAllByGameId(@RequestParam String gameId) {
        return gameRepository.findAllByGameId(gameId);
    }

    @GetMapping("/findLastByGameId")
    public ResponseEntity<Game> findLastByGameId(@RequestParam String gameId) {
        Game game = gameRepository.findFirstByGameId(gameId);
        if (game == null) {
            throw new RuntimeException("Game not found with gameId=" + gameId);
        }
        return ResponseEntity.ok(game);
    }
}

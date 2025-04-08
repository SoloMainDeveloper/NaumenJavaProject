package ru.solomein_michael.NauJava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.solomein_michael.NauJava.service.GameService;
import ru.solomein_michael.NauJava.dto.GameDto;

import java.util.List;


@Controller
@RequestMapping("/games")
public class GameController {
    @Autowired
    private GameService gameService;

    @GetMapping("/view")
    public String getGames(Model model) {
        List<GameDto> games = gameService.findAll().stream().map(GameDto::new).toList();
        model.addAttribute("games", games);
        return "gameList";
    }

    @GetMapping("/findAllByGameId")
    public ResponseEntity<List<GameDto>> findAllByGameId(@RequestParam String gameId) {
        if(gameId == null || gameId.isEmpty()) {
            return ResponseEntity.badRequest().body(null); // Возвращаем 400 Bad Request, если gameId недействителен
        }

        List<GameDto> gamesDto = gameService.findAllByGameId(gameId).stream()
                .map(GameDto::new)
                .toList();

        if(gamesDto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(gamesDto);
    }

    @GetMapping("/findLastByGameId")
    public ResponseEntity<GameDto> findLastByGameId(@RequestParam String gameId) {
        GameDto gameDto = new GameDto(gameService.findFirstByGameId(gameId));
        return ResponseEntity.ok(gameDto);
    }
}

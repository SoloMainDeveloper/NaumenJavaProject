package ru.solomein_michael.NauJava.InpOut;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.solomein_michael.NauJava.Game.Game;
import ru.solomein_michael.NauJava.Service.GameServiceImpl;

@Component
public class CommandProcessor {
    private final GameServiceImpl gameService;
    private Game currentGame;
    private boolean isGameStarted;

    @Autowired
    public CommandProcessor(GameServiceImpl gameService) {
        this.gameService = gameService;
        this.isGameStarted = false;
    }

    public boolean isGameStarted(){
        return isGameStarted;
    }

    public void execute(String input) {
        String[] cmd = input.split(" ");
        try {
            if(isGameStarted){
                executeGameMode(cmd);
            } else {
                executeDefaultMode(cmd);
            }

        } catch (RuntimeException e) {
            System.out.println("Аргументы введены некорректно");
        }
    }

    private void executeDefaultMode(String[] cmd){
        switch (cmd[0]) {
            case "create" -> {
                gameService.createGame(Long.valueOf(cmd[1]), cmd[2]);
                System.out.println("Игра для пользователя " + cmd[2] + " успешна создана...");
            }
            case "get" -> {
                var game = gameService.findById(Long.valueOf(cmd[1]));
                System.out.println("Игра: id=" + game.getId() + ", имя игрока=" + game.getPlayer().getName());
            }
            case "start" -> {
                currentGame = gameService.findById(Long.valueOf(cmd[1]));
                isGameStarted = true;
                System.out.println("Привет, " + currentGame.getPlayer().getName() + "! Игра (id:" + currentGame.getId() + ") началась!");
            }
            case "delete" -> {
                var id = Long.valueOf(cmd[1]);
                gameService.deleteById(id);
                System.out.println("Игра с id= " + id + " была удалена");
            }
            case "man" -> System.out.println("Скоро здесь появится справка по командам пользовательского режима");
            default -> System.out.println("Введена неизвестная команда...");
        }
    }

    private void executeGameMode(String[] cmd){
        switch (cmd[0]) {
            case "finish" -> {
                isGameStarted = false;
                currentGame = null;
                System.out.println("Игра была завершена.");
            }
            case "move" -> {
                var hasMoved = gameService.updateGameWithPlayerMove(currentGame.getId(), cmd[1]);
                if(hasMoved){
                    System.out.println("Игрок сместился " + cmd[1] + ". Текущее положение: " + currentGame.getPlayer().getPosX() + ", " + currentGame.getPlayer().getPosY());
                } else {
                    System.out.println("Движение в данном направлении невозможно.");
                }
            }
            default -> System.out.println("Введена неизвестная команда...");
        }
    }
}

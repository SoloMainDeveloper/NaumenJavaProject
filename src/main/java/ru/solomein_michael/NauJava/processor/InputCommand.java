package ru.solomein_michael.NauJava.processor;

import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InputCommand {
    @Autowired
    private CommandProcessor commandProcessor;

    @Bean
    public CommandLineRunner commandScanner() {
        return args -> {
            try (Scanner scanner = new Scanner(System.in)) {
                System.out.println("Введите команду. 'exit' для выхода.");
                while(true) {
                    var prompt = commandProcessor.isGameStarted() ? "# " : "> ";
                    System.out.print(prompt);
                    String input = scanner.nextLine();
                    if ("exit".equalsIgnoreCase(input.trim())) {
                        System.out.println("Выход из программы...");
                        break;
                    }
                    commandProcessor.execute(input);
                }
            }
        };
    }
}
package ru.solomein_michael.NauJava;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.solomein_michael.NauJava.Game.Game;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataBase {
    @Bean
    @Scope(value = BeanDefinition.SCOPE_SINGLETON)
    public List<Game> games(){
        return new ArrayList<>();
    }
}
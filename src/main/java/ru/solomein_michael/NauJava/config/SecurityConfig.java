package ru.solomein_michael.NauJava.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http.authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/registration", "/login", "/logout", "/api/register")
                        .permitAll()
                        .requestMatchers("/delete/**").hasRole("ADMIN")
                        .requestMatchers("/create/**").hasAuthority("CREATOR")
                        .requestMatchers("/swagger-ui/index.html").hasRole("ADMIN")
                        .anyRequest().authenticated()).formLogin((form) -> form
                        .defaultSuccessUrl("/home", true)
                        .permitAll());
        return http.build();
    }
}



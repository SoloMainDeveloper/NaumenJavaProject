package ru.solomein_michael.NauJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.solomein_michael.NauJava.entity.User;
import ru.solomein_michael.NauJava.repository.UserRepository;
import java.util.Collection;
import java.util.stream.Collectors;
import ru.solomein_michael.NauJava.entity.CustomUserDetails;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User appUser = userRepository.findByUsername(username);
        if (appUser != null)
        {
            return new CustomUserDetails(appUser.getUsername(), appUser.getPassword(), mapRoles(appUser));
        }
        return null;
    }

    private Collection<GrantedAuthority> mapRoles(User appUser)
    {
        return appUser.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                .collect(Collectors.toList());
    }
}

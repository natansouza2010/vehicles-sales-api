package br.com.vehiclessales.demo.domain.services;

import br.com.vehiclessales.demo.domain.auth.UserPrincipal;
import br.com.vehiclessales.demo.domain.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CustomDetailsService {


    public UserPrincipal create(User user) {

        List<GrantedAuthority> authorities = Arrays.asList(
                new SimpleGrantedAuthority(user.getRole().toString()));


        return new UserPrincipal(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }
}

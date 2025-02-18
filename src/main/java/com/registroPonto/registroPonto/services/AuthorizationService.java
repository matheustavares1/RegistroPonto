package com.registroPonto.registroPonto.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.registroPonto.registroPonto.repositories.UsersRespository;
@Service
public class AuthorizationService implements UserDetailsService {

    private final UsersRespository respository;

    public AuthorizationService(UsersRespository respository) {
        this.respository = respository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return respository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}

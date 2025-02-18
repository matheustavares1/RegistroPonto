package com.registroPonto.registroPonto.infraSecurity;

import com.registroPonto.registroPonto.repositories.UsersRespository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class TokenFilter extends OncePerRequestFilter {

    private final TokenConfiguration tokenConfiguration;
    private final UsersRespository usersRespository;

    public TokenFilter(TokenConfiguration tokenConfiguration, UsersRespository usersRespository) {
        this.tokenConfiguration = tokenConfiguration;
        this.usersRespository = usersRespository;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.reconverToken(request);

        if(token != null) {
            var subject = tokenConfiguration.validationToken(token);

            UserDetails user = usersRespository.findByEmail(subject)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);

        }
        filterChain.doFilter(request, response);
    }

    //Pegando o token da requiscao
    private String reconverToken(HttpServletRequest request) {
        var authReader = request.getHeader("Authorization");
        if(authReader == null) return null;

        //Identificacao do token no caso
        return authReader.replace("Bearer ", "");
    }
}

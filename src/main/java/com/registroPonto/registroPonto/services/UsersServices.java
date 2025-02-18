package com.registroPonto.registroPonto.services;

import com.registroPonto.registroPonto.dtos.LoginUserDTO;
import com.registroPonto.registroPonto.dtos.ResponseUserLoginDTO;
import com.registroPonto.registroPonto.dtos.ResponseUserResgisterDTO;
import com.registroPonto.registroPonto.dtos.UsersDTO;
import com.registroPonto.registroPonto.entities.Users;
import com.registroPonto.registroPonto.infraSecurity.TokenConfiguration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.registroPonto.registroPonto.repositories.UsersRespository;

@Service
public class UsersServices {

    private final UsersRespository usersRespository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenConfiguration tokenConfiguration;

    public UsersServices(UsersRespository usersRespository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenConfiguration tokenConfiguration) {
        this.usersRespository = usersRespository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenConfiguration = tokenConfiguration;
    }

    public ResponseUserResgisterDTO registerUser(UsersDTO usersDTO) {

        if(usersRespository.findByEmail(usersDTO.email()).isPresent()) throw new RuntimeException("Email already exists");

        String passwordEncrypted = passwordEncoder.encode(usersDTO.password());
        Users newUser = new Users(
                usersDTO.name(), usersDTO.email(), passwordEncrypted, usersDTO.role());
        usersRespository.save(newUser);


        return new ResponseUserResgisterDTO(newUser.getId(), "User created successfully");
    }

   public ResponseUserLoginDTO loginUSer(LoginUserDTO userDTO){
            //Criando o token de autenticacao com o email(username) e a senha(password)
           var emailPasswordToken = new UsernamePasswordAuthenticationToken(userDTO.email(), userDTO.password());
           //Autenticacao do usuario
           var authToken = authenticationManager.authenticate(emailPasswordToken);
           //Pego o usuario autenticado
           var user = (Users) authToken.getPrincipal();
           //Geracao do Token
           var token = tokenConfiguration.generateToken(user);


           return new ResponseUserLoginDTO(token, user.getRole());
   }
}

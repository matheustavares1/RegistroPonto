package com.registroPonto.registroPonto.controllers;

import com.registroPonto.registroPonto.dtos.LoginUserDTO;
import com.registroPonto.registroPonto.dtos.ResponseUserLoginDTO;
import com.registroPonto.registroPonto.dtos.ResponseUserResgisterDTO;
import com.registroPonto.registroPonto.dtos.UsersDTO;
import com.registroPonto.registroPonto.services.UsersServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/auth")
public class UserController {

    private final UsersServices usersServices;


    public UserController(UsersServices usersServices) {
        this.usersServices = usersServices;
    }



    @PostMapping("/register")
    public ResponseEntity<ResponseUserResgisterDTO> registerUser(@RequestBody UsersDTO user) {
        ResponseUserResgisterDTO responseDTO = usersServices.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
    @PostMapping("/login")
    public ResponseEntity<ResponseUserLoginDTO> loginUser(@RequestBody LoginUserDTO userLogin) {
        ResponseUserLoginDTO loginDTO = usersServices.loginUSer(userLogin);
        return ResponseEntity.status(HttpStatus.OK).body(loginDTO);
    }

}

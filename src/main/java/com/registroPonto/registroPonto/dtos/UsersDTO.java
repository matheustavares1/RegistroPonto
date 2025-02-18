package com.registroPonto.registroPonto.dtos;

import com.registroPonto.registroPonto.entities.Role;

public record UsersDTO(
        String name,
        String email,
        String password,
        Role role
) {
}

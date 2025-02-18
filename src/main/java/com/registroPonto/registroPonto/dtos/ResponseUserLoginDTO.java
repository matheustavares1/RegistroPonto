package com.registroPonto.registroPonto.dtos;

import com.registroPonto.registroPonto.entities.Role;

public record ResponseUserLoginDTO(
        String token,
        Role role
) {
}

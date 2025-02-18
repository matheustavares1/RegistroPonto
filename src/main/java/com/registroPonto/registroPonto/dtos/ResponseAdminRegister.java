package com.registroPonto.registroPonto.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

public record ResponseAdminRegister(
        String name,
        LocalDate date,
        LocalTime checkin,
        LocalTime checkout,
        String hours_worked
) {
}

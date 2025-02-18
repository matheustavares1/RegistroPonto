package com.registroPonto.registroPonto.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

public record ResponseHistory(
        LocalDate date,
        LocalTime checkin,
        LocalTime checkout,
        Double hours_worked
) {
}

package com.registroPonto.registroPonto.dtos;

import java.time.Instant;

public record ResponseCheckinDTO (
        String message,
        Instant timeStamp
){
}

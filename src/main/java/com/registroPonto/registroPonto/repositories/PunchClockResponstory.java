package com.registroPonto.registroPonto.repositories;

import com.registroPonto.registroPonto.entities.PunchClock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public interface PunchClockResponstory extends JpaRepository<PunchClock, UUID> {

    @Query("""
    SELECT p.timestamp FROM PunchClock p 
    WHERE p.user.id = :userId 
    AND p.type = 'CHECKIN' 
    AND DATE(p.timestamp) = :date
    ORDER BY p.timestamp DESC LIMIT 1
""")
    Instant findLastCheckInByUserIdAndDate(@Param("userId") UUID userId, @Param("date") LocalDate date);

    @Query("""
    SELECT p.timestamp FROM PunchClock p 
    WHERE p.user.id = :userId 
    AND p.type = 'CHECKOUT' 
    AND DATE(p.timestamp) = :date
    ORDER BY p.timestamp DESC LIMIT 1
""")
    Instant findLastCheckOutByUserIdAndDate(@Param("userId") UUID userId, @Param("date") LocalDate date);



}

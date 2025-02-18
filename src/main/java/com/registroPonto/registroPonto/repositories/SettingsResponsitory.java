package com.registroPonto.registroPonto.repositories;

import com.registroPonto.registroPonto.dtos.EmployeeDTO;
import com.registroPonto.registroPonto.dtos.ReportsDTO;
import com.registroPonto.registroPonto.entities.Settings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface SettingsResponsitory extends JpaRepository<Settings, UUID> {

    List<Settings> findByDateBetween(LocalDate startDate, LocalDate endDate);
}

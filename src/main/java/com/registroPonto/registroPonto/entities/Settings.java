package com.registroPonto.registroPonto.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "settings")
public class Settings {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "hours_worked")
    private Double workdayHours;
    @Column(name = "overtime_worked")
    private Double overtimeRate;
    private String name;
    private LocalDate date;
    public Settings() {}
    public Settings(Double workdayHours, Double overtimeRate) {
        this.workdayHours = workdayHours;
        this.overtimeRate = overtimeRate;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setWorkdayHours(Double workdayHours) {
        this.workdayHours = workdayHours;
    }

    public void setOvertimeRate(Double overtimeRate) {
        this.overtimeRate = overtimeRate;
    }

    public UUID getId() {
        return id;
    }

    public Double getWorkdayHours() {
        return workdayHours;
    }

    public Double getOvertimeRate() {
        return overtimeRate;
    }
}

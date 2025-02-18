package com.registroPonto.registroPonto.controllers;

import com.registroPonto.registroPonto.dtos.ReportsDTO;
import com.registroPonto.registroPonto.services.AdministrationService;
import com.registroPonto.registroPonto.dtos.ResponseAdminRegister;
import com.registroPonto.registroPonto.services.SettingsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class AdministrationController {

    private final AdministrationService administrationService;
    private final SettingsService settingsService;
    public AdministrationController(AdministrationService administrationService, SettingsService settingsService) {
        this.administrationService = administrationService;
        this.settingsService = settingsService;
    }
    @GetMapping("/get")
    public ResponseEntity<ResponseAdminRegister> getRegisterEmployee(@RequestParam LocalDate date, @RequestParam UUID idEmployee){
        ResponseAdminRegister response  = administrationService.getRegisterEmployee(date,idEmployee);
        return ResponseEntity.ok().body(response);
    }
    @GetMapping("/reports")
    public ResponseEntity<ReportsDTO> getReportsEmployee(@RequestParam LocalDate startDate , @RequestParam LocalDate endDate){
        ReportsDTO reponseReports = settingsService.getReports(startDate,endDate);
        return ResponseEntity.ok().body(reponseReports);
    }
}

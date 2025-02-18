package com.registroPonto.registroPonto.controllers;

import com.registroPonto.registroPonto.dtos.CheckInCheckOutDTO;
import com.registroPonto.registroPonto.dtos.ResponseCheckinDTO;
import com.registroPonto.registroPonto.dtos.ResponseHistory;
import com.registroPonto.registroPonto.services.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/check/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/")
    public ResponseEntity<ResponseCheckinDTO> checkin(@RequestBody CheckInCheckOutDTO checkIn) {
        ResponseCheckinDTO checkinDTO = employeeService.checkin(checkIn);
        return ResponseEntity.ok().body(checkinDTO);
    }



    @GetMapping("/punch-clock/history")
    public ResponseEntity<ResponseHistory> history(@RequestParam LocalDate date) {
        ResponseHistory responseHistory = employeeService.history(date);
        return ResponseEntity.ok().body(responseHistory);
    }
}

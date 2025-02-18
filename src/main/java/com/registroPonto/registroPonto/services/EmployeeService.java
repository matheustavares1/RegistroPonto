package com.registroPonto.registroPonto.services;

import com.registroPonto.registroPonto.dtos.CheckInCheckOutDTO;
import com.registroPonto.registroPonto.dtos.ResponseCheckinDTO;
import com.registroPonto.registroPonto.dtos.ResponseHistory;
import com.registroPonto.registroPonto.entities.PunchClock;
import com.registroPonto.registroPonto.entities.Settings;
import com.registroPonto.registroPonto.entities.Users;
import com.registroPonto.registroPonto.exception.EmailNotFound;
import com.registroPonto.registroPonto.exception.TimeCheckException;
import com.registroPonto.registroPonto.repositories.PunchClockResponstory;
import com.registroPonto.registroPonto.repositories.SettingsResponsitory;
import com.registroPonto.registroPonto.repositories.UsersRespository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class EmployeeService {

    private final PunchClockResponstory punchClockResponstory;
    private final UsersRespository usersRepository;
    private final SettingsResponsitory settingsResponsitory;

    public EmployeeService(PunchClockResponstory punchClockResponstory, UsersRespository usersRepository, SettingsResponsitory settingsResponsitory) {
        this.punchClockResponstory = punchClockResponstory;
        this.usersRepository = usersRepository;

        this.settingsResponsitory = settingsResponsitory;
    }
    //Bater Ponto
    public ResponseCheckinDTO checkin(CheckInCheckOutDTO checkinDTO) {
        //Pegando o usuario autenticado
        String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        //Buscando no BD
        Users user = usersRepository.findByEmail(email).orElseThrow(() -> new EmailNotFound("Email not found"));

        PunchClock punchClock = new PunchClock();
        punchClock.setTimestamp(Instant.now());
        punchClock.setUser(user);
        punchClock.setType(checkinDTO.types());
        punchClockResponstory.save(punchClock);

        return new ResponseCheckinDTO("Point registered successfully!", Instant.now());
    }


    //Buscar historico do ponto e horas trabalhadas
    public ResponseHistory history(LocalDate date) {
        //Consulta o usuario logado
        String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        //verificacao para username do usuario logado
        Users user = usersRepository.findByEmail(email).orElseThrow(() ->
                new EmailNotFound("Email Not Found"));

        //Metodos auxiliares para conultas do CheckIn e CheckOut
        LocalTime timeCheckin = timeCheckin(date, user.getId());
        LocalTime timeCheckout = timeCheckOut(date, user.getId());


        //Calculo para saber quantas horas trabalhadas
        Duration hours = Duration.between( timeCheckin, timeCheckout);
        long hours1 = hours.toHours();
        ///long minutes = hours.toMinutes() % 60;
       // String hoursWorking = String.format("%02d %02d", hours1, minutes);
        double workdayHours = 0.0;
        double overTimeRate = 0.0;

        if(hours1 > 8){
            workdayHours = 8.0;
            overTimeRate = hours1 - 8.0;
        }
        else {
            overTimeRate = hours1;
        }

        Settings settings = new Settings();
        settings.setName(user.getName());
        settings.setOvertimeRate(overTimeRate);
        settings.setWorkdayHours(workdayHours);
        settings.setDate(date);
        settingsResponsitory.save(settings);

         return new ResponseHistory(
                 date, timeCheckin,timeCheckout, workdayHours + overTimeRate
        );

    }

    //Metodo auxiliar para buscar a hora do checkin
    public LocalTime timeCheckin(LocalDate date, UUID userId) {
        //Consulta para buscar o ultimo checkin
        var checkin = punchClockResponstory.findLastCheckInByUserIdAndDate(userId, date);

        if(checkin == null) {
            throw new TimeCheckException("Time checkin failed");
        }
        return checkin.atZone(ZoneId.of("America/Sao_Paulo")).toLocalTime();
    }

    //Metodo auxiliar para buscar a hora do checkout
    public LocalTime timeCheckOut(LocalDate date, UUID userId ) {
        //Consulta para buscar a data do ultimo checkout
       var checkout = punchClockResponstory.findLastCheckOutByUserIdAndDate(userId,date);

       if(checkout == null) {
           throw new TimeCheckException("Time checkout failed");
       }

       return checkout.atZone(ZoneId.of("America/Sao_Paulo")).toLocalTime();
    }

}

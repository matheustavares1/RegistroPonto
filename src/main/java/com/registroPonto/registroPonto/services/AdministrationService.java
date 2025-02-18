
package com.registroPonto.registroPonto.services;

import com.registroPonto.registroPonto.dtos.EmployeeDTO;
import com.registroPonto.registroPonto.dtos.ReportsDTO;
import com.registroPonto.registroPonto.dtos.ResponseAdminRegister;
import com.registroPonto.registroPonto.entities.Users;
import com.registroPonto.registroPonto.exception.IdNotFound;
import com.registroPonto.registroPonto.exception.TimeCheckException;
import com.registroPonto.registroPonto.repositories.PunchClockResponstory;
import com.registroPonto.registroPonto.repositories.UsersRespository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@Service
public class AdministrationService {



    private final PunchClockResponstory punchClockResponstory;
    private final UsersRespository usersRespository;

    public AdministrationService(PunchClockResponstory punchClockResponstory, UsersRespository usersRespository) {
        this.punchClockResponstory = punchClockResponstory;
        this.usersRespository = usersRespository;
    }

     public ResponseAdminRegister getRegisterEmployee(LocalDate date, UUID idEmployee){
        //Buscando ID do Usuario para pegar os registros de pontos
        Users user = usersRespository.findById(idEmployee).orElseThrow(() -> new IdNotFound("Id Not Found"));

        //Metodos Auxiliares para pegar os instantes do CheckIn e CheckOut
        var timeCheckinGetEmployee = this.chekIn(date, user.getId());
        var timeCheckoutGetEmpoyee = this.chekIn(date, user.getId());

        //DIFERENCA ENTRE AS HORAS
        Duration hours = Duration.between(timeCheckinGetEmployee, timeCheckoutGetEmpoyee);
        long hours1 = hours.toHours();
        long minutes = hours.toMinutes() % 60;
        //Convertendo a hora e o minuto do Instant
        String hoursWorkin = String.format("%02d:%02d", hours1, minutes);



        return new ResponseAdminRegister(
                user.getName(), date, timeCheckinGetEmployee,timeCheckoutGetEmpoyee, hoursWorkin
        );
    }

    //Metodo auxiliar para pegar o tempo do CheckIn
    public LocalTime chekIn(LocalDate date, UUID idEmployee){
        var checkin = punchClockResponstory.findLastCheckInByUserIdAndDate(idEmployee, date);

        if(checkin == null) throw new TimeCheckException("Time CheckIn Failed");


        return checkin.atZone(ZoneId.of("America/Sao_Paulo")).toLocalTime();
    }
    //Metodo auxiliar para pegar o tempo do CheckOut
    public LocalTime chekOut(LocalDate date, UUID idEmployee){
        var checkout = punchClockResponstory.findLastCheckOutByUserIdAndDate(idEmployee, date);
        if(checkout == null) throw new TimeCheckException("Time CheckOut Failed");

        return checkout.atZone(ZoneId.of("America/Sao_Paulo")).toLocalTime();
    }


}

package com.registroPonto.registroPonto.services;

import com.registroPonto.registroPonto.dtos.EmployeeDTO;
import com.registroPonto.registroPonto.dtos.ReportsDTO;
import com.registroPonto.registroPonto.entities.Settings;
import com.registroPonto.registroPonto.repositories.SettingsResponsitory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SettingsService {

    private final SettingsResponsitory respository;

    public SettingsService(SettingsResponsitory responsitory) {
        this.respository = responsitory;
    }

    //metodo para gerar o relatorio de pontos de todos os funciarios entre datas
    public ReportsDTO getReports(LocalDate startDate, LocalDate endDate ) {
        //Busca dados entre duas datas
        List<Settings> settings = respository.findByDateBetween(startDate, endDate);
        //Mapeia os dados buscados para DTOS(apenas nome e horas trabalhadas)
        List<EmployeeDTO> dto = settings.stream()
                .map(settings1 ->
                    new EmployeeDTO(settings1.getName(), settings1.getWorkdayHours()))
                .collect(Collectors.toList());
        //Somo as horas totais
        Double totalHoursWorked = dto.stream()
                //Transformo os elementos da lista em Double e pego apenas o hoursWorked
                .mapToDouble(EmployeeDTO::hoursWorked)
                .sum();
        return new ReportsDTO(totalHoursWorked,dto);
    }

}

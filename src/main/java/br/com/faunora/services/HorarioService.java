package br.com.faunora.services;

import br.com.faunora.domain.models.ConsultaModel;
import br.com.faunora.domain.models.DosagemModel;
import br.com.faunora.domain.models.ExameModel;
import br.com.faunora.domain.models.UserModel;
import br.com.faunora.repositories.ConsultaRepository;
import br.com.faunora.repositories.DosagemRepository;
import br.com.faunora.repositories.ExameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class HorarioService {
    @Value("${api.services.time.duration}")
    private Integer duration;
    @Value("${api.services.morning.start.time}")
    private Integer morningStart;
    @Value("${api.services.morning.ending.time}")
    private Integer morningEnd;
    @Value("${api.services.afternoon.start.time}")
    private Integer afternoonStart;
    @Value("${api.services.afternoon.ending.time}")
    private Integer afternoonEnd;
    @Value("${api.services.maximum.appointment.range}")
    private Integer maximumAppointmentRange;
    @Autowired
    private ExameRepository exameRepository;
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private DosagemRepository dosagemRepository;

    public List<LocalTime> getSlotsDisponiveisPorVetEDia(UserModel vet, LocalDate date) {
        List<LocalTime> slotsDisponiveis = new ArrayList<>();
        LocalTime slotMorning = LocalTime.of(morningStart, 0);

        while (slotMorning.isBefore(LocalTime.of(morningEnd, 0))) {
            if (isSlotAvailableForVet(vet, date, slotMorning)) {
                slotsDisponiveis.add(slotMorning);
            }
            slotMorning = slotMorning.plusMinutes(30);
        }

        LocalTime slotAfternoon = LocalTime.of(afternoonStart, 0);

        while (slotAfternoon.isBefore(LocalTime.of(afternoonEnd, 0))) {
            if (isSlotAvailableForVet(vet, date, slotAfternoon)) {
                slotsDisponiveis.add(slotAfternoon);
            }
            slotAfternoon = slotAfternoon.plusMinutes(30);
        }

        return slotsDisponiveis;
    }

    public boolean isSlotValid(LocalTime slot) {
        if (slot.getMinute() == 0 || slot.getMinute() == 30) {
            if (slot.getHour() >= morningStart && slot.getHour() < morningEnd) {
                return true;
            } else if (slot.getHour() >= afternoonStart && slot.getHour() < afternoonEnd) {
                return true;
            }
        }

        return false;
    }

    public boolean isDateValid(LocalDate date) {
        LocalDate today = LocalDate.now();
        LocalDate maxDate = today.plusDays(maximumAppointmentRange);

        return date.isAfter(today) && date.isBefore(maxDate);
    }


    public boolean isSlotAvailableForVet(UserModel vet, LocalDate date, LocalTime slot) {
        List<ExameModel> exames = exameRepository.findAllByVeterinarioAndData(vet, date);
        List<ConsultaModel> consultas = consultaRepository.findAllByVeterinarioAndData(vet, date);
        List<DosagemModel> dosagens = dosagemRepository.findAllByVeterinarioAndData(vet, date);

        for (ExameModel exameModel : exames) {
            if (exameModel.getHora().equals(slot)) {
                return false;
            }
        }

        for (ConsultaModel consultaModel : consultas) {
            if (consultaModel.getHora().equals(slot)) {
                return false;
            }
        }

        for (DosagemModel dosagemModel : dosagens) {
            if (dosagemModel.getHora().equals(slot)) {
                return false;
            }
        }

        return true;
    }

    public List<LocalDate> getDiasDisponiveisPorVet(UserModel vet) {
        List<LocalDate> diasDisponiveis = new ArrayList<>();

        for (LocalDate dia = LocalDate.now(); dia.isBefore(LocalDate.now().plusDays(30)); dia = dia.plusDays(1)) {
            if (!getSlotsDisponiveisPorVetEDia(vet, dia).isEmpty()) {
                diasDisponiveis.add(dia);
            }
        }

        return diasDisponiveis;
    }
}

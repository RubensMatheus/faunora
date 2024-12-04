package br.com.faunora.services;

import br.com.faunora.domain.dto.consultas.ConsultaRecordDto;
import br.com.faunora.domain.enums.UserTipo;
import br.com.faunora.domain.models.ConsultaModel;
import br.com.faunora.domain.models.PetModel;
import br.com.faunora.domain.models.UserModel;
import br.com.faunora.infra.exceptions.consultas.ConsultaNaoEncontradaException;
import br.com.faunora.infra.exceptions.consultas.NenhumaConsultaEncontradaException;
import br.com.faunora.infra.exceptions.datas.DataIndisponivelException;
import br.com.faunora.infra.exceptions.datas.HorarioIndisponivelException;
import br.com.faunora.infra.exceptions.exames.NenhumExameEncontradoException;
import br.com.faunora.infra.exceptions.pets.PetIndisponivelException;
import br.com.faunora.infra.exceptions.pets.PetNaoEncontradoException;
import br.com.faunora.infra.exceptions.users.UsuarioNaoEncontradoException;
import br.com.faunora.infra.exceptions.users.VeterinarioInvalidoException;
import br.com.faunora.infra.exceptions.users.VeterinarioNaoEncontradoException;
import br.com.faunora.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConsultaService {
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HorarioService horarioService;
    @Autowired
    private ExameRepository exameRepository;
    @Autowired
    private DosagemRepository dosagemRepository;

    private UserModel getAuthenticatedUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findByEmail(email)
                .orElseThrow(UsuarioNaoEncontradoException::new);
    }

    @Transactional
    public void saveConsulta(ConsultaRecordDto consultaRecordDto) {
        UserModel usuario = getAuthenticatedUser();

        PetModel pet = petRepository.findById(consultaRecordDto.pacienteId())
                .orElseThrow(PetNaoEncontradoException::new);

        if (!petRepository.findAllByTutor(usuario).contains(pet)) {
            throw new PetNaoEncontradoException("pet não disponível para consulta");
        }

        if (!horarioService.isDateValid(consultaRecordDto.data())) {
            throw new DataIndisponivelException();
        }

        if (!horarioService.isSlotValid(consultaRecordDto.hora())) {
            throw new HorarioIndisponivelException();
        }

        if (!consultaRepository.findAllByPacienteAndDataAndHora(pet, consultaRecordDto.data(), consultaRecordDto.hora()).isEmpty()
            || !exameRepository.findAllByPacienteAndDataAndHora(pet, consultaRecordDto.data(), consultaRecordDto.hora()).isEmpty()
            || !dosagemRepository.findAllByPacienteAndDataAndHora(pet, consultaRecordDto.data(), consultaRecordDto.hora()).isEmpty()) {
            throw new PetIndisponivelException();
        }

        ConsultaModel consulta = new ConsultaModel();
        consulta.setPaciente(pet);
        consulta.setData(consultaRecordDto.data());
        consulta.setHora(consultaRecordDto.hora());

        UserModel veterinario = userRepository.findById(consultaRecordDto.veterinarioId())
                .orElseThrow(VeterinarioNaoEncontradoException::new);

        if (!veterinario.getTipo().equals(UserTipo.VETERINARIO)) {
            throw new VeterinarioInvalidoException("veterinário não é válido");
        }

        if (!horarioService.isSlotAvailableForVet(veterinario, consulta.getData(), consulta.getHora())) {
            throw new HorarioIndisponivelException("horário indisponível para veterinário fornecido");
        }

        consulta.setVeterinario(veterinario);

        consultaRepository.save(consulta);
    }

    public ConsultaModel findById(Long id) {
        return consultaRepository.findById(id)
                .orElseThrow(ConsultaNaoEncontradaException::new);
    }

    public List<ConsultaModel> findAllByTutor() {
        UserModel usuario = getAuthenticatedUser();
        List<ConsultaModel> consultas = new ArrayList<>();

        for (PetModel petModel : petRepository.findAllByTutor(usuario)) {
            consultas.addAll(consultaRepository.findAllByPaciente(petModel));
        }

        if (consultas.isEmpty()) {
            throw new NenhumaConsultaEncontradaException();
        }

        return consultas;
    }

    public List<ConsultaModel> findAllByPaciente(Long pacienteId) {
        UserModel usuario = getAuthenticatedUser();

        PetModel paciente = petRepository.findById(pacienteId)
                .orElseThrow(PetNaoEncontradoException::new);

        if (!petRepository.findAllByTutor(usuario).contains(paciente)) {
            throw new PetNaoEncontradoException("pet não disponível para consulta");
        }

        List<ConsultaModel> consultas = consultaRepository.findAllByPaciente(paciente);

        if (consultas.isEmpty()) {
            throw new NenhumaConsultaEncontradaException();
        }

        return consultas;
    }

    public List<ConsultaModel> findAllByVeterinario() {
        UserModel veterinario = getAuthenticatedUser();

        if (!veterinario.getTipo().equals(UserTipo.VETERINARIO)) {
            throw new VeterinarioInvalidoException("usuário não é veterinário");
        }

        List<ConsultaModel> consultas = consultaRepository.findAllByVeterinario(veterinario);

        if (consultas.isEmpty()) {
            throw new NenhumaConsultaEncontradaException();
        }

        return consultas;
    }

    public List<ConsultaModel> findAnterioresVeterinario() {
        List<ConsultaModel> consultas = this.findAllByVeterinario();

        for (ConsultaModel consultaModel : consultas) {
            LocalDateTime dataHora = LocalDateTime.of(consultaModel.getData(), consultaModel.getHora());

            if (dataHora.isAfter(LocalDateTime.now())) {
                consultas.remove(consultaModel);
            }
        }

        if (consultas.isEmpty()) {
            throw new NenhumaConsultaEncontradaException("nenhuma consulta anterior encontrada.");
        }

        return consultas;
    }

    public List<ConsultaModel> findAnteriores() {
        List<ConsultaModel> consultas = this.findAllByTutor();

        for (ConsultaModel consultaModel : consultas) {
            LocalDateTime dataHora = LocalDateTime.of(consultaModel.getData(), consultaModel.getHora());

            if (dataHora.isAfter(LocalDateTime.now())) {
                consultas.remove(consultaModel);
            }
        }

        if (consultas.isEmpty()) {
            throw new NenhumaConsultaEncontradaException("nenhuma consulta anterior encontrada.");
        }

        return consultas;
    }

    public List<ConsultaModel> findMarcadosVeterinario() {
        List<ConsultaModel> consultas = this.findAllByVeterinario();

        for (ConsultaModel consultaModel : consultas) {
            LocalDateTime dataHora = LocalDateTime.of(consultaModel.getData(), consultaModel.getHora());

            if (dataHora.isBefore(LocalDateTime.now())) {
                consultas.remove(consultaModel);
            }
        }

        if (consultas.isEmpty()) {
            throw new NenhumExameEncontradoException();
        }

        return consultas;
    }

    public List<ConsultaModel> findMarcados() {
        List<ConsultaModel> consultas = this.findAllByTutor();

        for (ConsultaModel consultaModel : consultas) {
            LocalDateTime dataHora = LocalDateTime.of(consultaModel.getData(), consultaModel.getHora());

            if (dataHora.isBefore(LocalDateTime.now())) {
                consultas.remove(consultaModel);
            }
        }

        if (consultas.isEmpty()) {
            throw new NenhumExameEncontradoException();
        }

        return consultas;
    }

    public List<ConsultaModel> findAllByRandomVeterinario(String filter) {
        List<ConsultaModel> consultaModelsByRandom = new ArrayList<>();

        for (ConsultaModel consultaModel : this.findAllByVeterinario()) {
            if (consultaModel.getData().toString().contains(filter) || consultaModel.getHora().toString().contains(filter) || consultaModel.getPaciente().getNome().contains(filter) || consultaModel.getVeterinario().getNome().contains(filter)) {
                consultaModelsByRandom.add(consultaModel);
            }
        }

        if (consultaModelsByRandom.isEmpty()) {
            throw new NenhumExameEncontradoException("nenhum exame correspondente encontrado");
        }

        return consultaModelsByRandom;
    }

    public List<ConsultaModel> findAllByRandom(String filter) {
        List<ConsultaModel> consultaModelsByRandom = new ArrayList<>();

        for (ConsultaModel consultaModel : this.findAllByTutor()) {
            if (consultaModel.getData().toString().contains(filter) || consultaModel.getHora().toString().contains(filter) || consultaModel.getPaciente().getNome().contains(filter) || consultaModel.getVeterinario().getNome().contains(filter)) {
                consultaModelsByRandom.add(consultaModel);
            }
        }

        if (consultaModelsByRandom.isEmpty()) {
            throw new NenhumExameEncontradoException("nenhum exame correspondente encontrado");
        }

        return consultaModelsByRandom;
    }

    @Transactional
    public void updateConsulta(Long id, ConsultaRecordDto consultaRecordDto) {
        UserModel userModel = getAuthenticatedUser();

        ConsultaModel consultaModel = consultaRepository.findById(id)
                .orElseThrow(ConsultaNaoEncontradaException::new);

        PetModel petModel = petRepository.findById(consultaRecordDto.pacienteId())
                .orElseThrow(PetNaoEncontradoException::new);

        UserModel veterinario = userRepository.findById(consultaRecordDto.veterinarioId())
                .orElseThrow(UsuarioNaoEncontradoException::new);

        if (!veterinario.getTipo().equals(UserTipo.VETERINARIO)) {
            throw new UsuarioNaoEncontradoException("usuário não é um veterinário válido");
        }

        consultaModel.setPaciente(petModel);
        consultaModel.setVeterinario(veterinario);
        consultaModel.setData(consultaRecordDto.data());
        consultaModel.setHora(consultaRecordDto.hora());

        consultaRepository.save(consultaModel);
    }

    @Transactional
    public void deleteById(Long id) {
        UserModel usuario = getAuthenticatedUser();

        ConsultaModel consulta = consultaRepository.findById(id)
                .orElseThrow(ConsultaNaoEncontradaException::new);

        if (!petRepository.findAllByTutor(usuario).contains(consulta.getPaciente())) {
            throw new PetNaoEncontradoException("pet não disponível para consulta");
        }

        consultaRepository.delete(consulta);
    }

    public List<LocalDate> getDiasDisponiveisPorVet(Long veterinarioId) {
        UserModel veterinario = userRepository.findById(veterinarioId)
                .orElseThrow(VeterinarioNaoEncontradoException::new);

        if (!veterinario.getTipo().equals(UserTipo.VETERINARIO)) {
            throw new VeterinarioInvalidoException("usuário não é um veterinário válido");
        }

        return horarioService.getDiasDisponiveisPorVet(veterinario);
    }

    public List<LocalTime> getHorariosDisponiveisPorDiaEVet(LocalDate data, Long veterinarioId) {
        UserModel veterinario = userRepository.findById(veterinarioId)
                .orElseThrow(VeterinarioNaoEncontradoException::new);

        if (!veterinario.getTipo().equals(UserTipo.VETERINARIO)) {
            throw new VeterinarioInvalidoException("usuário não é um veterinário válido");
        }

        return horarioService.getSlotsDisponiveisPorVetEDia(veterinario, data);
    }
}

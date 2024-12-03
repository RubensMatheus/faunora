package br.com.faunora.services;

import br.com.faunora.domain.dto.ConsultaRecordDto;
import br.com.faunora.domain.enums.UserTipo;
import br.com.faunora.domain.models.ConsultaModel;
import br.com.faunora.domain.models.PetModel;
import br.com.faunora.domain.models.UserModel;
import br.com.faunora.infra.exceptions.*;
import br.com.faunora.repositories.ConsultaRepository;
import br.com.faunora.repositories.PetRepository;
import br.com.faunora.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
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

        ConsultaModel consulta = new ConsultaModel();
        consulta.setPaciente(pet);
        consulta.setData(consultaRecordDto.data());
        consulta.setHora(consultaRecordDto.hora());

        UserModel veterinario = userRepository.findById(consultaRecordDto.veterinarioId())
                .orElseThrow(VeterinarioNaoEncontradoException::new);

        if (!veterinario.getTipo().equals(UserTipo.VETERINARIO)) {
            throw new VeterinarioNaoEncontradoException("o veterinário fornecido não é válido");
        }

        consulta.setVeterinario(veterinario);

        consultaRepository.save(consulta);
    }

    public ConsultaModel findById(Long id) {
        UserModel usuario = getAuthenticatedUser();

        ConsultaModel consulta = consultaRepository.findById(id)
                .orElseThrow(ConsultaNaoEncontradaException::new);

        if (!petRepository.findAllByTutor(usuario).contains(consulta.getPaciente())) {
            throw new PetNaoEncontradoException("pet não disponível para consulta");
        }

        boolean isPetOwnedByUser = petRepository.existsByIdAndTutor(consulta.getPaciente().getId(), usuario);

        if (!isPetOwnedByUser) {
            throw new PetNaoEncontradoException("pet não disponível para consulta");
        }

        return consulta;
    }

    public List<ConsultaModel> findAll() {
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
            throw new VeterinarioNaoEncontradoException();
        }

        List<ConsultaModel> consultas = consultaRepository.findAllByVeterinario(veterinario);

        if (consultas.isEmpty()) {
            throw new NenhumaConsultaEncontradaException();
        }

        return consultas;
    }

    public List<ConsultaModel> findAnteriores() {
        List<ConsultaModel> consultas = this.findAll();

        consultas.removeIf(consulta -> consulta.getData().isAfter(ChronoLocalDate.from(LocalDate.now())));

        if (consultas.isEmpty()) {
            throw new NenhumaConsultaEncontradaException("Nenhuma consulta anterior encontrada.");
        }

        return consultas;
    }

    public List<ConsultaModel> findMarcados() {
        List<ConsultaModel> consultas = this.findAll();

        consultas.removeIf(exameModel -> exameModel.getData().isBefore(ChronoLocalDate.from(LocalDate.now())));

        if (consultas.isEmpty()) {
            throw new NenhumExameEncontradoException();
        }

        return consultas;
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
            throw new UsuarioNaoEncontradoException("Usuário não é um veterinário válido");
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
}

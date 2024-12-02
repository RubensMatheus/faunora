package br.com.faunora.services;

import br.com.faunora.domain.dto.ExameRecordDto;
import br.com.faunora.domain.enums.ExameTipo;
import br.com.faunora.domain.enums.UserTipo;
import br.com.faunora.domain.models.ExameModel;
import br.com.faunora.domain.models.PetModel;
import br.com.faunora.domain.models.UserModel;
import br.com.faunora.infra.exceptions.ExameNaoEncontradoException;
import br.com.faunora.infra.exceptions.NenhumExameEncontradoException;
import br.com.faunora.infra.exceptions.PetNaoEncontradoException;
import br.com.faunora.infra.exceptions.UsuarioNaoEncontradoException;
import br.com.faunora.repositories.ExameRepository;
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
import java.util.Random;

@Service
public class ExameService {
    @Autowired
    private ExameRepository exameRepository;
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void saveExame(ExameRecordDto exameRecordDto) {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            throw new UsuarioNaoEncontradoException();
        }

        UserModel userModel = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(UsuarioNaoEncontradoException::new);

        PetModel petModel = petRepository.findById(exameRecordDto.pacienteId())
                .orElseThrow(PetNaoEncontradoException::new);

        if (!userModel.getPets().contains(petModel)) {
            throw new PetNaoEncontradoException("pet não disponível para consulta");
        }

        ExameModel exameModel = new ExameModel();
        exameModel.setPaciente(petModel);
        exameModel.setTipo(exameRecordDto.tipo());
        exameModel.setData(exameRecordDto.data());
        exameModel.setHora(exameRecordDto.hora());

        List<UserModel> veterinarios = userRepository.findAllByTipo(UserTipo.VETERINÁRIO);

        exameModel.setVeterinario(veterinarios.get(new Random().nextInt(veterinarios.size())));

        exameRepository.save(exameModel);
    }

    public ExameModel findById(Long id) {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            throw new UsuarioNaoEncontradoException();
        }

        UserModel userModel = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(UsuarioNaoEncontradoException::new);

        ExameModel exameModel = exameRepository.findById(id)
                .orElseThrow(ExameNaoEncontradoException::new);

        if (!userModel.getPets().contains(exameModel.getPaciente())) {
            throw new PetNaoEncontradoException("pet não disponível para consulta");
        }

        return exameModel;
    }

    public List<ExameModel> findAll() {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            throw new UsuarioNaoEncontradoException();
        }

        UserModel userModel = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(UsuarioNaoEncontradoException::new);

        List<ExameModel> exameModels = new ArrayList<>();

        for (PetModel petModel : userModel.getPets()) {
            exameModels.addAll(exameRepository.findAllByPaciente(petModel));
        }

        if (exameModels.isEmpty()) {
            throw new NenhumExameEncontradoException();
        }

        return exameModels;
    }

    public List<ExameModel> findAllByPaciente(Long pacienteId) {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            throw new UsuarioNaoEncontradoException();
        }

        UserModel userModel = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(UsuarioNaoEncontradoException::new);

        PetModel paciente = petRepository.findById(pacienteId)
                .orElseThrow(PetNaoEncontradoException::new);

        if (!userModel.getPets().contains(paciente)) {
            throw new PetNaoEncontradoException("pet não disponível para consulta");
        }

        List<ExameModel> exameModels = exameRepository.findAllByPaciente(paciente);

        if (exameModels.isEmpty()) {
            throw new NenhumExameEncontradoException();
        }

        return exameModels;
    }

    public List<ExameModel> findAllByTipo(ExameTipo tipo) {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            throw new UsuarioNaoEncontradoException();
        }

        UserModel userModel = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(UsuarioNaoEncontradoException::new);

        List<ExameModel> exameModels = new ArrayList<>();
        for (PetModel petModel : userModel.getPets()) {
            exameModels.addAll(exameRepository.findAllByTipoAndPaciente(tipo, petModel));
        }

        if (exameModels.isEmpty()) {
            throw new NenhumExameEncontradoException();
        }

        return exameModels;
    }

    public List<ExameModel> findAnteriores() {
        List<ExameModel> exameModels = this.findAll();

        exameModels.removeIf(exameModel -> exameModel.getData().isAfter(ChronoLocalDate.from(LocalDate.now())));

        if (exameModels.isEmpty()) {
            throw new NenhumExameEncontradoException();
        }

        return exameModels;
    }

    public List<ExameModel> findMarcados() {
        List<ExameModel> exameModels = this.findAll();

        exameModels.removeIf(exameModel -> exameModel.getData().isBefore(ChronoLocalDate.from(LocalDate.now())));

        if (exameModels.isEmpty()) {
            throw new NenhumExameEncontradoException();
        }

        return exameModels;
    }

    public List<ExameModel> findAllByRandom(String filter) {
        List<ExameModel> exameModelsByRandom = new ArrayList<>();

        for (ExameModel exameModel : this.findAll()) {
            if (exameModel.getTipo().toString().contains(filter) || exameModel.getData().toString().contains(filter) || exameModel.getHora().toString().contains(filter)) {
                exameModelsByRandom.add(exameModel);
            }
        }

        if (exameModelsByRandom.isEmpty()) {
            throw new NenhumExameEncontradoException("nenhum exame correspondente encontrado");
        }

        return exameModelsByRandom;
    }

    @Transactional
    public void updateExame(Long id, ExameRecordDto exameRecordDto) {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            throw new UsuarioNaoEncontradoException();
        }

        UserModel userModel = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(UsuarioNaoEncontradoException::new);

        ExameModel exameModel = exameRepository.findById(id)
                .orElseThrow(ExameNaoEncontradoException::new);

        PetModel petModel = petRepository.findById(exameRecordDto.pacienteId())
                .orElseThrow(() -> new PetNaoEncontradoException("pet não disponível para consulta"));

        if (!userModel.getPets().contains(petModel)) {
            throw new PetNaoEncontradoException();
        }

        exameModel.setPaciente(petModel);
        exameModel.setTipo(exameRecordDto.tipo());
        exameModel.setData(exameRecordDto.data());
        exameModel.setHora(exameRecordDto.hora());

        exameRepository.save(exameModel);
    }

    @Transactional
    public void deleteById(Long id) {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            throw new UsuarioNaoEncontradoException();
        }

        UserModel userModel = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(UsuarioNaoEncontradoException::new);

        ExameModel exameModel = exameRepository.findById(id)
                .orElseThrow(ExameNaoEncontradoException::new);

        if (!userModel.getPets().contains(exameModel.getPaciente())) {
            throw new PetNaoEncontradoException("pet não disponível para consulta");
        }

        exameRepository.delete(exameModel);
    }
}

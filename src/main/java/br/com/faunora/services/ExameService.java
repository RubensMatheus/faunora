package br.com.faunora.services;

import br.com.faunora.domain.dto.exames.ExameRecordDto;
import br.com.faunora.domain.enums.ExameTipo;
import br.com.faunora.domain.enums.UserTipo;
import br.com.faunora.domain.models.ExameModel;
import br.com.faunora.domain.models.PetModel;
import br.com.faunora.domain.models.UserModel;
import br.com.faunora.infra.exceptions.datas.DataIndisponivelException;
import br.com.faunora.infra.exceptions.datas.HorarioIndisponivelException;
import br.com.faunora.infra.exceptions.exames.ExameNaoEncontradoException;
import br.com.faunora.infra.exceptions.exames.NenhumExameEncontradoException;
import br.com.faunora.infra.exceptions.pets.PetIndisponivelException;
import br.com.faunora.infra.exceptions.pets.PetNaoEncontradoException;
import br.com.faunora.infra.exceptions.users.NenhumVeterinarioDisponivelException;
import br.com.faunora.infra.exceptions.users.UsuarioNaoEncontradoException;
import br.com.faunora.infra.exceptions.users.VeterinarioInvalidoException;
import br.com.faunora.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExameService {
    @Autowired
    private ExameRepository exameRepository;
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HorarioService horarioService;
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private DosagemRepository dosagemRepository;

    @Transactional
    public void saveExame(ExameRecordDto exameRecordDto) {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            throw new UsuarioNaoEncontradoException();
        }

        UserModel userModel = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(UsuarioNaoEncontradoException::new);

        PetModel petModel = petRepository.findById(exameRecordDto.pacienteId())
                .orElseThrow(PetNaoEncontradoException::new);

        if (!petRepository.findAllByTutor(userModel).contains(petModel)) {
            throw new PetNaoEncontradoException("pet não disponível para consulta");
        }

        if (!horarioService.isDateValid(exameRecordDto.data())) {
            throw new DataIndisponivelException();
        }

        if (!horarioService.isSlotValid(exameRecordDto.hora())) {
            throw new HorarioIndisponivelException();
        }

        if (!consultaRepository.findAllByPacienteAndDataAndHora(petModel, exameRecordDto.data(), exameRecordDto.hora()).isEmpty()
                || !exameRepository.findAllByPacienteAndDataAndHora(petModel, exameRecordDto.data(), exameRecordDto.hora()).isEmpty()
                || !dosagemRepository.findAllByPacienteAndDataAndHora(petModel, exameRecordDto.data(), exameRecordDto.hora()).isEmpty()) {
            throw new PetIndisponivelException();
        }

        ExameModel exameModel = new ExameModel();
        exameModel.setPaciente(petModel);
        exameModel.setTipo(exameRecordDto.tipo());
        exameModel.setData(exameRecordDto.data());
        exameModel.setHora(exameRecordDto.hora());

        List<UserModel> veterinarios = userRepository.findAllByTipo(UserTipo.VETERINARIO);

        for (UserModel veterinario : veterinarios) {
            if (horarioService.isSlotAvailableForVet(veterinario, exameModel.getData(), exameModel.getHora())) {
                exameModel.setVeterinario(veterinario);
                break;
            }
        }

        if (exameModel.getVeterinario() == null) {
            throw new NenhumVeterinarioDisponivelException();
        }

        exameRepository.save(exameModel);
    }

    public ExameModel findById(Long id) {
        return exameRepository.findById(id)
                .orElseThrow(ExameNaoEncontradoException::new);
    }

    public List<ExameModel> findAllByTutor() {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            throw new UsuarioNaoEncontradoException();
        }

        UserModel userModel = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(UsuarioNaoEncontradoException::new);

        List<ExameModel> exameModels = new ArrayList<>();

        for (PetModel petModel : petRepository.findAllByTutor(userModel)) {
            exameModels.addAll(exameRepository.findAllByPaciente(petModel));
        }

        if (exameModels.isEmpty()) {
            throw new NenhumExameEncontradoException();
        }

        return exameModels;
    }

    public List<ExameModel> findAllByVeterinario() {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            throw new UsuarioNaoEncontradoException();
        }

        UserModel veterinario = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(UsuarioNaoEncontradoException::new);

        if (veterinario.getTipo() != UserTipo.VETERINARIO) {
            throw new VeterinarioInvalidoException("usuário não é veterinário");
        }

        List<ExameModel> exameModels = exameRepository.findAllByVeterinario(veterinario);

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

        if (!petRepository.findAllByTutor(userModel).contains(paciente)) {
            throw new PetNaoEncontradoException("pet não disponível para exame");
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
        for (PetModel petModel : petRepository.findAllByTutor(userModel)) {
            exameModels.addAll(exameRepository.findAllByTipoAndPaciente(tipo, petModel));
        }

        if (exameModels.isEmpty()) {
            throw new NenhumExameEncontradoException();
        }

        return exameModels;
    }

    public List<ExameModel> findAnterioresVeterinario() {
        List<ExameModel> exameModels = this.findAllByVeterinario();

        for (ExameModel exameModel : exameModels) {
            LocalDateTime dateTime = LocalDateTime.of(exameModel.getData(), exameModel.getHora());

            if (dateTime.isAfter(LocalDateTime.now())) {
                exameModels.remove(exameModel);
            }
        }

        if (exameModels.isEmpty()) {
            throw new NenhumExameEncontradoException();
        }

        return exameModels;
    }

    public List<ExameModel> findAnteriores() {
        List<ExameModel> exameModels = this.findAllByTutor();


        for (ExameModel exameModel : exameModels) {
            LocalDateTime dateTime = LocalDateTime.of(exameModel.getData(), exameModel.getHora());

            if (dateTime.isAfter(LocalDateTime.now())) {
                exameModels.remove(exameModel);
            }
        }

        if (exameModels.isEmpty()) {
            throw new NenhumExameEncontradoException();
        }

        return exameModels;
    }

    public List<ExameModel> findMarcadosVeterinario() {
        List<ExameModel> exameModels = this.findAllByVeterinario();

        for (ExameModel exameModel : exameModels) {
            LocalDateTime dateTime = LocalDateTime.of(exameModel.getData(), exameModel.getHora());

            if (dateTime.isBefore(LocalDateTime.now())) {
                exameModels.remove(exameModel);
            }
        }

        if (exameModels.isEmpty()) {
            throw new NenhumExameEncontradoException();
        }

        return exameModels;
    }

    public List<ExameModel> findMarcados() {
        List<ExameModel> exameModels = this.findAllByTutor();

        for (ExameModel exameModel : exameModels) {
            LocalDateTime dateTime = LocalDateTime.of(exameModel.getData(), exameModel.getHora());

            if (dateTime.isBefore(LocalDateTime.now())) {
                exameModels.remove(exameModel);
            }
        }

        if (exameModels.isEmpty()) {
            throw new NenhumExameEncontradoException();
        }

        return exameModels;
    }

    public List<ExameModel> findAllByRandomVeterinario(String filter) {
        List<ExameModel> exameModelsByRandom = new ArrayList<>();

        for (ExameModel exameModel : this.findAllByVeterinario()) {
            if (exameModel.getTipo().toString().contains(filter) || exameModel.getData().toString().contains(filter) || exameModel.getHora().toString().contains(filter) || exameModel.getPaciente().getNome().contains(filter) || exameModel.getPaciente().getTutor().getNome().contains(filter)) {
                exameModelsByRandom.add(exameModel);
            }
        }

        if (exameModelsByRandom.isEmpty()) {
            throw new NenhumExameEncontradoException("nenhum exame correspondente encontrado");
        }

        return exameModelsByRandom;
    }

    public List<ExameModel> findAllByRandom(String filter) {
        List<ExameModel> exameModelsByRandom = new ArrayList<>();

        for (ExameModel exameModel : this.findAllByTutor()) {
            if (exameModel.getTipo().toString().contains(filter) || exameModel.getData().toString().contains(filter) || exameModel.getHora().toString().contains(filter) || exameModel.getPaciente().getNome().contains(filter) || exameModel.getVeterinario().getNome().contains(filter)) {
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

        if (!petRepository.findAllByTutor(userModel).contains(petModel)) {
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

        if (!petRepository.findAllByTutor(userModel).contains(exameModel.getPaciente())) {
            throw new PetNaoEncontradoException("pet não disponível para consulta");
        }

        exameRepository.delete(exameModel);
    }
}

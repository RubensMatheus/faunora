package br.com.faunora.services;

import br.com.faunora.domain.dto.PetRecordDto;
import br.com.faunora.domain.enums.PetSexo;
import br.com.faunora.domain.enums.PetTipo;
import br.com.faunora.domain.models.*;
import br.com.faunora.infra.exceptions.NenhumPetEncontradoException;
import br.com.faunora.infra.exceptions.PetNaoEncontradoException;
import br.com.faunora.infra.exceptions.UsuarioNaoEncontradoException;
import br.com.faunora.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.Instant;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void savePet(PetRecordDto petRecordDto) {
        PetModel petModel = new PetModel();
        BeanUtils.copyProperties(petRecordDto, petModel);
        petModel.setDosagens(new HashSet<>());
        petModel.setConsultas(new HashSet<>());
        petModel.setExames(new HashSet<>());

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            throw new UsuarioNaoEncontradoException();
        }

        UserModel userModel = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(UsuarioNaoEncontradoException::new);

        petModel.setTutor(userModel);

        petRepository.save(petModel);
    }

    public PetModel findById(UUID id) {
        return petRepository.findById(id)
                .orElseThrow(PetNaoEncontradoException::new);
    }

    public List<PetModel> findAll() {
        List<PetModel> petModels = petRepository.findAll();

        if (petModels.isEmpty()) {
            throw new NenhumPetEncontradoException();
        }

        return petModels;
    }

    public List<PetModel> findAllByProximasVacinas() {
        List<PetModel> petModelsByProximasVacinas = new ArrayList<>();

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            throw new UsuarioNaoEncontradoException();
        }

        UserModel userModel = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(UsuarioNaoEncontradoException::new);

        List<PetModel> petModels = userModel.getPets();

        for (PetModel petModel : petModels) {
            for (DosagemModel dosagemModel : petModel.getDosagens()) {
                if (dosagemModel.getData().isAfter(ChronoLocalDate.from(Instant.now()))) {
                    petModelsByProximasVacinas.add(petModel);
                }
            }
        }

        if (petModelsByProximasVacinas.isEmpty()) {
            throw new NenhumPetEncontradoException("nenhum pet com vacinas próximas encontrado");
        }

        return petModelsByProximasVacinas;
    }

    public List<PetModel> findAllByProximasConsultas() {
        List<PetModel> petModelsByProximasConsultas = new ArrayList<>();

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            throw new UsuarioNaoEncontradoException();
        }

        UserModel userModel = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(UsuarioNaoEncontradoException::new);

        List<PetModel> petModels = userModel.getPets();

        for (PetModel petModel : petModels) {
            for (ConsultaModel consultaModel : petModel.getConsultas()) {
                if (consultaModel.getData().isAfter(ChronoLocalDate.from(Instant.now()))) {
                    petModelsByProximasConsultas.add(petModel);
                }
            }
        }

        if (petModelsByProximasConsultas.isEmpty()) {
            throw new NenhumPetEncontradoException("nenhum pet com consultas próximas encontrado");
        }

        return petModelsByProximasConsultas;
    }

    public List<PetModel> findAllByProximosExames() {
        List<PetModel> petModelsByProximosExames = new ArrayList<>();

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            throw new UsuarioNaoEncontradoException();
        }

        UserModel userModel = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(UsuarioNaoEncontradoException::new);

        List<PetModel> petModels = userModel.getPets();

        for (PetModel petModel : petModels) {
            for (ExameModel exameModel : petModel.getExames()) {
                if (exameModel.getData().isAfter(ChronoLocalDate.from(Instant.now()))) {
                    petModelsByProximosExames.add(petModel);
                }
            }
        }

        if (petModelsByProximosExames.isEmpty()) {
            throw new NenhumPetEncontradoException("nenhum pet com exames próximos encontrado");
        }

        return petModelsByProximosExames;
    }

    public List<PetModel> findAllByRandom(String filter) {
        List<PetModel> petModelsByRandom = new ArrayList<>();

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            throw new UsuarioNaoEncontradoException();
        }

        UserModel userModel = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(UsuarioNaoEncontradoException::new);

        List<PetModel> petModels = userModel.getPets();

        for (PetModel petModel : petModels) {
            if (petModel.getNome().contains(filter) || petModel.getTipo().toString().contains(filter) || petModel.getSexo().toString().contains(filter) || petModel.getDataNascimento().toString().contains(filter) || Double.toString(petModel.getPeso()).contains(filter)) {
                petModelsByRandom.add(petModel);
            }
        }

        if (petModelsByRandom.isEmpty()) {
            throw new NenhumPetEncontradoException("nenhum pet correspondente encontrado");
        }

        return petModelsByRandom;
    }

    @Transactional
    public void updatePet(UUID id, PetRecordDto petRecordDto) {
        PetModel petModel = petRepository.findById(id)
                .orElseThrow(PetNaoEncontradoException::new);

        BeanUtils.copyProperties(petRecordDto, petModel);

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            throw new UsuarioNaoEncontradoException();
        }

        UserModel userModel = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(UsuarioNaoEncontradoException::new);

        petModel.setTutor(userModel);

        petRepository.save(petModel);
    }

    @Transactional
    public void updatePetNome(UUID id, String nome) {
        PetModel petModel = petRepository.findById(id)
                .orElseThrow(PetNaoEncontradoException::new);

        petModel.setNome(nome);
    }

    @Transactional
    public void updatePetTipo(UUID id, PetTipo tipo) {
        PetModel petModel = petRepository.findById(id)
                .orElseThrow(PetNaoEncontradoException::new);

        petModel.setTipo(tipo);
    }

    @Transactional
    public void updatePetPeso(UUID id, Double peso) {
        PetModel petModel = petRepository.findById(id)
                .orElseThrow(PetNaoEncontradoException::new);

        petModel.setPeso(peso);
    }

    @Transactional
    public void updatePetSexo(UUID id, PetSexo sexo) {
        PetModel petModel = petRepository.findById(id)
                .orElseThrow(PetNaoEncontradoException::new);

        petModel.setSexo(sexo);
    }

    @Transactional
    public void updatePetNascimento(UUID id, LocalDate nascimento) {
        PetModel petModel = petRepository.findById(id)
                .orElseThrow(PetNaoEncontradoException::new);

        petModel.setDataNascimento(nascimento);
    }

    @Transactional
    public void deleteById(UUID id) {
        PetModel petModel = petRepository.findById(id)
                .orElseThrow(PetNaoEncontradoException::new);

        petRepository.delete(petModel);
    }
}


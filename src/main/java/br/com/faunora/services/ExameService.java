package br.com.faunora.services;

import br.com.faunora.domain.dto.ExameRecordDto;
import br.com.faunora.domain.enums.ExameTipo;
import br.com.faunora.domain.models.ExameModel;
import br.com.faunora.domain.models.PetModel;
import br.com.faunora.infra.exceptions.ExameNaoEncontradoException;
import br.com.faunora.infra.exceptions.NenhumExameEncontradoException;
import br.com.faunora.infra.exceptions.PetNaoEncontradoException;
import br.com.faunora.repositories.ExameRepository;
import br.com.faunora.repositories.PetRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ExameService {
    @Autowired
    private ExameRepository exameRepository;
    @Autowired
    private PetRepository petRepository;

    @Transactional
    public void saveExame(ExameRecordDto exameRecordDto) {
        PetModel petModel = petRepository.findById(exameRecordDto.pacienteId())
                .orElseThrow(PetNaoEncontradoException::new);

        ExameModel exameModel = new ExameModel();
        exameModel.setPaciente(petModel);
        exameModel.setTipo(exameRecordDto.tipo());
        exameModel.setData(exameRecordDto.data());
        exameModel.setHora(exameRecordDto.hora());

        exameRepository.save(exameModel);
    }

    public ExameModel findById(Long id) {
        return exameRepository.findById(id)
                .orElseThrow(ExameNaoEncontradoException::new);
    }

    public List<ExameModel> findAll() {
        List<ExameModel> exameModels = exameRepository.findAll();

        if (exameModels.isEmpty()) {
            throw new NenhumExameEncontradoException();
        }

        return exameModels;
    }

    public List<ExameModel> findAllByPaciente(UUID pacienteId) {
        PetModel paciente = petRepository.findById(pacienteId)
                .orElseThrow(PetNaoEncontradoException::new);

        List<ExameModel> exameModels = exameRepository.findAllByPaciente(paciente);

        if (exameModels.isEmpty()) {
            throw new NenhumExameEncontradoException();
        }

        return exameModels;
    }

    public List<ExameModel> findAllByTipo(ExameTipo tipo) {
        List<ExameModel> exameModels = exameRepository.findAllByTipo(tipo);

        if (exameModels.isEmpty()) {
            throw new NenhumExameEncontradoException();
        }

        return exameModels;
    }

    public List<ExameModel> findAnteriores() {
        List<ExameModel> exameModels = exameRepository.findAll();

        exameModels.removeIf(exameModel -> exameModel.getData().isAfter(ChronoLocalDate.from(LocalDate.now())));

        if (exameModels.isEmpty()) {
            throw new NenhumExameEncontradoException();
        }

        return exameModels;
    }

    public List<ExameModel> findMarcados() {
        List<ExameModel> exameModels = exameRepository.findAll();

        exameModels.removeIf(exameModel -> exameModel.getData().isBefore(ChronoLocalDate.from(LocalDate.now())));

        if (exameModels.isEmpty()) {
            throw new NenhumExameEncontradoException();
        }

        return exameModels;
    }

    public List<ExameModel> findAllByRandom(String filter) {
        List<ExameModel> exameModelsByRandom = new ArrayList<>();

        for (ExameModel exameModel : exameRepository.findAll()) {
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
        ExameModel exameModel = exameRepository.findById(id)
                .orElseThrow(ExameNaoEncontradoException::new);

        PetModel petModel = petRepository.findById(exameRecordDto.pacienteId())
                .orElseThrow(PetNaoEncontradoException::new);

        exameModel.setPaciente(petModel);
        exameModel.setTipo(exameRecordDto.tipo());
        exameModel.setData(exameRecordDto.data());
        exameModel.setHora(exameRecordDto.hora());

        exameRepository.save(exameModel);
    }

    @Transactional
    public void deleteById(Long id) {
        ExameModel exameModel = exameRepository.findById(id)
                .orElseThrow(ExameNaoEncontradoException::new);

        exameRepository.delete(exameModel);
    }
}

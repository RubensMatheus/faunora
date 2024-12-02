package br.com.faunora.services;

import br.com.faunora.domain.dto.DosagemRecordDto;
import br.com.faunora.domain.enums.DosagemTipo;
import br.com.faunora.domain.models.DosagemModel;
import br.com.faunora.domain.models.PetModel;
import br.com.faunora.infra.exceptions.DosagemNaoEncontradaException;
import br.com.faunora.infra.exceptions.NenhumaDosagemEncontradaException;
import br.com.faunora.infra.exceptions.PetNaoEncontradoException;
import br.com.faunora.repositories.DosagemRepository;
import br.com.faunora.repositories.PetRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DosagemService {
    @Autowired
    private DosagemRepository dosagemRepository;
    @Autowired
    private PetRepository petRepository;

    @Transactional
    public void saveDosagem(DosagemRecordDto dosagemRecordDto) {
        PetModel petModel = petRepository.findById(dosagemRecordDto.pacienteId())
                .orElseThrow(PetNaoEncontradoException::new);

        DosagemModel dosagemModel = new DosagemModel();
        dosagemModel.setPaciente(petModel);
        dosagemModel.setTipo(dosagemRecordDto.tipo());
        dosagemModel.setData(dosagemRecordDto.data());
        dosagemModel.setHora(dosagemRecordDto.hora());

        dosagemRepository.save(dosagemModel);
    }

    public DosagemModel findById(Long id) {
        return dosagemRepository.findById(id)
                .orElseThrow(DosagemNaoEncontradaException::new);
    }

    public List<DosagemModel> findAll() {
        List<DosagemModel> dosagemModels = dosagemRepository.findAll();

        if (dosagemModels.isEmpty()) {
            throw new NenhumaDosagemEncontradaException();
        }

        return dosagemModels;
    }

    public List<DosagemModel> findAllByPaciente(Long pacienteId) {
        PetModel paciente = petRepository.findById(pacienteId)
                .orElseThrow(PetNaoEncontradoException::new);

        List<DosagemModel> dosagemModels = dosagemRepository.findAllByPaciente(paciente);

        if (dosagemModels.isEmpty()) {
            throw new NenhumaDosagemEncontradaException();
        }

        return dosagemModels;
    }

    public List<DosagemModel> findAllByTipo(DosagemTipo tipo) {
        List<DosagemModel> dosagemModels = dosagemRepository.findAllByTipo(tipo);

        if (dosagemModels.isEmpty()) {
            throw new NenhumaDosagemEncontradaException();
        }

        return dosagemModels;
    }

    public List<DosagemModel> findAnteriores() {
        List<DosagemModel> dosagemModels = dosagemRepository.findAll();

        dosagemModels.removeIf(dosagemModel -> dosagemModel.getData().isAfter(ChronoLocalDate.from(LocalDate.now())));

        if (dosagemModels.isEmpty()) {
            throw new NenhumaDosagemEncontradaException();
        }

        return dosagemModels;
    }

    public List<DosagemModel> findMarcadas() {
        List<DosagemModel> dosagemModels = dosagemRepository.findAll();

        dosagemModels.removeIf(dosagemModel -> dosagemModel.getData().isBefore(ChronoLocalDate.from(LocalDate.now())));

        if (dosagemModels.isEmpty()) {
            throw new NenhumaDosagemEncontradaException();
        }

        return dosagemModels;
    }

    public List<DosagemModel> findAllByRandom(String filter) {
        List<DosagemModel> dosagemModelsByRandom = new ArrayList<>();

        for (DosagemModel dosagemModel : dosagemRepository.findAll()) {
            if (dosagemModel.getPaciente().getNome().contains(filter) || dosagemModel.getTipo().toString().contains(filter) || dosagemModel.getData().toString().contains(filter) || dosagemModel.getHora().toString().contains(filter)) {
                dosagemModelsByRandom.add(dosagemModel);
            }
        }

        if (dosagemModelsByRandom.isEmpty()) {
            throw new NenhumaDosagemEncontradaException("nenhuma dosagem correspondente encontrada");
        }

        return dosagemModelsByRandom;
    }

    @Transactional
    public void updateDosagem(Long id, DosagemRecordDto dosagemRecordDto) {
        DosagemModel dosagemModel = dosagemRepository.findById(id)
                .orElseThrow(DosagemNaoEncontradaException::new);

        PetModel petModel = petRepository.findById(dosagemRecordDto.pacienteId())
                .orElseThrow(PetNaoEncontradoException::new);

        dosagemModel.setPaciente(petModel);
        dosagemModel.setTipo(dosagemRecordDto.tipo());
        dosagemModel.setData(dosagemRecordDto.data());
        dosagemModel.setHora(dosagemRecordDto.hora());

        dosagemRepository.save(dosagemModel);
    }

    @Transactional
    public void deleteById(Long id) {
        DosagemModel dosagemModel = dosagemRepository.findById(id)
                .orElseThrow(DosagemNaoEncontradaException::new);

        dosagemRepository.delete(dosagemModel);
    }
}

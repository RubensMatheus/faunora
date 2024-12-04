package br.com.faunora.services;

import br.com.faunora.domain.dto.dosagens.DosagemRecordDto;
import br.com.faunora.domain.enums.DosagemTipo;
import br.com.faunora.domain.enums.UserTipo;
import br.com.faunora.domain.models.DosagemModel;
import br.com.faunora.domain.models.PetModel;
import br.com.faunora.domain.models.UserModel;
import br.com.faunora.infra.exceptions.datas.DataIndisponivelException;
import br.com.faunora.infra.exceptions.datas.HorarioIndisponivelException;
import br.com.faunora.infra.exceptions.dosagens.DosagemNaoEncontradaException;
import br.com.faunora.infra.exceptions.dosagens.NenhumaDosagemEncontradaException;
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
public class DosagemService {
    @Autowired
    private DosagemRepository dosagemRepository;
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HorarioService horarioService;
    @Autowired
    private ExameRepository exameRepository;
    @Autowired
    private ConsultaRepository consultaRepository;

    @Transactional
    public void saveDosagem(DosagemRecordDto dosagemRecordDto) {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            throw new UsuarioNaoEncontradoException();
        }

        UserModel userModel = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(UsuarioNaoEncontradoException::new);

        PetModel petModel = petRepository.findById(dosagemRecordDto.pacienteId())
                .orElseThrow(PetNaoEncontradoException::new);

        if (!petRepository.findAllByTutor(userModel).contains(petModel)) {
            throw new PetNaoEncontradoException("pet não disponível para dosagem");
        }

        if (!horarioService.isDateValid(dosagemRecordDto.data())) {
            throw new DataIndisponivelException();
        }

        if (!horarioService.isSlotValid(dosagemRecordDto.hora())) {
            throw new HorarioIndisponivelException();
        }

        if (!consultaRepository.findAllByPacienteAndDataAndHora(petModel, dosagemRecordDto.data(), dosagemRecordDto.hora()).isEmpty()
                || !exameRepository.findAllByPacienteAndDataAndHora(petModel, dosagemRecordDto.data(), dosagemRecordDto.hora()).isEmpty()
                || !dosagemRepository.findAllByPacienteAndDataAndHora(petModel, dosagemRecordDto.data(), dosagemRecordDto.hora()).isEmpty()) {
            throw new PetIndisponivelException();
        }

        DosagemModel dosagemModel = new DosagemModel();
        dosagemModel.setPaciente(petModel);
        dosagemModel.setTipo(dosagemRecordDto.tipo());
        dosagemModel.setData(dosagemRecordDto.data());
        dosagemModel.setHora(dosagemRecordDto.hora());

        List<UserModel> veterinarios = userRepository.findAllByTipo(UserTipo.VETERINARIO);

        for (UserModel veterinario : veterinarios) {
            if (horarioService.isSlotAvailableForVet(veterinario, dosagemModel.getData(), dosagemModel.getHora())) {
                dosagemModel.setVeterinario(veterinario);
                break;
            }
        }

        if (dosagemModel.getVeterinario() == null) {
            throw new NenhumVeterinarioDisponivelException();
        }

        dosagemRepository.save(dosagemModel);
    }

    public DosagemModel findById(Long id) {
        return dosagemRepository.findById(id)
                .orElseThrow(DosagemNaoEncontradaException::new);
    }

    public List<DosagemModel> findAllByTutor() {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            throw new UsuarioNaoEncontradoException();
        }

        UserModel userModel = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(UsuarioNaoEncontradoException::new);

        List<PetModel> petModels = petRepository.findAllByTutor(userModel);

        List<DosagemModel> dosagemModels = new ArrayList<>();

        for (PetModel petModel : petModels) {
            dosagemModels.addAll(dosagemRepository.findAllByPaciente(petModel));
        }

        if (dosagemModels.isEmpty()) {
            throw new NenhumaDosagemEncontradaException();
        }

        return dosagemModels;
    }

    public List<DosagemModel> findAllByVeterinario() {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            throw new UsuarioNaoEncontradoException();
        }

        UserModel userModel = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(UsuarioNaoEncontradoException::new);

        if (userModel.getTipo() != UserTipo.VETERINARIO) {
            throw new VeterinarioInvalidoException("usuário não é veterinário");
        }

        List<DosagemModel> dosagemModels = dosagemRepository.findAllByVeterinario(userModel);

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

    public List<DosagemModel> findAnterioresVeterinario() {
        List<DosagemModel> dosagemModels = this.findAllByVeterinario();

        for (DosagemModel dosagemModel : dosagemModels) {
            LocalDateTime dataHora = LocalDateTime.of(dosagemModel.getData(), dosagemModel.getHora());

            if (dataHora.isAfter(LocalDateTime.now())) {
                dosagemModels.remove(dosagemModel);
            }
        }

        if (dosagemModels.isEmpty()) {
            throw new NenhumaDosagemEncontradaException();
        }

        return dosagemModels;
    }

    public List<DosagemModel> findAnteriores() {
        List<DosagemModel> dosagemModels = this.findAllByTutor();

        for (DosagemModel dosagemModel : dosagemModels) {
            LocalDateTime dataHora = LocalDateTime.of(dosagemModel.getData(), dosagemModel.getHora());

            if (dataHora.isAfter(LocalDateTime.now())) {
                dosagemModels.remove(dosagemModel);
            }
        }

        if (dosagemModels.isEmpty()) {
            throw new NenhumaDosagemEncontradaException();
        }

        return dosagemModels;
    }

    public List<DosagemModel> findMarcadasVeterinario() {
        List<DosagemModel> dosagemModels = this.findAllByVeterinario();

        for (DosagemModel dosagemModel : dosagemModels) {
            LocalDateTime dataHora = LocalDateTime.of(dosagemModel.getData(), dosagemModel.getHora());

            if (dataHora.isBefore(LocalDateTime.now())) {
                dosagemModels.remove(dosagemModel);
            }
        }

        if (dosagemModels.isEmpty()) {
            throw new NenhumaDosagemEncontradaException();
        }

        return dosagemModels;
    }

    public List<DosagemModel> findMarcadas() {
        List<DosagemModel> dosagemModels = this.findAllByTutor();

        for (DosagemModel dosagemModel : dosagemModels) {
            LocalDateTime dataHora = LocalDateTime.of(dosagemModel.getData(), dosagemModel.getHora());

            if (dataHora.isBefore(LocalDateTime.now())) {
                dosagemModels.remove(dosagemModel);
            }
        }

        if (dosagemModels.isEmpty()) {
            throw new NenhumaDosagemEncontradaException();
        }

        return dosagemModels;
    }

    public List<DosagemModel> findAllByRandomVeterinario(String filter) {
        List<DosagemModel> dosagemModelsByRandom = new ArrayList<>();

        for (DosagemModel dosagemModel : this.findAllByVeterinario()) {
            if (dosagemModel.getPaciente().getNome().contains(filter) || dosagemModel.getTipo().toString().contains(filter) || dosagemModel.getData().toString().contains(filter) || dosagemModel.getHora().toString().contains(filter) || dosagemModel.getPaciente().getTutor().getNome().contains(filter)) {
                dosagemModelsByRandom.add(dosagemModel);
            }
        }

        if (dosagemModelsByRandom.isEmpty()) {
            throw new NenhumaDosagemEncontradaException("nenhuma dosagem correspondente encontrada");
        }

        return dosagemModelsByRandom;
    }

    public List<DosagemModel> findAllByRandom(String filter) {
        List<DosagemModel> dosagemModelsByRandom = new ArrayList<>();

        for (DosagemModel dosagemModel : this.findAllByTutor()) {
            if (dosagemModel.getPaciente().getNome().contains(filter) || dosagemModel.getTipo().toString().contains(filter) || dosagemModel.getData().toString().contains(filter) || dosagemModel.getHora().toString().contains(filter) || dosagemModel.getVeterinario().getNome().contains(filter)) {
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

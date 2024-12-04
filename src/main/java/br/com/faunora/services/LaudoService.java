package br.com.faunora.services;

import br.com.faunora.domain.dto.exames.LaudoRecordDto;
import br.com.faunora.domain.enums.UserTipo;
import br.com.faunora.domain.models.ExameModel;
import br.com.faunora.domain.models.LaudoModel;
import br.com.faunora.domain.models.UserModel;
import br.com.faunora.infra.exceptions.exames.CadastroLaudoInvalidoException;
import br.com.faunora.infra.exceptions.exames.ExameNaoEncontradoException;
import br.com.faunora.infra.exceptions.exames.LaudoNaoEncontradoException;
import br.com.faunora.infra.exceptions.users.UsuarioNaoEncontradoException;
import br.com.faunora.infra.exceptions.users.VeterinarioInvalidoException;
import br.com.faunora.repositories.ExameRepository;
import br.com.faunora.repositories.LaudoRepository;
import br.com.faunora.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LaudoService {
    @Autowired
    private LaudoRepository laudoRepository;
    @Autowired
    private ExameRepository exameRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void saveLaudo(LaudoRecordDto laudoRecordDto) {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            throw new UsuarioNaoEncontradoException();
        }

        UserModel userModel = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(UsuarioNaoEncontradoException::new);

        if (userModel.getTipo() != UserTipo.VETERINARIO) {
            throw new VeterinarioInvalidoException("usuário não é veterinário");
        }

        ExameModel exameModel = exameRepository.findById(laudoRecordDto.exameId())
                .orElseThrow(ExameNaoEncontradoException::new);

        if (!exameModel.getVeterinario().equals(userModel)) {
            throw new VeterinarioInvalidoException("apenas o veterinário responsável pelo exame pode atribui-la um laudo");
        }

        LocalDateTime horaExame = LocalDateTime.of(exameModel.getData(), exameModel.getHora());

        if (horaExame.isAfter(LocalDateTime.now())) {
            throw new CadastroLaudoInvalidoException();
        }

        LaudoModel laudoModel = new LaudoModel();
        laudoModel.setExame(exameModel);
        laudoModel.setPrescricao(laudoRecordDto.prescricao());
        laudoModel.setValidade(laudoRecordDto.validade());

        laudoRepository.save(laudoModel);
    }

    public LaudoModel findById(Long id) {
        return laudoRepository.findById(id)
                .orElseThrow(LaudoNaoEncontradoException::new);
    }

    @Transactional
    public void updateById(Long id, LaudoRecordDto laudoRecordDto) {
        LaudoModel laudoModel = laudoRepository.findById(id)
                .orElseThrow(LaudoNaoEncontradoException::new);

        ExameModel exameModel = exameRepository.findById(laudoRecordDto.exameId())
                .orElseThrow(ExameNaoEncontradoException::new);

        laudoModel.setExame(exameModel);
        laudoModel.setPrescricao(laudoRecordDto.prescricao());
        laudoModel.setValidade(laudoRecordDto.validade());

        laudoRepository.save(laudoModel);
    }

    @Transactional
    public void deleteById(Long id) {
        LaudoModel laudoModel = laudoRepository.findById(id)
                .orElseThrow(LaudoNaoEncontradoException::new);

        laudoRepository.delete(laudoModel);
    }
}

package br.com.faunora.services;

import br.com.faunora.domain.dto.LaudoRecordDto;
import br.com.faunora.domain.models.ExameModel;
import br.com.faunora.domain.models.LaudoModel;
import br.com.faunora.infra.exceptions.ExameNaoEncontradoException;
import br.com.faunora.infra.exceptions.LaudoNaoEncontradoException;
import br.com.faunora.repositories.ExameRepository;
import br.com.faunora.repositories.LaudoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LaudoService {
    @Autowired
    private LaudoRepository laudoRepository;
    @Autowired
    private ExameRepository exameRepository;

    @Transactional
    public void saveLaudo(LaudoRecordDto laudoRecordDto) {
        ExameModel exameModel = exameRepository.findById(laudoRecordDto.exameId())
                .orElseThrow(ExameNaoEncontradoException::new);

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

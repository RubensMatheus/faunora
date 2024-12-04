package br.com.faunora.services;

import br.com.faunora.domain.dto.LaudoRecordDto;
import br.com.faunora.domain.dto.ReceitaRecordDto;
import br.com.faunora.domain.models.ConsultaModel;
import br.com.faunora.domain.models.ExameModel;
import br.com.faunora.domain.models.LaudoModel;
import br.com.faunora.domain.models.ReceitaModel;
import br.com.faunora.infra.exceptions.ConsultaNaoEncontradaException;
import br.com.faunora.infra.exceptions.ExameNaoEncontradoException;
import br.com.faunora.infra.exceptions.LaudoNaoEncontradoException;
import br.com.faunora.infra.exceptions.ReceitaNaoEncontradaException;
import br.com.faunora.repositories.ConsultaRepository;
import br.com.faunora.repositories.ExameRepository;
import br.com.faunora.repositories.LaudoRepository;
import br.com.faunora.repositories.ReceitaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceitaService {
    @Autowired
    private ReceitaRepository receitaRepository;
    @Autowired
    private ConsultaRepository consultaRepository;

    @Transactional
    public void saveReceita(ReceitaRecordDto receitaRecordDto) {
        ConsultaModel consulta = consultaRepository.findById(receitaRecordDto.consultaId())
                .orElseThrow(ConsultaNaoEncontradaException::new);

        ReceitaModel receita = new ReceitaModel();
        receita.setConsulta(consulta);
        receita.setPrescricao(receitaRecordDto.prescricao());
        receita.setRecomendacao(receitaRecordDto.recomendacao());

        receitaRepository.save(receita);
    }

    public ReceitaModel findById(Long id) {
        return receitaRepository.findById(id)
                .orElseThrow(ReceitaNaoEncontradaException::new);
    }

    @Transactional
    public void updateById(Long id, ReceitaRecordDto receitaRecordDto) {
        ReceitaModel receita = receitaRepository.findById(id)
                .orElseThrow(ReceitaNaoEncontradaException::new);

        ConsultaModel consulta = consultaRepository.findById(receitaRecordDto.consultaId())
                .orElseThrow(ConsultaNaoEncontradaException::new);

        receita.setConsulta(consulta);
        receita.setPrescricao(receitaRecordDto.prescricao());
        receita.setRecomendacao(receitaRecordDto.recomendacao());

        receitaRepository.save(receita);
    }

    @Transactional
    public void deleteById(Long id) {
        ReceitaModel receita = receitaRepository.findById(id)
                .orElseThrow(ReceitaNaoEncontradaException::new);

        receitaRepository.delete(receita);
    }
}

package br.com.faunora.services;

import br.com.faunora.domain.dto.consultas.ReceitaRecordDto;
import br.com.faunora.domain.enums.UserTipo;
import br.com.faunora.domain.models.*;
import br.com.faunora.infra.exceptions.consultas.CadastroReceitaInvalidoException;
import br.com.faunora.infra.exceptions.consultas.ConsultaNaoEncontradaException;
import br.com.faunora.infra.exceptions.consultas.ReceitaNaoEncontradaException;
import br.com.faunora.infra.exceptions.users.UsuarioNaoEncontradoException;
import br.com.faunora.infra.exceptions.users.VeterinarioInvalidoException;
import br.com.faunora.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReceitaService {
    @Autowired
    private ReceitaRepository receitaRepository;
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void saveReceita(ReceitaRecordDto receitaRecordDto) {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            throw new UsuarioNaoEncontradoException();
        }

        UserModel userModel = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(UsuarioNaoEncontradoException::new);

        if (userModel.getTipo() != UserTipo.VETERINARIO) {
            throw new VeterinarioInvalidoException("usuário não é veterinário");
        }

        ConsultaModel consulta = consultaRepository.findById(receitaRecordDto.consultaId())
                .orElseThrow(ConsultaNaoEncontradaException::new);

        if (!consulta.getVeterinario().equals(userModel)) {
            throw new VeterinarioInvalidoException("apenas o veterinário responsável pela consulta pode atribui-la uma receita");
        }

        LocalDateTime horaConsulta = LocalDateTime.of(consulta.getData(), consulta.getHora());

        if (horaConsulta.isAfter(LocalDateTime.now())) {
            throw new CadastroReceitaInvalidoException();
        }

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

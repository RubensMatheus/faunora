package br.com.faunora.services;

import br.com.faunora.domain.dto.produtos.PedidoRecordDto;
import br.com.faunora.domain.dto.produtos.UpdatePedidoRecordDto;
import br.com.faunora.domain.enums.PedidoStatus;
import br.com.faunora.domain.models.PedidoModel;
import br.com.faunora.domain.models.ProdutoModel;
import br.com.faunora.infra.exceptions.produtos.CancelamentoImpossivelException;
import br.com.faunora.infra.exceptions.produtos.NenhumProdutoEncontradoException;
import br.com.faunora.infra.exceptions.produtos.PedidoNaoEncontradoException;
import br.com.faunora.infra.exceptions.produtos.ProdutoNaoEncontradoException;
import br.com.faunora.repositories.PedidoRepository;
import br.com.faunora.repositories.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public void savePedido(PedidoRecordDto pedidoRecordDto) {
        ProdutoModel produtoModel = produtoRepository.findById(pedidoRecordDto.produtoId())
                .orElseThrow(ProdutoNaoEncontradoException::new);

        PedidoModel pedidoModel = new PedidoModel();
        pedidoModel.setStatus(PedidoStatus.PENDENTE);
        pedidoModel.setProduto(produtoModel);
        pedidoModel.setDataCriacao(LocalDate.now());

        pedidoRepository.save(pedidoModel);
    }

    public PedidoModel findById(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(PedidoNaoEncontradoException::new);
    }

    public List<PedidoModel> findAll() {
        List<PedidoModel> pedidoModels = pedidoRepository.findAll();

        if (pedidoModels.isEmpty()) {
            throw new NenhumProdutoEncontradoException();
        }

        return pedidoModels;
    }

    @Transactional
    public void updatePedido(Long id, UpdatePedidoRecordDto updatePedidoRecordDto) {
        PedidoModel pedidoModel = pedidoRepository.findById(id)
                .orElseThrow(PedidoNaoEncontradoException::new);

        ProdutoModel produtoModel = produtoRepository.findById(updatePedidoRecordDto.produtoId())
                .orElseThrow(ProdutoNaoEncontradoException::new);

        BeanUtils.copyProperties(updatePedidoRecordDto, pedidoModel);

        produtoRepository.save(produtoModel);
    }

    @Transactional
    public void cancelarPedido(Long id) {
        PedidoModel pedidoModel = pedidoRepository.findById(id)
                .orElseThrow(PedidoNaoEncontradoException::new);

        if (!(pedidoModel.getStatus() == PedidoStatus.PENDENTE)) {
            throw new CancelamentoImpossivelException();
        }

        pedidoModel.setStatus(PedidoStatus.CANCELADO);

        pedidoRepository.save(pedidoModel);
    }

    @Transactional
    public void deleteById(Long id) {
        PedidoModel pedidoModel = pedidoRepository.findById(id)
                .orElseThrow(PedidoNaoEncontradoException::new);

        pedidoRepository.delete(pedidoModel);
    }

    @Transactional
    @Scheduled(cron = "0 0 0 * * ?")
    public void expirarPedidos() {
        List<PedidoModel> pedidoModelsExpiraveis = pedidoRepository.findAllByStatus(PedidoStatus.PENDENTE);

        for (PedidoModel pedidoModel : pedidoModelsExpiraveis) {
            if (pedidoModel.getDataCriacao().plusDays(30).isBefore(LocalDate.now()) || pedidoModel.getDataCriacao().plusDays(30).isEqual(LocalDate.now())) {
                pedidoModel.setStatus(PedidoStatus.EXPIRADO);
            }
        }

        pedidoRepository.saveAll(pedidoModelsExpiraveis);
    }
}

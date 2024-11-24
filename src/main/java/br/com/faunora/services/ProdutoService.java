package br.com.faunora.services;

import br.com.faunora.domain.dto.ProdutoRecordDto;
import br.com.faunora.domain.models.ProdutoModel;
import br.com.faunora.infra.exceptions.NenhumProdutoEncontradoException;
import br.com.faunora.infra.exceptions.ProdutoNaoEncontradoException;
import br.com.faunora.repositories.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public ProdutoModel saveProduto(ProdutoRecordDto produtoRecordDto) {
        ProdutoModel produtoModel = new ProdutoModel();
        BeanUtils.copyProperties(produtoRecordDto, produtoModel);

        return produtoRepository.save(produtoModel);
    }

    public ProdutoModel findById(UUID id) {
        return produtoRepository.findById(id)
                .orElseThrow(ProdutoNaoEncontradoException::new);
    }

    public List<ProdutoModel> findAll() {
        List<ProdutoModel> produtoModels = produtoRepository.findAll();

        if (produtoModels.isEmpty()) {
            throw new NenhumProdutoEncontradoException();
        }

        return produtoModels;
    }

    @Transactional
    public ProdutoModel updateProduto(UUID id, ProdutoRecordDto produtoRecordDto) {
        ProdutoModel produtoModel = produtoRepository.findById(id)
                .orElseThrow(ProdutoNaoEncontradoException::new);

        BeanUtils.copyProperties(produtoRecordDto, produtoModel);
        return produtoRepository.save(produtoModel);
    }

    @Transactional
    public void deleteById(UUID id) {
        ProdutoModel produtoModel = produtoRepository.findById(id)
                .orElseThrow(ProdutoNaoEncontradoException::new);

        produtoRepository.delete(produtoModel);
    }
}

package br.com.faunora.services;

import br.com.faunora.domain.dto.ProdutoRecordDto;
import br.com.faunora.domain.enums.ProdutoTipo;
import br.com.faunora.domain.models.ProdutoModel;
import br.com.faunora.infra.exceptions.NenhumProdutoEncontradoException;
import br.com.faunora.infra.exceptions.PetNaoEncontradoException;
import br.com.faunora.infra.exceptions.ProdutoNaoEncontradoException;
import br.com.faunora.repositories.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public void saveProduto(ProdutoRecordDto produtoRecordDto) {
        ProdutoModel produtoModel = new ProdutoModel();
        produtoModel.setCategoria(produtoRecordDto.tipo());
        produtoModel.setDescricao(produtoRecordDto.descricao());
        produtoModel.setPreco(produtoRecordDto.preco());
        produtoModel.setNome(produtoRecordDto.nome());
        produtoModel.setMarca(produtoRecordDto.marca());

        produtoRepository.save(produtoModel);
    }

    public ProdutoModel findById(Long id) {
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

    public List<ProdutoModel> findAllByCategoria(ProdutoTipo categoria) {
        List<ProdutoModel> produtoModels = produtoRepository.findByCategoria(categoria);

        if (produtoModels.isEmpty()) {
            throw new NenhumProdutoEncontradoException();
        }

        return produtoModels;
    }

    public List<ProdutoModel> findAllByRandom(String filter) {
        List<ProdutoModel> produtoModelsByRandom = new ArrayList<>();

        for (ProdutoModel produtoModel : produtoRepository.findAll()) {
            if (produtoModel.getNome().contains(filter) || produtoModel.getMarca().contains(filter) || produtoModel.getCategoria().toString().contains(filter) || produtoModel.getPreco().toString().contains(filter)) {
                produtoModelsByRandom.add(produtoModel);
            }
        }

        if (produtoModelsByRandom.isEmpty()) {
            throw new NenhumProdutoEncontradoException("nenhum produto correspondente encontrado");
        }

        return produtoModelsByRandom;
    }

    @Transactional
    public void updateProduto(Long id, ProdutoRecordDto produtoRecordDto) {
        ProdutoModel produtoModel = produtoRepository.findById(id)
                .orElseThrow(ProdutoNaoEncontradoException::new);

        BeanUtils.copyProperties(produtoRecordDto, produtoModel);
        produtoRepository.save(produtoModel);
    }

    @Transactional
    public void deleteById(Long id) {
        ProdutoModel produtoModel = produtoRepository.findById(id)
                .orElseThrow(ProdutoNaoEncontradoException::new);

        produtoRepository.delete(produtoModel);
    }
}

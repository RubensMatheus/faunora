package br.com.faunora.controllers;

import br.com.faunora.domain.dto.ProdutoRecordDto;
import br.com.faunora.domain.models.ProdutoModel;
import br.com.faunora.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoModel> saveProduto(@RequestBody ProdutoRecordDto produtoRecordDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.saveProduto(produtoRecordDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoModel> getProdutoById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoModel>> getAllProdutos() {
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoModel> updateProduto(@PathVariable UUID id, @RequestBody ProdutoRecordDto produtoRecordDto) {
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.updateProduto(id, produtoRecordDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable UUID id) {
        produtoService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("produto deletado com sucesso");
    }
}

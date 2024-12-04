package br.com.faunora.controllers;

import br.com.faunora.domain.dto.ReceitaRecordDto;
import br.com.faunora.domain.dto.RestMensagemRecordDto;
import br.com.faunora.domain.models.ReceitaModel;
import br.com.faunora.services.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {
    @Autowired
    private ReceitaService receitaService;

    @PreAuthorize("hasRole('ROLE_VETERINARIO')")
    @PostMapping
    public ResponseEntity<RestMensagemRecordDto> saveReceita(@RequestBody ReceitaRecordDto receitaRecordDto) {
        receitaService.saveReceita(receitaRecordDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new RestMensagemRecordDto("Receita criada com sucesso"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReceitaModel> getReceitaById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(receitaService.findById(id));
    }

    @PreAuthorize("hasRole('ROLE_VETERINARIO')")
    @PutMapping("/{id}")
    public ResponseEntity<RestMensagemRecordDto> updateReceitaById(@PathVariable Long id, @RequestBody ReceitaRecordDto receitaRecordDto) {
        receitaService.updateById(id, receitaRecordDto);
        return ResponseEntity.status(HttpStatus.OK).body(new RestMensagemRecordDto("Receita atualizada com sucesso"));
    }

    @PreAuthorize("hasRole('ROLE_VETERINARIO')")
    @DeleteMapping("/{id}")
    public ResponseEntity<RestMensagemRecordDto> deleteReceitaById(@PathVariable Long id) {
        receitaService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new RestMensagemRecordDto("Receita deletada com sucesso"));
    }
}

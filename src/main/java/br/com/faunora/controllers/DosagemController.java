package br.com.faunora.controllers;

import br.com.faunora.domain.dto.DosagemRecordDto;
import br.com.faunora.domain.dto.RestMensagemRecordDto;
import br.com.faunora.domain.enums.DosagemTipo;
import br.com.faunora.domain.models.DosagemModel;
import br.com.faunora.services.DosagemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dosagens")
public class DosagemController {
    @Autowired
    private DosagemService dosagemService;

    @PostMapping
    public ResponseEntity<RestMensagemRecordDto> saveDosagem(@RequestBody @Valid DosagemRecordDto dosagemRecordDto) {
        dosagemService.saveDosagem(dosagemRecordDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new RestMensagemRecordDto("dosagem criada com sucesso"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DosagemModel> getDosagemById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(dosagemService.findById(id));
    }

    @GetMapping("/tutor")
    public ResponseEntity<List<DosagemModel>> getAllDosagens() {
        return ResponseEntity.status(HttpStatus.OK).body(dosagemService.findAllByTutor());
    }

    @GetMapping("/vet")
    public ResponseEntity<List<DosagemModel>> getAllDosagensVet() {
        return ResponseEntity.status(HttpStatus.OK).body(dosagemService.findAllByVeterinario());
    }

    @GetMapping("/tutor/tipo")
    public ResponseEntity<List<DosagemModel>> getAllByTipo(@RequestParam DosagemTipo tipo) {
        return ResponseEntity.status(HttpStatus.OK).body(dosagemService.findAllByTipo(tipo));
    }

    @GetMapping("/tutor/anteriores")
    public ResponseEntity<List<DosagemModel>> getAnteriores() {
        return ResponseEntity.status(HttpStatus.OK).body(dosagemService.findAnteriores());
    }

    @GetMapping("/vet/anteriores")
    public ResponseEntity<List<DosagemModel>> getAnterioresVet() {
        return ResponseEntity.status(HttpStatus.OK).body(dosagemService.findAnterioresVeterinario());
    }

    @GetMapping("/tutor/marcadas")
    public ResponseEntity<List<DosagemModel>> getMarcadas() {
        return ResponseEntity.status(HttpStatus.OK).body(dosagemService.findMarcadas());
    }

    @GetMapping("/vet/marcadas")
    public ResponseEntity<List<DosagemModel>> getMarcadasVet() {
        return ResponseEntity.status(HttpStatus.OK).body(dosagemService.findMarcadasVeterinario());
    }

    @GetMapping("/tutor/{filter}")
    public ResponseEntity<List<DosagemModel>> getAllByRandom(@PathVariable String filter) {
        return ResponseEntity.status(HttpStatus.OK).body(dosagemService.findAllByRandom(filter));
    }

    @GetMapping("/vet/{filter}")
    public ResponseEntity<List<DosagemModel>> getAllByRandomVet(@PathVariable String filter) {
        return ResponseEntity.status(HttpStatus.OK).body(dosagemService.findAllByRandomVeterinario(filter));
    }

    @GetMapping("/paciente/{id}")
    public ResponseEntity<List<DosagemModel>> getAllByPaciente(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(dosagemService.findAllByPaciente(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestMensagemRecordDto> updateDosagem(@PathVariable Long id, @RequestBody @Valid DosagemRecordDto dosagemRecordDto) {
        dosagemService.updateDosagem(id, dosagemRecordDto);
        return ResponseEntity.status(HttpStatus.OK).body(new RestMensagemRecordDto("dosagem atualizada com sucesso"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestMensagemRecordDto> deleteById(@PathVariable Long id) {
        dosagemService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new RestMensagemRecordDto("dosagem deletada com sucesso"));
    }
}

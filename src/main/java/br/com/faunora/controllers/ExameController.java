package br.com.faunora.controllers;

import br.com.faunora.domain.dto.ExameRecordDto;
import br.com.faunora.domain.dto.RestMensagemRecordDto;
import br.com.faunora.domain.enums.ExameTipo;
import br.com.faunora.domain.models.ExameModel;
import br.com.faunora.services.ExameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/exames")
public class ExameController {
    @Autowired
    private ExameService exameService;

    @PostMapping
    public ResponseEntity<RestMensagemRecordDto> saveExame(@RequestBody ExameRecordDto exameRecordDto) {
        exameService.saveExame(exameRecordDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new RestMensagemRecordDto("exame criado com sucesso"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExameModel> getExameById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(exameService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ExameModel>> getAllExames() {
        return ResponseEntity.status(HttpStatus.OK).body(exameService.findAll());
    }

    @GetMapping("/tipo")
    public ResponseEntity<List<ExameModel>> getAllByTipo(@RequestParam ExameTipo tipo) {
        return ResponseEntity.status(HttpStatus.OK).body(exameService.findAllByTipo(tipo));
    }

    @GetMapping("/anteriores")
    public ResponseEntity<List<ExameModel>> getAnteriores() {
        return ResponseEntity.status(HttpStatus.OK).body(exameService.findAnteriores());
    }

    @GetMapping("/marcados")
    public ResponseEntity<List<ExameModel>> getMarcados() {
        return ResponseEntity.status(HttpStatus.OK).body(exameService.findMarcados());
    }

    @GetMapping("/{filter}")
    public ResponseEntity<List<ExameModel>> getAllByRandom(@PathVariable String filter) {
        return ResponseEntity.status(HttpStatus.OK).body(exameService.findAllByRandom(filter));
    }

    @GetMapping("/paciente/{id}")
    public ResponseEntity<List<ExameModel>> getAllByPaciente(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(exameService.findAllByPaciente(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestMensagemRecordDto> updateExame(@PathVariable Long id, @RequestBody ExameRecordDto exameRecordDto) {
        exameService.updateExame(id, exameRecordDto);
        return ResponseEntity.status(HttpStatus.OK).body(new RestMensagemRecordDto("exame atualizado com sucesso"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestMensagemRecordDto> deleteById(@PathVariable Long id) {
        exameService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new RestMensagemRecordDto("exame deletado com sucesso"));
    }
}

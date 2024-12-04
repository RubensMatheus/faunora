package br.com.faunora.controllers;

import br.com.faunora.domain.dto.exames.ExameRecordDto;
import br.com.faunora.domain.dto.rest.RestMensagemRecordDto;
import br.com.faunora.domain.enums.ExameTipo;
import br.com.faunora.domain.models.ExameModel;
import br.com.faunora.services.ExameService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exames")
public class ExameController {
    @Autowired
    private ExameService exameService;

    @PreAuthorize("hasRole('ROLE_TUTOR')")
    @PostMapping
    public ResponseEntity<RestMensagemRecordDto> saveExame(@RequestBody @Valid ExameRecordDto exameRecordDto) {
        exameService.saveExame(exameRecordDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new RestMensagemRecordDto("exame criado com sucesso"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExameModel> getExameById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(exameService.findById(id));
    }

    @PreAuthorize("hasRole('ROLE_TUTOR')")
    @GetMapping("/tutor")
    public ResponseEntity<List<ExameModel>> getAllExames() {
        return ResponseEntity.status(HttpStatus.OK).body(exameService.findAllByTutor());
    }

    @PreAuthorize("hasRole('ROLE_VET')")
    @GetMapping("/vet")
    public ResponseEntity<List<ExameModel>> getAllExamesVet() {
        return ResponseEntity.status(HttpStatus.OK).body(exameService.findAllByVeterinario());
    }

    @PreAuthorize("hasRole('ROLE_TUTOR')")
    @GetMapping("/tutor/tipo")
    public ResponseEntity<List<ExameModel>> getAllByTipo(@RequestParam ExameTipo tipo) {
        return ResponseEntity.status(HttpStatus.OK).body(exameService.findAllByTipo(tipo));
    }

    @PreAuthorize("hasRole('ROLE_TUTOR')")
    @GetMapping("/tutor/anteriores")
    public ResponseEntity<List<ExameModel>> getAnteriores() {
        return ResponseEntity.status(HttpStatus.OK).body(exameService.findAnteriores());
    }

    @PreAuthorize("hasRole('ROLE_VET')")
    @GetMapping("/vet/anteriores")
    public ResponseEntity<List<ExameModel>> getAnterioresVet() {
        return ResponseEntity.status(HttpStatus.OK).body(exameService.findAnterioresVeterinario());
    }

    @PreAuthorize("hasRole('ROLE_TUTOR')")
    @GetMapping("/tutor/marcados")
    public ResponseEntity<List<ExameModel>> getMarcados() {
        return ResponseEntity.status(HttpStatus.OK).body(exameService.findMarcados());
    }

    @PreAuthorize("hasRole('ROLE_VET')")
    @GetMapping("/vet/marcados")
    public ResponseEntity<List<ExameModel>> getMarcadosVet() {
        return ResponseEntity.status(HttpStatus.OK).body(exameService.findMarcadosVeterinario());
    }

    @PreAuthorize("hasRole('ROLE_TUTOR')")
    @GetMapping("/tutor/{filter}")
    public ResponseEntity<List<ExameModel>> getAllByRandom(@PathVariable String filter) {
        return ResponseEntity.status(HttpStatus.OK).body(exameService.findAllByRandom(filter));
    }

    @PreAuthorize("hasRole('ROLE_VET')")
    @GetMapping("/vet/{filter}")
    public ResponseEntity<List<ExameModel>> getAllByRandomVet(@PathVariable String filter) {
        return ResponseEntity.status(HttpStatus.OK).body(exameService.findAllByRandomVeterinario(filter));
    }

    @GetMapping("/paciente/{id}")
    public ResponseEntity<List<ExameModel>> getAllByPaciente(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(exameService.findAllByPaciente(id));
    }

    @PreAuthorize("hasRole('ROLE_TUTOR')")
    @PutMapping("/{id}")
    public ResponseEntity<RestMensagemRecordDto> updateExame(@PathVariable Long id, @RequestBody @Valid ExameRecordDto exameRecordDto) {
        exameService.updateExame(id, exameRecordDto);
        return ResponseEntity.status(HttpStatus.OK).body(new RestMensagemRecordDto("exame atualizado com sucesso"));
    }

    @PreAuthorize("hasRole('ROLE_TUTOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<RestMensagemRecordDto> deleteById(@PathVariable Long id) {
        exameService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new RestMensagemRecordDto("exame deletado com sucesso"));
    }
}

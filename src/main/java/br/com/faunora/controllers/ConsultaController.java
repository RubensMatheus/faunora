package br.com.faunora.controllers;

import br.com.faunora.domain.dto.ConsultaRecordDto;
import br.com.faunora.domain.dto.RestMensagemRecordDto;
import br.com.faunora.domain.models.ConsultaModel;
import br.com.faunora.services.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    public ResponseEntity<RestMensagemRecordDto> saveConsulta(@RequestBody ConsultaRecordDto consultaRecordDto) {
        consultaService.saveConsulta(consultaRecordDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new RestMensagemRecordDto("Consulta criada com sucesso."));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaModel> getConsultaById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(consultaService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ConsultaModel>> getAllConsultas() {
        return ResponseEntity.status(HttpStatus.OK).body(consultaService.findAll());
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<ConsultaModel>> getConsultasByPaciente(@PathVariable Long pacienteId) {
        return ResponseEntity.status(HttpStatus.OK).body(consultaService.findAllByPaciente(pacienteId));
    }

    @GetMapping("/veterinario")
    public ResponseEntity<List<ConsultaModel>> getConsultasByVeterinario() {
        return ResponseEntity.status(HttpStatus.OK).body(consultaService.findAllByVeterinario());
    }

    @GetMapping("/anteriores")
    public ResponseEntity<List<ConsultaModel>> getConsultasAnteriores() {
        return ResponseEntity.status(HttpStatus.OK).body(consultaService.findAnteriores());
    }

    @GetMapping("/marcados")
    public ResponseEntity<List<ConsultaModel>> getConsultasMarcadas() {
        return ResponseEntity.status(HttpStatus.OK).body(consultaService.findMarcados());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestMensagemRecordDto> updateConsulta(
            @PathVariable Long id,
            @RequestBody ConsultaRecordDto consultaRecordDto) {
        consultaService.updateConsulta(id, consultaRecordDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new RestMensagemRecordDto("Consulta atualizada com sucesso."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestMensagemRecordDto> deleteConsultaById(@PathVariable Long id) {
        consultaService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new RestMensagemRecordDto("Consulta deletada com sucesso."));
    }
}

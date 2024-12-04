package br.com.faunora.controllers;

import br.com.faunora.domain.dto.ConsultaRecordDto;
import br.com.faunora.domain.dto.RestMensagemRecordDto;
import br.com.faunora.domain.models.ConsultaModel;
import br.com.faunora.services.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
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

    @GetMapping("/tutor")
    public ResponseEntity<List<ConsultaModel>> getAllConsultas() {
        return ResponseEntity.status(HttpStatus.OK).body(consultaService.findAllByTutor());
    }

    @GetMapping("/vet")
    public ResponseEntity<List<ConsultaModel>> getAllConsultasVet() {
        return ResponseEntity.status(HttpStatus.OK).body(consultaService.findAllByVeterinario());
    }

    @GetMapping("/dias-disponiveis-vet/{id}")
    public ResponseEntity<List<LocalDate>> getDiasDisponiveisVet(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(consultaService.getDiasDisponiveisPorVet(id));
    }

    @GetMapping("/horarios-disponiveis-vet/{id}/{data}")
    public ResponseEntity<List<LocalTime>> getHorariosDisponiveisVet(@PathVariable Long id, @PathVariable LocalDate data) {
        return ResponseEntity.status(HttpStatus.OK).body(consultaService.getHorariosDisponiveisPorDiaEVet(data, id));
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<ConsultaModel>> getConsultasByPaciente(@PathVariable Long pacienteId) {
        return ResponseEntity.status(HttpStatus.OK).body(consultaService.findAllByPaciente(pacienteId));
    }

    @GetMapping("/tutor/anteriores")
    public ResponseEntity<List<ConsultaModel>> getConsultasAnteriores() {
        return ResponseEntity.status(HttpStatus.OK).body(consultaService.findAnteriores());
    }

    @GetMapping("/vet/anteriores")
    public ResponseEntity<List<ConsultaModel>> getConsultasAnterioresVet() {
        return ResponseEntity.status(HttpStatus.OK).body(consultaService.findAnterioresVeterinario());
    }

    @GetMapping("/tutor/marcados")
    public ResponseEntity<List<ConsultaModel>> getConsultasMarcadas() {
        return ResponseEntity.status(HttpStatus.OK).body(consultaService.findMarcados());
    }

    @GetMapping("/vet/marcados")
    public ResponseEntity<List<ConsultaModel>> getConsultasMarcadosVet() {
        return ResponseEntity.status(HttpStatus.OK).body(consultaService.findMarcadosVeterinario());
    }

    @GetMapping("/tutor/{filter}")
    public ResponseEntity<List<ConsultaModel>> getAllByRandom(@PathVariable String filter) {
        return ResponseEntity.status(HttpStatus.OK).body(consultaService.findAllByRandom(filter));
    }

    @GetMapping("/vet/{filter}")
    public ResponseEntity<List<ConsultaModel>> getAllByRandomVet(@PathVariable String filter) {
        return ResponseEntity.status(HttpStatus.OK).body(consultaService.findAllByRandomVeterinario(filter));
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

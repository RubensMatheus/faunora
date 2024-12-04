package br.com.faunora.controllers;

import br.com.faunora.domain.dto.ConsultaRecordDto;
import br.com.faunora.domain.dto.RestMensagemRecordDto;
import br.com.faunora.domain.models.ConsultaModel;
import br.com.faunora.services.ConsultaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {
    @Autowired
    private ConsultaService consultaService;

    @PreAuthorize("hasRole('ROLE_TUTOR')")
    @PostMapping
    public ResponseEntity<RestMensagemRecordDto> saveConsulta(@RequestBody @Valid ConsultaRecordDto consultaRecordDto) {
        consultaService.saveConsulta(consultaRecordDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new RestMensagemRecordDto("consulta criada com sucesso"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaModel> getConsultaById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(consultaService.findById(id));
    }

    public ResponseEntity<List<ConsultaModel>> getAllConsultas() {
        return ResponseEntity.status(HttpStatus.OK).body(consultaService.findAllByTutor());
    }

    @PreAuthorize("hasRole('ROLE_VET')")
    @GetMapping("/vet")
    public ResponseEntity<List<ConsultaModel>> getAllConsultasVet() {
        return ResponseEntity.status(HttpStatus.OK).body(consultaService.findAllByVeterinario());
    }

    @PreAuthorize("hasRole('ROLE_TUTOR')")
    @GetMapping("/dias-disponiveis-vet/{id}")
    public ResponseEntity<List<LocalDate>> getDiasDisponiveisVet(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(consultaService.getDiasDisponiveisPorVet(id));
    }

    @PreAuthorize("hasRole('ROLE_TUTOR')")
    @GetMapping("/horarios-disponiveis-vet/{id}/{data}")
    public ResponseEntity<List<LocalTime>> getHorariosDisponiveisVet(@PathVariable Long id, @PathVariable LocalDate data) {
        return ResponseEntity.status(HttpStatus.OK).body(consultaService.getHorariosDisponiveisPorDiaEVet(data, id));
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<ConsultaModel>> getConsultasByPaciente(@PathVariable Long pacienteId) {
        return ResponseEntity.status(HttpStatus.OK).body(consultaService.findAllByPaciente(pacienteId));
    }

    @PreAuthorize("hasRole('ROLE_TUTOR')")
    @GetMapping("/tutor/anteriores")
    public ResponseEntity<List<ConsultaModel>> getConsultasAnteriores() {
        return ResponseEntity.status(HttpStatus.OK).body(consultaService.findAnteriores());
    }

    @PreAuthorize("hasRole('ROLE_TUTOR')")
    @GetMapping("/vet/anteriores")
    public ResponseEntity<List<ConsultaModel>> getConsultasAnterioresVet() {
        return ResponseEntity.status(HttpStatus.OK).body(consultaService.findAnterioresVeterinario());
    }

    @PreAuthorize("hasRole('ROLE_TUTOR')")
    @GetMapping("/tutor/marcados")
    public ResponseEntity<List<ConsultaModel>> getConsultasMarcadas() {
        return ResponseEntity.status(HttpStatus.OK).body(consultaService.findMarcados());
    }

    @PreAuthorize("hasRole('ROLE_VET')")
    @GetMapping("/vet/marcados")
    public ResponseEntity<List<ConsultaModel>> getConsultasMarcadosVet() {
        return ResponseEntity.status(HttpStatus.OK).body(consultaService.findMarcadosVeterinario());
    }

    @PreAuthorize("hasRole('ROLE_TUTOR')")
    @GetMapping("/tutor/{filter}")
    public ResponseEntity<List<ConsultaModel>> getAllByRandom(@PathVariable String filter) {
        return ResponseEntity.status(HttpStatus.OK).body(consultaService.findAllByRandom(filter));
    }

    @PreAuthorize("hasRole('ROLE_VETERINARIO')")
    @GetMapping("/vet/{filter}")
    public ResponseEntity<List<ConsultaModel>> getAllByRandomVet(@PathVariable String filter) {
        return ResponseEntity.status(HttpStatus.OK).body(consultaService.findAllByRandomVeterinario(filter));
    }

    @PreAuthorize("hasRole('ROLE_TUTOR')")
    @PutMapping("/{id}")
    public ResponseEntity<RestMensagemRecordDto> updateConsulta(@PathVariable Long id, @RequestBody @Valid ConsultaRecordDto consultaRecordDto) {
        consultaService.updateConsulta(id, consultaRecordDto);
        return ResponseEntity.status(HttpStatus.OK).body(new RestMensagemRecordDto("consulta atualizada com sucesso"));
    }

    @PreAuthorize("hasRole('ROLE_TUTOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<RestMensagemRecordDto> deleteConsultaById(@PathVariable Long id) {
        consultaService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new RestMensagemRecordDto("consulta deletada com sucesso"));
    }
}

package br.com.faunora.controllers;

import br.com.faunora.domain.dto.rest.RestMensagemRecordDto;
import br.com.faunora.domain.enums.PetSexo;
import br.com.faunora.domain.enums.PetTipo;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.faunora.domain.dto.pets.PetRecordDto;
import br.com.faunora.domain.models.PetModel;
import br.com.faunora.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {
    @Autowired
    private PetService petService;

    @PreAuthorize("hasRole('ROLE_TUTOR')")
    @PostMapping
    public ResponseEntity<RestMensagemRecordDto> savePet(@RequestBody @Valid PetRecordDto petRecordDto) {
        petService.savePet(petRecordDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new RestMensagemRecordDto("pet criado com sucesso"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetModel> getPetById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(petService.findById(id));
    }

    @PreAuthorize("hasRole('ROLE_TUTOR')")
    @GetMapping
    public ResponseEntity<List<PetModel>> getAllPets() {
        return ResponseEntity.status(HttpStatus.OK).body(petService.findAll());
    }

    @PreAuthorize("hasRole('ROLE_TUTOR')")
    @GetMapping("/exames")
    public ResponseEntity<List<PetModel>> getAllPetsPorProximosExames() {
        return ResponseEntity.status(HttpStatus.OK).body(petService.findAllByProximosExames());
    }

    @PreAuthorize("hasRole('ROLE_TUTOR')")
    @GetMapping("/vacinas")
    public ResponseEntity<List<PetModel>> getAllPetsPorProximasVacinas() {
        return ResponseEntity.status(HttpStatus.OK).body(petService.findAllByProximasVacinas());
    }

    @PreAuthorize("hasRole('ROLE_TUTOR')")
    @GetMapping("/consultas")
    public ResponseEntity<List<PetModel>> getAllPetsPorProximasConsultas() {
        return ResponseEntity.status(HttpStatus.OK).body(petService.findAllByProximasConsultas());
    }

    @PreAuthorize("hasRole('ROLE_TUTOR')")
    @GetMapping("/{filter}")
    public ResponseEntity<List<PetModel>> getAllPetsRandom(@PathVariable String filter) {
        return ResponseEntity.status(HttpStatus.OK).body(petService.findAllByRandom(filter));
    }

    @PreAuthorize("hasRole('ROLE_TUTOR')")
    @PutMapping("/{id}")
    public ResponseEntity<RestMensagemRecordDto> updatePet(@PathVariable Long id, @RequestBody @Valid PetRecordDto petRecordDto) {
        petService.updatePet(id, petRecordDto);
        return ResponseEntity.status(HttpStatus.OK).body(new RestMensagemRecordDto("pet atualizado com sucesso"));
    }

    @PreAuthorize("hasRole('ROLE_TUTOR')")
    @PatchMapping("/nome/{id}")
    public ResponseEntity<RestMensagemRecordDto> updatePetNome(@PathVariable Long id, @RequestParam String nome) {
        petService.updatePetNome(id, nome);
        return ResponseEntity.status(HttpStatus.OK).body(new RestMensagemRecordDto("nome do pet atualizado com sucesso"));
    }

    @PreAuthorize("hasRole('ROLE_TUTOR')")
    @PatchMapping("/sexo/{id}")
    public ResponseEntity<RestMensagemRecordDto> updatePetSexo(@PathVariable Long id, @RequestParam PetSexo sexo) {
        petService.updatePetSexo(id, sexo);
        return ResponseEntity.status(HttpStatus.OK).body(new RestMensagemRecordDto("sexo do pet atualizado com sucesso"));
    }

    @PreAuthorize("hasRole('ROLE_TUTOR')")
    @PatchMapping("/tipo/{id}")
    public ResponseEntity<RestMensagemRecordDto> updatePetTipo(@PathVariable Long id, @RequestParam PetTipo tipo) {
        petService.updatePetTipo(id, tipo);
        return ResponseEntity.status(HttpStatus.OK).body(new RestMensagemRecordDto("espécie do pet atualizado com sucesso"));
    }

    @PreAuthorize("hasRole('ROLE_TUTOR')")
    @PatchMapping("/peso/{id}")
    public ResponseEntity<RestMensagemRecordDto> updatePetPeso(@PathVariable Long id, @RequestParam double peso) {
        petService.updatePetPeso(id, peso);
        return ResponseEntity.status(HttpStatus.OK).body(new RestMensagemRecordDto("peso do pet atualizado com sucesso"));
    }

    @PreAuthorize("hasRole('ROLE_TUTOR')")
    @PatchMapping("/data-nasc/{id}")
    public ResponseEntity<RestMensagemRecordDto> updatePetNascimento(@PathVariable Long id, @RequestParam LocalDate nascimento) {
        petService.updatePetNascimento(id, nascimento);
        return ResponseEntity.status(HttpStatus.OK).body(new RestMensagemRecordDto("data de nascimento do pet atualizado com sucesso"));
    }

    @PreAuthorize("hasRole('ROLE_TUTOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<RestMensagemRecordDto> deleteById(@PathVariable Long id) {
        petService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new RestMensagemRecordDto("pet deletado com sucesso"));
    }
}

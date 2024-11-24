package br.com.faunora.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.faunora.domain.dto.PetRecordDto;
import br.com.faunora.domain.models.PetModel;
import br.com.faunora.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pets")
public class PetController {
    @Autowired
    private PetService petService;

    @PostMapping
    public ResponseEntity<PetModel> savePet(@RequestBody PetRecordDto petRecordDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(petService.savePet(petRecordDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetModel> getPetById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(petService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<PetModel>> getAllPets() {
        return ResponseEntity.status(HttpStatus.OK).body(petService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PetModel> updatePet(@PathVariable UUID id, @RequestBody PetRecordDto petRecordDto) {
        return ResponseEntity.status(HttpStatus.OK).body(petService.updatePet(id, petRecordDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable UUID id) {
        petService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("pet deletado com sucesso");
    }
}


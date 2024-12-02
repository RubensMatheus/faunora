package br.com.faunora.controllers;

import br.com.faunora.domain.dto.LaudoRecordDto;
import br.com.faunora.domain.dto.RestMensagemRecordDto;
import br.com.faunora.domain.models.LaudoModel;
import br.com.faunora.services.LaudoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/laudos")
public class LaudoController {
    @Autowired
    private LaudoService laudoService;

    @GetMapping("/{id}")
    public ResponseEntity<LaudoModel> getLaudoById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(laudoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<RestMensagemRecordDto> saveLaudo(@RequestBody LaudoRecordDto laudoRecordDto) {
        laudoService.saveLaudo(laudoRecordDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new RestMensagemRecordDto("laudo criado com sucesso"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestMensagemRecordDto> updateLaudoById(@PathVariable Long id, @RequestBody LaudoRecordDto laudoRecordDto) {
        laudoService.updateById(id, laudoRecordDto);
        return ResponseEntity.status(HttpStatus.OK).body(new RestMensagemRecordDto("laudo atualizado com sucesso"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestMensagemRecordDto> deleteLaudoById(@PathVariable Long id) {
        laudoService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new RestMensagemRecordDto("laudo deletado com sucesso"));
    }
}

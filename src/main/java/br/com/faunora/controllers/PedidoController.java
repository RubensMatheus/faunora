package br.com.faunora.controllers;

import br.com.faunora.domain.dto.PedidoRecordDto;
import br.com.faunora.domain.dto.RestMensagemRecordDto;
import br.com.faunora.domain.dto.UpdatePedidoRecordDto;
import br.com.faunora.domain.models.PedidoModel;
import br.com.faunora.services.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @PreAuthorize("hasRole('ROLE_TUTOR')")
    @PostMapping
    public ResponseEntity<RestMensagemRecordDto> savePedido(@RequestBody @Valid PedidoRecordDto pedidoRecordDto) {
        pedidoService.savePedido(pedidoRecordDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new RestMensagemRecordDto("pedido criado com sucesso"));
    }

    @PreAuthorize("hasRole('ROLE_TUTOR')")
    @GetMapping("/{id}")
    public ResponseEntity<PedidoModel> getPedidoById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.findById(id));
    }

    @PreAuthorize("hasRole('ROLE_TUTOR')")
    @GetMapping
    public ResponseEntity<List<PedidoModel>> getAllPedidos() {
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.findAll());
    }

    @PreAuthorize("hasRole('ROLE_TUTOR')")
    @PutMapping("/{id}")
    public ResponseEntity<RestMensagemRecordDto> updatePedido(@PathVariable Long id, @RequestBody @Valid UpdatePedidoRecordDto updatePedidoRecordDto) {
        pedidoService.updatePedido(id, updatePedidoRecordDto);
        return ResponseEntity.status(HttpStatus.OK).body(new RestMensagemRecordDto("pedido atualizado com sucesso"));
    }

    @PreAuthorize("hasRole('ROLE_TUTOR')")
    @PatchMapping("cancelar/{id}")
    public ResponseEntity<RestMensagemRecordDto> cancelarPedido(@PathVariable Long id) {
        pedidoService.cancelarPedido(id);
        return ResponseEntity.status(HttpStatus.OK).body(new RestMensagemRecordDto("pedido cancelado com sucesso"));
    }

    @PreAuthorize("hasRole('ROLE_TUTOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<RestMensagemRecordDto> deletePedidoById(@PathVariable Long id) {
        pedidoService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new RestMensagemRecordDto("pedido deletado com sucesso"));
    }
}

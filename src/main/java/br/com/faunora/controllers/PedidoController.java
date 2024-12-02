package br.com.faunora.controllers;

import br.com.faunora.domain.dto.PedidoRecordDto;
import br.com.faunora.domain.dto.RestMensagemRecordDto;
import br.com.faunora.domain.dto.UpdatePedidoRecordDto;
import br.com.faunora.domain.models.PedidoModel;
import br.com.faunora.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<RestMensagemRecordDto> savePedido(@RequestBody PedidoRecordDto pedidoRecordDto) {
        pedidoService.savePedido(pedidoRecordDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new RestMensagemRecordDto("pedido criado com sucesso"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoModel> getPedidoById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<PedidoModel>> getAllPedidos() {
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestMensagemRecordDto> updatePedido(@PathVariable Long id, @RequestBody UpdatePedidoRecordDto updatePedidoRecordDto) {
        pedidoService.updatePedido(id, updatePedidoRecordDto);
        return ResponseEntity.status(HttpStatus.OK).body(new RestMensagemRecordDto("pedido atualizado com sucesso"));
    }

    @PatchMapping("cancelar/{id}")
    public ResponseEntity<RestMensagemRecordDto> cancelarPedido(@PathVariable Long id) {
        pedidoService.cancelarPedido(id);
        return ResponseEntity.status(HttpStatus.OK).body(new RestMensagemRecordDto("pedido cancelado com sucesso"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestMensagemRecordDto> deletePedidoById(@PathVariable Long id) {
        pedidoService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new RestMensagemRecordDto("pedido deletado com sucesso"));
    }
}

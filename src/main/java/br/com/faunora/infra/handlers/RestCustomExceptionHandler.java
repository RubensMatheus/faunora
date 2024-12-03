package br.com.faunora.infra.handlers;

import br.com.faunora.domain.dto.RestMensagemRecordDto;
import br.com.faunora.infra.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestCustomExceptionHandler {
    @ExceptionHandler({DosagemNaoEncontradaException.class, NenhumaDosagemEncontradaException.class, ExameNaoEncontradoException.class, NenhumExameEncontradoException.class, LaudoNaoEncontradoException.class, PedidoNaoEncontradoException.class, NenhumPedidoEncontradoException.class, UsuarioNaoEncontradoException.class, NenhumUsuarioEncontradoException.class, PetNaoEncontradoException.class, NenhumPetEncontradoException.class, ProdutoNaoEncontradoException.class, NenhumProdutoEncontradoException.class})
    public ResponseEntity<RestMensagemRecordDto> handleNotFoundException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RestMensagemRecordDto(ex.getMessage()));
    }

    @ExceptionHandler({EmailNaoDisponivelException.class})
    public ResponseEntity<RestMensagemRecordDto> handleConflictException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new RestMensagemRecordDto(ex.getMessage()));
    }
}

package br.com.faunora.infra.handlers.custom;

import br.com.faunora.domain.dto.RestMensagemRecordDto;
import br.com.faunora.infra.exceptions.NenhumPetEncontradoException;
import br.com.faunora.infra.exceptions.NenhumProdutoEncontradoException;
import br.com.faunora.infra.exceptions.PetNaoEncontradoException;
import br.com.faunora.infra.exceptions.ProdutoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestCustomExceptionHandler {
    @ExceptionHandler({PetNaoEncontradoException.class, NenhumPetEncontradoException.class, ProdutoNaoEncontradoException.class, NenhumProdutoEncontradoException.class})
    public ResponseEntity<RestMensagemRecordDto> handleNotFoundException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RestMensagemRecordDto(ex.getMessage()));
    }
}

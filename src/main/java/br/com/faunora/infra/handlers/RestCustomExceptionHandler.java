package br.com.faunora.infra.handlers;

import br.com.faunora.domain.dto.RestMensagemRecordDto;
import br.com.faunora.infra.exceptions.*;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestCustomExceptionHandler {
    @ExceptionHandler({DosagemNaoEncontradaException.class, NenhumaDosagemEncontradaException.class, ExameNaoEncontradoException.class, NenhumExameEncontradoException.class, LaudoNaoEncontradoException.class, PedidoNaoEncontradoException.class, NenhumPedidoEncontradoException.class, UsuarioNaoEncontradoException.class, NenhumUsuarioEncontradoException.class, PetNaoEncontradoException.class, NenhumPetEncontradoException.class, ProdutoNaoEncontradoException.class, NenhumProdutoEncontradoException.class, UsernameNotFoundException.class})
    public ResponseEntity<RestMensagemRecordDto> handleNotFoundException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RestMensagemRecordDto(ex.getMessage()));
    }

    @ExceptionHandler({EmailNaoDisponivelException.class})
    public ResponseEntity<RestMensagemRecordDto> handleConflictException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new RestMensagemRecordDto(ex.getMessage()));
    }

    @ExceptionHandler({CredenciaisInvalidasException.class, BadCredentialsException.class})
    public ResponseEntity<RestMensagemRecordDto> handleUnauthorizedException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new RestMensagemRecordDto(ex.getMessage()));
    }

    @ExceptionHandler({SenhasNaoCoincidemException.class})
    public ResponseEntity<RestMensagemRecordDto> handleBadRequestException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RestMensagemRecordDto(ex.getMessage()));
    }

    @ExceptionHandler({JWTCreationException.class})
    public ResponseEntity<RestMensagemRecordDto> handleInternalServerErrorException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RestMensagemRecordDto(ex.getMessage()));
    }
}

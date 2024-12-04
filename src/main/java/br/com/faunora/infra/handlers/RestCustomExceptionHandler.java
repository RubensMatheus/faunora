package br.com.faunora.infra.handlers;

import br.com.faunora.domain.dto.RestMensagemRecordDto;
import br.com.faunora.infra.exceptions.*;
import com.auth0.jwt.exceptions.JWTCreationException;
import jakarta.mail.MessagingException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.sql.SQLSyntaxErrorException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@ControllerAdvice
public class RestCustomExceptionHandler {
    @ExceptionHandler({NenhumaConsultaEncontradaException.class, VeterinarioNaoEncontradoException.class, ReceitaNaoEncontradaException.class, ConsultaNaoEncontradaException.class, DosagemNaoEncontradaException.class, NenhumaDosagemEncontradaException.class, ExameNaoEncontradoException.class, NenhumExameEncontradoException.class, LaudoNaoEncontradoException.class, PedidoNaoEncontradoException.class, NenhumPedidoEncontradoException.class, UsuarioNaoEncontradoException.class, NenhumUsuarioEncontradoException.class, PetNaoEncontradoException.class, NenhumPetEncontradoException.class, ProdutoNaoEncontradoException.class, NenhumProdutoEncontradoException.class, UsernameNotFoundException.class})
    public ResponseEntity<RestMensagemRecordDto> handleNotFoundException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RestMensagemRecordDto(ex.getMessage()));
    }

    @ExceptionHandler({EmailNaoDisponivelException.class})
    public ResponseEntity<RestMensagemRecordDto> handleConflictException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new RestMensagemRecordDto(ex.getMessage()));
    }

    @ExceptionHandler({CredenciaisInvalidasException.class})
    public ResponseEntity<RestMensagemRecordDto> handleUnauthorizedException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new RestMensagemRecordDto(ex.getMessage()));
    }

    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<RestMensagemRecordDto> handleBadCredentialsException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new RestMensagemRecordDto("usuário inexistente ou senha inválida"));
    }

    @ExceptionHandler({PetIndisponivelException.class, CadastroLaudoInvalidoException.class, CadastroReceitaInvalidoException.class, NenhumVeterinarioDisponivelException.class, SenhasNaoCoincidemException.class, HorarioIndisponivelException.class, VeterinarioInvalidoException.class})
    public ResponseEntity<RestMensagemRecordDto> handleBadRequestException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RestMensagemRecordDto(ex.getMessage()));
    }

    @ExceptionHandler({JWTCreationException.class, MessagingException.class})
    public ResponseEntity<RestMensagemRecordDto> handleInternalServerErrorException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RestMensagemRecordDto(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new LinkedHashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Map<String, String>> handleConstraintsException(ConstraintViolationException ex) {
        Map<String, String> errors = new LinkedHashMap<>();
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();

        for (ConstraintViolation<?> violation : violations) {
            String fieldName = violation.getPropertyPath().toString();
            String errorMessage = violation.getMessage();
            errors.put(fieldName, errorMessage);
        }

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({SQLSyntaxErrorException.class})
    public ResponseEntity<RestMensagemRecordDto> handleSQLSyntaxErrorException(SQLSyntaxErrorException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RestMensagemRecordDto("sem informacoes correspondentes no banco de dados"));
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<RestMensagemRecordDto> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RestMensagemRecordDto("dados nao legíveis em campos obrigatórios"));
    }

    @ExceptionHandler({NoResourceFoundException.class})
    public ResponseEntity<RestMensagemRecordDto> handleNoResourceFoundException(NoResourceFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RestMensagemRecordDto("recurso nao encontrado"));
    }
}

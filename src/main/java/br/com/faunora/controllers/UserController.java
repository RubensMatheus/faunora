package br.com.faunora.controllers;

import br.com.faunora.domain.dto.rest.RestMensagemRecordDto;
import br.com.faunora.domain.dto.users.*;
import br.com.faunora.domain.models.UserModel;
import br.com.faunora.services.UserService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
    private UserService userService;

    @PostMapping("/registrar")
    public ResponseEntity<RestMensagemRecordDto> registrarUser(@RequestBody @Valid UserRecordDto userRecordDto) {
        userService.saveUser(userRecordDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new RestMensagemRecordDto("usuário registrado com sucesso"));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseRecordDto> loginUser(@RequestBody @Valid LoginRequestRecordDto loginRequestRecordDto) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.validateUser(loginRequestRecordDto));
    }

    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateUserResponseRecordDto> updateUser(@PathVariable Long id, @RequestBody @Valid UserRecordDto userRecordDto) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id, userRecordDto));
    }

    @PatchMapping("/esqueceu-senha")
    public ResponseEntity<RestMensagemRecordDto> esqueceuSenha(@RequestBody @Valid ForgotPasswordRecordDto forgotPasswordRecordDto) throws MessagingException {
        System.out.println("teste");
        userService.esqueceuSenha(forgotPasswordRecordDto.email());
        return ResponseEntity.status(HttpStatus.OK).body(new RestMensagemRecordDto("email com código para atualização de senha enviado"));
    }

    @PatchMapping("/redefinir-senha")
    public ResponseEntity<RestMensagemRecordDto> redefinirSenha(@RequestBody @Valid ResetPasswordRecordDto resetPasswordRecordDto) {
        userService.redefinirSenha(resetPasswordRecordDto);
        return ResponseEntity.status(HttpStatus.OK).body(new RestMensagemRecordDto("senha atualizada com sucesso"));
    }

    @PatchMapping("/senha")
    public ResponseEntity<UpdateUserResponseRecordDto> updateSenha(@RequestBody @Valid UpdateUserPasswordRecordDto updateUserPasswordRecordDto) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateSenha(updateUserPasswordRecordDto));
    }

    @PatchMapping("/nome")
    public ResponseEntity<UpdateUserResponseRecordDto> updateNome(@RequestBody @Valid UpdateUserNameRecordDto updateUserNameRecordDto) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateNome(updateUserNameRecordDto));
    }

    @PatchMapping("/email")
    public ResponseEntity<UpdateUserResponseRecordDto> updateEmail(@RequestBody @Valid UpdateUserEmailRecordDto updateUserEmailRecordDto) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateEmail(updateUserEmailRecordDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestMensagemRecordDto> deleteUserById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new RestMensagemRecordDto("usuário deletado com sucesso"));
    }
}


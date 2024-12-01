package br.com.faunora.controllers;

import br.com.faunora.domain.dto.RestMensagemRecordDto;
import br.com.faunora.domain.dto.UserRecordDto;
import br.com.faunora.domain.models.UserModel;
import br.com.faunora.services.UserService;
import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
    @Autowired
    private UserService userService;

    @PostMapping("/registrar")
    public ResponseEntity<RestMensagemRecordDto> registerUser(@RequestBody @Valid UserRecordDto userRecordDto) {
        userService.saveUser(userRecordDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new RestMensagemRecordDto("usuário registrado com sucesso"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestMensagemRecordDto> updateUser(@PathVariable Long id, @RequestBody UserRecordDto userRecordDto) {
        logger.info("Atualizando usuário com ID: {}", id);
        userService.updateUser(id, userRecordDto);
        logger.info("Usuário atualizado com novo token gerado.");
        return ResponseEntity.status(HttpStatus.OK).body(new RestMensagemRecordDto("usuário atualizado com sucesso"));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<RestMensagemRecordDto> deleteUserById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new RestMensagemRecordDto("usuário deletado com sucesso"));
    }
}


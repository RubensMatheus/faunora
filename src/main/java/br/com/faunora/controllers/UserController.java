package br.com.faunora.controllers;

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
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody @Valid UserRecordDto userRecordDto) {
        userService.saveUser(userRecordDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
            "message", "Usuário registrado com sucesso"
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateUser(@PathVariable Long id, @RequestBody UserRecordDto userRecordDto) {
        logger.info("Atualizando usuário com ID: {}", id);
        Map<String, Object> updatedUserData = userService.updateUser(id, userRecordDto);
        logger.info("Usuário atualizado com novo token gerado.");
        return ResponseEntity.status(HttpStatus.OK).body(updatedUserData);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado com sucesso");
    }
}


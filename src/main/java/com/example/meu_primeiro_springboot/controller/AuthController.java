package com.example.meu_primeiro_springboot.controller;

import com.example.meu_primeiro_springboot.DTO.LoginDTO;
import com.example.meu_primeiro_springboot.DTO.RegisterDTO;
import com.example.meu_primeiro_springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> postRegister(@RequestBody RegisterDTO registerDTO){
        userService.register(registerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cadastro efetuado com sucesso! ");
    }

    @PostMapping("/login")
    public ResponseEntity<String> postLogin(@RequestBody LoginDTO loginDTO){
        String acess = userService.login(loginDTO);

        if(acess.equals("Credenciais Inválidas.")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais erradas");
        } else {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(acess);
        }
    }

}

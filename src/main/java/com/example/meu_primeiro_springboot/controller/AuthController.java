package com.example.meu_primeiro_springboot.controller;

import com.example.meu_primeiro_springboot.DTO.LoginDTO;
import com.example.meu_primeiro_springboot.DTO.RegisterDTO;
import com.example.meu_primeiro_springboot.DTO.UpdateUserDTO;
import com.example.meu_primeiro_springboot.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {
    private final UserService userService;
    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> postRegister(@Valid @RequestBody RegisterDTO registerDTO){
        userService.register(registerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cadastro efetuado com sucesso! ");
    }

    @PostMapping("/login")
    public ResponseEntity<String> postLogin(@Valid @RequestBody LoginDTO loginDTO){
        String acess = userService.login(loginDTO);

        if(acess.equals("Credenciais Inválidas.")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais erradas");
        } else {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(acess);
        }
    }
    @PatchMapping("/updateUser")
    public ResponseEntity<String> patchUpdateUser(Authentication authentication, @Valid @RequestBody UpdateUserDTO updateUserDTO){
      String update = userService.alter(authentication.getName(), updateUserDTO);
      return ResponseEntity.ok(update);
    }



}

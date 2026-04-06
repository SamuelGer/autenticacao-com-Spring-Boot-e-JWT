package com.example.meu_primeiro_springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/me")
public class UserController {
    @GetMapping
    public String authenticationTest(){
        return "Você está autenticado!";
    }
}

package com.example.meu_primeiro_springboot.controller;

import com.example.meu_primeiro_springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/admin")
public class AdminController {

    @GetMapping("/dashboard")
    public String getDashboard (){
    return "Área administrativa!";
    }
}

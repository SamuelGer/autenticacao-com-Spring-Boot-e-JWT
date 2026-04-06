package com.example.meu_primeiro_springboot.DTO;

import lombok.Getter;

public class RegisterDTO {

    @Getter
    private String username;
    @Getter
    private String password;
    @Getter
    private String confirmPassword;
}

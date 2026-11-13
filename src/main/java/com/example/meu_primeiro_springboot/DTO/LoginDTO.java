package com.example.meu_primeiro_springboot.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class LoginDTO {
    @Getter
    @NotBlank
    private String identifier;
    @Getter
    @NotBlank
    private String password;
}

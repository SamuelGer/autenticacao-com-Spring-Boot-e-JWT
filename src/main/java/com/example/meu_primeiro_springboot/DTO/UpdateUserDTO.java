package com.example.meu_primeiro_springboot.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

public class UpdateUserDTO {
    @Getter
    @Pattern(regexp = "[a-zA-Z0-9._-]+", message = "O username só pode ter letras, números, ponto, hífen e underline.")
    private String username;
    @Getter
    private String currentPassword;
    @Getter
    @Size(min = 6, max = 20)
    private String newPassword;
    @Getter
    private String confirmPassword;
    @Getter
    @Email(regexp = "[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.[a-zA-Z0-9.-]{2,}",message = "Email Inválido. ")
    private String email;
}

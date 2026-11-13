package com.example.meu_primeiro_springboot.service;


import com.example.meu_primeiro_springboot.DTO.LoginDTO;
import com.example.meu_primeiro_springboot.DTO.RegisterDTO;
import com.example.meu_primeiro_springboot.DTO.UpdateUserDTO;
import com.example.meu_primeiro_springboot.config_security.JwtUtil;
import com.example.meu_primeiro_springboot.exceptions.InvalidCredentialsException;
import com.example.meu_primeiro_springboot.exceptions.SenhasNaoCoincidemException;
import com.example.meu_primeiro_springboot.model.User;
import com.example.meu_primeiro_springboot.repository.UserRepository;
import org.hibernate.service.UnknownServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtConfig;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtConfig) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtConfig = jwtConfig;
    }

    public void register(RegisterDTO register) {
        if (!register.getPassword().equals(register.getConfirmPassword())) {
            throw new SenhasNaoCoincidemException("As senhas não condizem.");
        }
        String encryptPassword = passwordEncoder.encode(register.getPassword());
        User user = new User(register.getUsername(), encryptPassword, register.getEmail());
        userRepository.save(user);
    }

    public String login(LoginDTO login){
        Optional<User> user = userRepository.findByUsernameOrEmail(login.getIdentifier());
        if(user.isPresent() && passwordEncoder.matches(login.getPassword(), user.get().getPassword())){
        return jwtConfig.generateToken(user.get().getUsername(), user.get().getRole());
        } else{
            throw new InvalidCredentialsException("Credenciais inválidas.");
        }
    }

    public String alter(String username, UpdateUserDTO updateUserDTO){
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));
        if(updateUserDTO.getUsername() != null){
            user.setUsername(updateUserDTO.getUsername());
        }
        if(updateUserDTO.getEmail() != null){
            user.setEmail(updateUserDTO.getEmail());
        }
        if (updateUserDTO.getNewPassword() != null) {
            if (updateUserDTO.getCurrentPassword() == null ) {
                throw new InvalidCredentialsException("Senha atual faltando. ");
            }
            if(!passwordEncoder.matches(updateUserDTO.getCurrentPassword(), user.getPassword())){
                throw new InvalidCredentialsException("Senha incorreta. ");
            }
            if (!updateUserDTO.getNewPassword().equals(updateUserDTO.getConfirmPassword())) {
                throw new SenhasNaoCoincidemException("As senhas não condizem.");
            }
            String encryptPassword = passwordEncoder.encode(updateUserDTO.getNewPassword());
            user.setPassword(encryptPassword);
        }
        userRepository.save(user);
        return jwtConfig.generateToken(user.getUsername(), user.getRole());
    }


}

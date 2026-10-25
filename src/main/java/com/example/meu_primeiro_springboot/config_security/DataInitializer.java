package com.example.meu_primeiro_springboot.config_security;

import com.example.meu_primeiro_springboot.model.Roles;
import com.example.meu_primeiro_springboot.model.User;
import com.example.meu_primeiro_springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner{
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    @Value("${admin.password}")
    private String adminPassword;
    @Autowired
    public DataInitializer(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(userRepository.findByUsername("admin").isEmpty()){
            User user = new User("admin", passwordEncoder.encode(adminPassword), Roles.ADMIN);
            userRepository.save(user);
        }
    }
}

package com.example.meu_primeiro_springboot.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
public class User {
    @Getter
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(unique = true, nullable = false )
    private String username;

    @Getter
    @Setter
    @Column(nullable = false)
    private String password;

    public User(){
    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
}

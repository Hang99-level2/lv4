package com.sparta.spartalecture.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="email",nullable = false)
    private String email;
    @Column(name ="password",nullable = false)
    private String password;
    @Column(name="gender",nullable = false)
    private String gender;
    @Column(name="number",nullable = false)
    private String number;
    @Column(name="address",nullable = false)
    private String address;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public User(String email, String password, String gender, String number, String address, String role) {
        this.email = email;
        this.password=password;
        this.gender = gender;
        this.number = number;
        this.address =address;
        if(role.equals("ADMIN")){
            this.role =  UserRoleEnum.ADMIN;
        }else{
            this.role =  UserRoleEnum.USER;
        }
    }

    public User(Long id) {
        this.id = id;
    }
}

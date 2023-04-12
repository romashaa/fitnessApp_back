package com.example.fitnessapp_back.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Data
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String role;
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_user_id")
    private User user;

    public Role(String role){
        this.role=role;
    }

    public Role() {

    }

    @Override
    public String getAuthority() {
        return role;
    }
}

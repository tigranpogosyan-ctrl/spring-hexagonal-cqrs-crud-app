package com.gs.springhexagonalcqrscrudapp.adapter.out;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class UserJpaEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    public UserJpaEntity(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}

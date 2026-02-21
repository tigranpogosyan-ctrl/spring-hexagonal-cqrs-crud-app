package com.gs.springhexagonalcqrscrudapp.application.command;

import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdateUserCommand {

    private final UUID id;
    private final String username;
    private final String email;
    private final String password;

    public UpdateUserCommand(UUID id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}

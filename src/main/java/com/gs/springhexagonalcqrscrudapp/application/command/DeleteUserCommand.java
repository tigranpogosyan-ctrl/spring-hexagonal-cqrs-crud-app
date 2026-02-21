package com.gs.springhexagonalcqrscrudapp.application.command;

import lombok.Getter;

import java.util.UUID;

@Getter
public class DeleteUserCommand {
    private final UUID id;

    public DeleteUserCommand(UUID id) {
        this.id = id;
    }
}

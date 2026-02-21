package com.gs.springhexagonalcqrscrudapp.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateUserCommand {
    private final String username;
    private final String email;
    private final String password;
}

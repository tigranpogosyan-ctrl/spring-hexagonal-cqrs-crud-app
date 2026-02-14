package com.gs.springhexagonalcqrscrudapp.application.command.handler;

import com.gs.springhexagonalcqrscrudapp.application.command.CreateUserCommand;
import com.gs.springhexagonalcqrscrudapp.application.command.DeleteUserCommand;
import com.gs.springhexagonalcqrscrudapp.application.command.UpdateUserCommand;
import com.gs.springhexagonalcqrscrudapp.domain.model.User;
import com.gs.springhexagonalcqrscrudapp.domain.port.out.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserCommandHandler {

    private final UserRepositoryPort userRepositoryPort;

    public User handle(CreateUserCommand command) {
        User user = new User(UUID.randomUUID(), command.getUsername(), command.getEmail(), command.getPassword());
        return userRepositoryPort.create(user);
    }

    // Update
    public Optional<User> handle(UpdateUserCommand command) {
        Optional<User> existingUser = userRepositoryPort.findById(command.getId());
        if (existingUser.isEmpty()) return Optional.empty();

        User user = existingUser.get();
        user.setUsername(command.getUsername());
        user.setEmail(command.getEmail());
        if (command.getPassword() != null && !command.getPassword().isBlank()) {
            user.setPassword(command.getPassword());
        }

        return Optional.of(userRepositoryPort.update(user));
    }

    // Delete
    public boolean handle(DeleteUserCommand command) {
        Optional<User> existingUser = userRepositoryPort.findById(command.getId());
        if (existingUser.isEmpty()) return false;

        userRepositoryPort.deleteById(command.getId());
        return true;
    }
}



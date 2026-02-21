package com.gs.springhexagonalcqrscrudapp.adapter.in.web;

import com.gs.springhexagonalcqrscrudapp.adapter.in.web.dto.CreateUserRequest;
import com.gs.springhexagonalcqrscrudapp.adapter.in.web.dto.UpdateUserRequest;
import com.gs.springhexagonalcqrscrudapp.adapter.in.web.dto.UserResponse;
import com.gs.springhexagonalcqrscrudapp.application.command.CreateUserCommand;
import com.gs.springhexagonalcqrscrudapp.application.command.DeleteUserCommand;
import com.gs.springhexagonalcqrscrudapp.application.command.UpdateUserCommand;
import com.gs.springhexagonalcqrscrudapp.application.command.handler.UserCommandHandler;
import com.gs.springhexagonalcqrscrudapp.application.query.GetUserQuery;
import com.gs.springhexagonalcqrscrudapp.application.query.handler.UserQueryHandler;
import com.gs.springhexagonalcqrscrudapp.domain.model.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserCommandHandler userCommandHandler;
    private final UserQueryHandler userQueryHandler;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserRequest request) {
        User user = userCommandHandler.handle(new CreateUserCommand(
                request.username(), request.email(), request.password()
        ));
        return ResponseEntity.ok(toResponse(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable UUID id,
                                                   @Valid @RequestBody UpdateUserRequest request) {
        Optional<User> updated = userCommandHandler.handle(new UpdateUserCommand(
                id, request.username(), request.email(), request.password()
        ));
        return updated.map(user -> ResponseEntity.ok(toResponse(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        boolean deleted = userCommandHandler.handle(new DeleteUserCommand(id));
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // Get by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable UUID id) {
        Optional<User> user = userQueryHandler.handle(new GetUserQuery(id));
        return user.map(u -> ResponseEntity.ok(toResponse(u)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Get all
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = userQueryHandler.getAllUsers()
                .stream().map(this::toResponse).collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    private UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getUsername(), user.getEmail());
    }
}



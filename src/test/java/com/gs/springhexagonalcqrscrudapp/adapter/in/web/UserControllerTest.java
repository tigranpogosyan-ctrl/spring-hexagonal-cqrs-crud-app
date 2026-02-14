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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserControllerTest {

    private UserCommandHandler commandHandler;
    private UserQueryHandler queryHandler;
    private UserController controller;

    @BeforeEach
    void setup() {
        commandHandler = mock(UserCommandHandler.class);
        queryHandler = mock(UserQueryHandler.class);
        controller = new UserController(commandHandler, queryHandler);
    }

    @Test
    void testCreateUser() {
        CreateUserRequest request = new CreateUserRequest("John", "john@example.com", "1234");
        User user = new User(UUID.randomUUID(), "John", "john@example.com", "1234");

        when(commandHandler.handle(any(CreateUserCommand.class))).thenReturn(user);

        ResponseEntity<UserResponse> response = controller.createUser(request);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("John", response.getBody().username());
        assertEquals("john@example.com", response.getBody().email());

        verify(commandHandler).handle(any(CreateUserCommand.class));
    }

    @Test
    void testUpdateUser() {
        UUID id = UUID.randomUUID();
        UpdateUserRequest request = new UpdateUserRequest("John Updated", "john2@example.com", "5678");
        User updatedUser = new User(id, "John Updated", "john2@example.com", "5678");

        when(commandHandler.handle(any(UpdateUserCommand.class))).thenReturn(Optional.of(updatedUser));

        ResponseEntity<UserResponse> response = controller.updateUser(id, request);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("John Updated", response.getBody().username());
        assertEquals("john2@example.com", response.getBody().email());

        verify(commandHandler).handle(any(UpdateUserCommand.class));
    }

    @Test
    void testUpdateUserNotFound() {
        UUID id = UUID.randomUUID();
        UpdateUserRequest request = new UpdateUserRequest("John Updated", "john2@example.com", "5678");

        when(commandHandler.handle(any(UpdateUserCommand.class))).thenReturn(Optional.empty());

        ResponseEntity<UserResponse> response = controller.updateUser(id, request);

        assertEquals(404, response.getStatusCode().value());
        verify(commandHandler).handle(any(UpdateUserCommand.class));
    }

    @Test
    void testDeleteUser() {
        UUID id = UUID.randomUUID();

        when(commandHandler.handle(any(DeleteUserCommand.class))).thenReturn(true);

        ResponseEntity<Void> response = controller.deleteUser(id);

        assertEquals(204, response.getStatusCode().value());
        verify(commandHandler).handle(any(DeleteUserCommand.class));
    }

    @Test
    void testDeleteUserNotFound() {
        UUID id = UUID.randomUUID();

        when(commandHandler.handle(any(DeleteUserCommand.class))).thenReturn(false);

        ResponseEntity<Void> response = controller.deleteUser(id);

        assertEquals(404, response.getStatusCode().value());
        verify(commandHandler).handle(any(DeleteUserCommand.class));
    }

    @Test
    void testGetUserById() {
        UUID id = UUID.randomUUID();
        User user = new User(id, "John", "john@example.com", "1234");

        when(queryHandler.handle(any(GetUserQuery.class))).thenReturn(Optional.of(user));

        ResponseEntity<UserResponse> response = controller.getUser(id);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("John", response.getBody().username());
        verify(queryHandler).handle(any(GetUserQuery.class));
    }

    @Test
    void testGetUserByIdNotFound() {
        UUID id = UUID.randomUUID();

        when(queryHandler.handle(any(GetUserQuery.class))).thenReturn(Optional.empty());

        ResponseEntity<UserResponse> response = controller.getUser(id);

        assertEquals(404, response.getStatusCode().value());
        verify(queryHandler).handle(any(GetUserQuery.class));
    }

    @Test
    void testGetAllUsers() {
        User user1 = new User(UUID.randomUUID(), "John", "john@example.com", "1234");
        User user2 = new User(UUID.randomUUID(), "Jane", "jane@example.com", "abcd");

        when(queryHandler.getAllUsers()).thenReturn(List.of(user1, user2));

        ResponseEntity<List<UserResponse>> response = controller.getAllUsers();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(2, response.getBody().size());
        assertEquals("John", response.getBody().get(0).username());
        assertEquals("Jane", response.getBody().get(1).username());

        verify(queryHandler).getAllUsers();
    }
}

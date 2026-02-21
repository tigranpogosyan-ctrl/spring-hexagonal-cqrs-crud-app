package com.gs.springhexagonalcqrscrudapp.application.command.handler;

import com.gs.springhexagonalcqrscrudapp.application.command.CreateUserCommand;
import com.gs.springhexagonalcqrscrudapp.application.command.DeleteUserCommand;
import com.gs.springhexagonalcqrscrudapp.application.command.UpdateUserCommand;
import com.gs.springhexagonalcqrscrudapp.domain.model.User;
import com.gs.springhexagonalcqrscrudapp.domain.port.out.UserRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserCommandHandlerTest {

    private UserRepositoryPort repositoryPort;
    private UserCommandHandler handler;

    @BeforeEach
    void setup() {
        repositoryPort = mock(UserRepositoryPort.class);
        handler = new UserCommandHandler(repositoryPort);
    }

    @Test
    void testCreateUser() {
        CreateUserCommand command = new CreateUserCommand("John", "john@example.com", "1234");
        User user = new User(UUID.randomUUID(), "John", "john@example.com", "1234");

        when(repositoryPort.create(any(User.class))).thenReturn(user);

        User result = handler.handle(command);
        assertNotNull(result.getId());
        assertEquals("John", result.getUsername());
        verify(repositoryPort).create(any(User.class));
    }

    @Test
    void testUpdateUser() {
        UUID id = UUID.randomUUID();
        UpdateUserCommand command = new UpdateUserCommand(id, "John Updated", "john2@example.com", "5678");
        User existing = new User(id, "John", "john@example.com", "1234");

        // MOCK: findById returns existing user
        when(repositoryPort.findById(id)).thenReturn(Optional.of(existing));

        // MOCK: update returns the same user
        when(repositoryPort.update(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Optional<User> updatedOpt = handler.handle(command);
        assertTrue(updatedOpt.isPresent());

        User updated = updatedOpt.get();
        assertEquals("John Updated", updated.getUsername());
        assertEquals("john2@example.com", updated.getEmail());
        assertEquals("5678", updated.getPassword());

        verify(repositoryPort).findById(id);
        verify(repositoryPort).update(any(User.class));
    }

    @Test
    void testDeleteUser() {
        UUID id = UUID.randomUUID();
        User existing = new User(id, "John", "john@example.com", "1234");

        // MOCK: findById returns existing user
        when(repositoryPort.findById(id)).thenReturn(Optional.of(existing));

        // MOCK: deleteById does nothing
        doNothing().when(repositoryPort).deleteById(id);

        boolean result = handler.handle(new DeleteUserCommand(id));
        assertTrue(result);

        verify(repositoryPort).findById(id);
        verify(repositoryPort).deleteById(id);
    }

}

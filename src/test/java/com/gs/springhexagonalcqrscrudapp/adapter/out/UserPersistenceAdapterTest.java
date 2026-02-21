package com.gs.springhexagonalcqrscrudapp.adapter.out;

import com.gs.springhexagonalcqrscrudapp.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserPersistenceAdapterTest {

    private UserJpaRepository userJpaRepository;
    private UserPersistenceAdapter adapter;

    @BeforeEach
    void setup() {
        userJpaRepository = mock(UserJpaRepository.class);
        adapter = new UserPersistenceAdapter(userJpaRepository);
    }

    @Test
    void testCreate() {
        User user = new User(null, "John", "john@example.com", "1234");
        UserJpaEntity entity = new UserJpaEntity("John", "john@example.com", "1234");
        entity.setId(UUID.randomUUID());

        when(userJpaRepository.save(any(UserJpaEntity.class))).thenReturn(entity);

        User saved = adapter.create(user);

        assertNotNull(saved.getId());
        assertEquals("John", saved.getUsername());
        verify(userJpaRepository, times(1)).save(any(UserJpaEntity.class));
    }

    @Test
    void testUpdateExistingUser() {
        UUID id = UUID.randomUUID();
        User user = new User(id, "John Updated", "john2@example.com", "5678");
        UserJpaEntity entity = new UserJpaEntity("John", "john@example.com", "1234");
        entity.setId(id);

        when(userJpaRepository.findById(id)).thenReturn(Optional.of(entity));
        when(userJpaRepository.save(entity)).thenReturn(entity);

        User updated = adapter.update(user);

        assertEquals("John Updated", updated.getUsername());
        assertEquals("john2@example.com", updated.getEmail());
        verify(userJpaRepository).findById(id);
        verify(userJpaRepository).save(entity);
    }

    @Test
    void testFindById() {
        UUID id = UUID.randomUUID();
        UserJpaEntity entity = new UserJpaEntity("John", "john@example.com", "1234");
        entity.setId(id);

        when(userJpaRepository.findById(id)).thenReturn(Optional.of(entity));

        Optional<User> user = adapter.findById(id);
        assertTrue(user.isPresent());
        assertEquals("John", user.get().getUsername());
    }

    @Test
    void testDeleteById() {
        UUID id = UUID.randomUUID();
        adapter.deleteById(id);
        verify(userJpaRepository).deleteById(id);
    }

    @Test
    void testFindAll() {
        UserJpaEntity entity1 = new UserJpaEntity("John", "john@example.com", "1234");
        entity1.setId(UUID.randomUUID());
        UserJpaEntity entity2 = new UserJpaEntity("Jane", "jane@example.com", "abcd");
        entity2.setId(UUID.randomUUID());

        when(userJpaRepository.findAll()).thenReturn(List.of(entity1, entity2));

        List<User> users = adapter.findAll();
        assertEquals(2, users.size());
    }
}

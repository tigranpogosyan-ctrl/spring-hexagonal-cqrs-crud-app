package com.gs.springhexagonalcqrscrudapp.adapter.out;

import com.gs.springhexagonalcqrscrudapp.domain.model.User;
import com.gs.springhexagonalcqrscrudapp.domain.port.out.UserRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UserPersistenceAdapter implements UserRepositoryPort {

    private final UserJpaRepository userJpaRepository;

    public UserPersistenceAdapter(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    // ---------------- CREATE ----------------
    public User create(User user) {
        // Always create new entity, let DB generate ID
        UserJpaEntity entity = new UserJpaEntity(user.getUsername(), user.getEmail(), user.getPassword());
        UserJpaEntity saved = userJpaRepository.save(entity);
        return mapToDomain(saved);
    }

    // ---------------- UPDATE ----------------
    public User update(User user) {
        if (user.getId() == null) {
            throw new IllegalArgumentException("User ID must not be null for update");
        }

        // Fetch existing entity from DB
        UserJpaEntity entity = userJpaRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + user.getId()));

        // Update only mutable fields
        entity.setUsername(user.getUsername());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());

        UserJpaEntity saved = userJpaRepository.save(entity);
        return mapToDomain(saved);
    }

    // ---------------- DELETE ----------------
    @Override
    public void deleteById(UUID id) {
        userJpaRepository.deleteById(id);
    }

    // ---------------- READ ----------------
    @Override
    public Optional<User> findById(UUID id) {
        return userJpaRepository.findById(id)
                .map(this::mapToDomain);
    }


    @Override
    public List<User> findAll() {
        return userJpaRepository.findAll()
                .stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    // ---------------- MAPPING ----------------
    private User mapToDomain(UserJpaEntity entity) {
        return new User(entity.getId(), entity.getUsername(), entity.getEmail(), entity.getPassword());
    }
}

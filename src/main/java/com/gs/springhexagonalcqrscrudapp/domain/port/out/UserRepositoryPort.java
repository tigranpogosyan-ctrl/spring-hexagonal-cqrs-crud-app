package com.gs.springhexagonalcqrscrudapp.domain.port.out;

import com.gs.springhexagonalcqrscrudapp.domain.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryPort {

    User create(User user);

    User update(User user);

    Optional<User> findById(UUID id);

    List<User> findAll();

    void deleteById(UUID id);
}

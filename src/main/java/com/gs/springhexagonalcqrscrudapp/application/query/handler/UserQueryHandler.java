package com.gs.springhexagonalcqrscrudapp.application.query.handler;

import com.gs.springhexagonalcqrscrudapp.application.query.GetUserQuery;
import com.gs.springhexagonalcqrscrudapp.domain.model.User;
import com.gs.springhexagonalcqrscrudapp.domain.port.out.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserQueryHandler {

    private final UserRepositoryPort userRepositoryPort;

    public Optional<User> handle(GetUserQuery query) {
        return userRepositoryPort.findById(query.getId());
    }

    public List<User> getAllUsers() {
        return userRepositoryPort.findAll();
    }
}

package com.gs.springhexagonalcqrscrudapp.application.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class GetUserQuery {
    private final UUID id;
}

package com.gs.springhexagonalcqrscrudapp.adapter.in.web.dto;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String username,
        String email
) {}

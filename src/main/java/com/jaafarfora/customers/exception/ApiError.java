package com.jaafarfora.customers.exception;

import java.time.LocalDateTime;

public record ApiError(
    String path,
    String[] messages,
    int statusCode,
    LocalDateTime localDateTime
) {
}
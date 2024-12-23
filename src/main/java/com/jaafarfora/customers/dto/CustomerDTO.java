package com.jaafarfora.customers.dto;

import jakarta.validation.constraints.NotBlank;

public record CustomerDTO(
    Long id,
    @NotBlank
    String firstName,
    @NotBlank
    String lastName,
    @NotBlank
    String email,
    @NotBlank
    String address
) {
}

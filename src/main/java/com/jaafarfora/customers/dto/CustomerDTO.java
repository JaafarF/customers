package com.jaafarfora.customers.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
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

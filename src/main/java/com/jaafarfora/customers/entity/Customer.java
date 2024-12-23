package com.jaafarfora.customers.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "first_name", nullable = false)
  @NotBlank
  private String firstName;

  @Column(name = "last_name", nullable = false)
  @NotBlank
  private String lastName;

  @Column(name = "email", nullable = false)
  @NotBlank
  private String email;

  @Column(name = "address", nullable = false)
  @NotBlank
  private String address;

}

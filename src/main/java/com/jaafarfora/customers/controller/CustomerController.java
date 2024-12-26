package com.jaafarfora.customers.controller;

import com.jaafarfora.customers.dto.CustomerDTO;
import com.jaafarfora.customers.service.CustomerServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/customers")
public class CustomerController {

  private final CustomerServiceImpl customerService;

  @GetMapping
  public ResponseEntity<String> findAll() {
    return ResponseEntity.ok().body("All customers found");
  }

  @PostMapping
  public ResponseEntity<Long> registerCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
    Long registeredCustomerId = customerService.registerCustomer(customerDTO);
    return ResponseEntity.ok()
        .body(registeredCustomerId);
  }
}

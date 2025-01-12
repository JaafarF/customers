package com.jaafarfora.customers.controller;

import com.jaafarfora.customers.dto.CustomerDTO;
import com.jaafarfora.customers.service.CustomerServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/customers")
public class CustomerController {

  private final CustomerServiceImpl customerService;

  @GetMapping(path = "test")
  public ResponseEntity<String> testApp() {
    return ResponseEntity.ok().body("App running!!!");
  }

  @GetMapping
  public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
    var dtos = customerService.getAllCustomers();
    return ResponseEntity.ok().body(dtos);
  }

  @PostMapping
  public ResponseEntity<Long> registerCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
    Long registeredCustomerId = customerService.registerCustomer(customerDTO);
    return ResponseEntity.ok()
        .body(registeredCustomerId);
  }
}

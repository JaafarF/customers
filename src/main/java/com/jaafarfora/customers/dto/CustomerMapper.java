package com.jaafarfora.customers.dto;

import com.jaafarfora.customers.entity.Customer;

public class CustomerMapper {

  public static CustomerDTO convertToDto(Customer customer) {
    return CustomerDTO.builder()
        .id(customer.getId())
        .firstName(customer.getFirstName())
        .lastName(customer.getLastName())
        .email(customer.getEmail())
        .address(customer.getAddress())
        .build();
  }

  public static Customer convertToEntity(CustomerDTO customerDTO) {
    return Customer.builder()
        .id(customerDTO.id())
        .firstName(customerDTO.firstName())
        .lastName(customerDTO.lastName())
        .email(customerDTO.email())
        .address(customerDTO.address())
        .build();
  }
}

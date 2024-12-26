package com.jaafarfora.customers.service;

import com.jaafarfora.customers.dto.CustomerDTO;

public interface CustomerService {

  Long registerCustomer(CustomerDTO customerDTO);
}

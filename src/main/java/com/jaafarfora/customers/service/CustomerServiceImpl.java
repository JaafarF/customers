package com.jaafarfora.customers.service;

import com.jaafarfora.customers.dao.CustomerRepository;
import com.jaafarfora.customers.dto.CustomerDTO;
import com.jaafarfora.customers.dto.CustomerDTOMapper;
import com.jaafarfora.customers.entity.Customer;
import com.jaafarfora.customers.exception.DuplicateResourceException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;

  public CustomerServiceImpl(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public Long registerCustomer(CustomerDTO customerDTO) {
    if (customerRepository.existsByEmail(customerDTO.email())) {
      throw new DuplicateResourceException("Customer with this email already exists");
    }
    Customer customer = CustomerDTOMapper.INSTANCE.dtoToModel(customerDTO);
    Customer registeredCustomer = customerRepository.save(customer);
    return registeredCustomer.getId();
  }
}

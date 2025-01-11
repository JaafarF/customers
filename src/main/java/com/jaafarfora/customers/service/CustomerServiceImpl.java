package com.jaafarfora.customers.service;

import com.jaafarfora.customers.dao.CustomerRepository;
import com.jaafarfora.customers.dto.CustomerDTO;
import com.jaafarfora.customers.dto.CustomerMapper;
import com.jaafarfora.customers.entity.Customer;
import com.jaafarfora.customers.exception.DuplicateResourceException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;

  public CustomerServiceImpl(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public List<CustomerDTO> getAllCustomers() {
    List<Customer> customers = customerRepository.findAll();
    List<CustomerDTO> dtos = new ArrayList<>();
    for (Customer customer : customers) {
      dtos.add(CustomerMapper.convertToDto(customer));
    }
    return dtos;
  }

  public Long registerCustomer(CustomerDTO customerDTO) {
    if (customerRepository.existsByEmail(customerDTO.email())) {
      throw new DuplicateResourceException("Customer with this email already exists");
    }
    Customer customer = CustomerMapper.convertToEntity(customerDTO);
    Customer registeredCustomer = customerRepository.save(customer);
    return registeredCustomer.getId();
  }
}

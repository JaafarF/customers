package com.jaafarfora.customers.service;

import com.jaafarfora.customers.dao.CustomerDao;
import com.jaafarfora.customers.dto.CustomerDTO;
import com.jaafarfora.customers.dto.CustomerDTOMapper;
import com.jaafarfora.customers.entity.Customer;
import com.jaafarfora.customers.exception.DuplicateResourceException;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  private final CustomerDao customerDao;

  public CustomerService(CustomerDao customerDao) {
    this.customerDao = customerDao;
  }

  public Long registerCustomer(CustomerDTO customerDTO) {
    if (customerDao.existCustomerByEmail(customerDTO.email())) {
      throw new DuplicateResourceException("Customer with this email already exists");
    }
    Customer customer = CustomerDTOMapper.INSTANCE.dtoToModel(customerDTO);
    Customer registeredCustomer = customerDao.addCustomer(customer);
    return registeredCustomer.getId();
  }
}

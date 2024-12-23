package com.jaafarfora.customers.dao.jpa;

import com.jaafarfora.customers.dao.CustomerDao;
import com.jaafarfora.customers.entity.Customer;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDaoJpaImpl implements CustomerDao {

  private final CustomerRepository customerRepository;

  public CustomerDaoJpaImpl(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }


  @Override
  public Customer addCustomer(Customer customer) {
    return customerRepository.save(customer);
  }

  @Override
  public boolean existCustomerByEmail(String email) {
    return customerRepository.existsCustomerByEmail(email);
  }
}

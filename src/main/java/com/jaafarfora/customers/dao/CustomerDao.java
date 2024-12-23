package com.jaafarfora.customers.dao;

import com.jaafarfora.customers.entity.Customer;

public interface CustomerDao {
  Customer addCustomer(Customer customer);

  boolean existCustomerByEmail(String email);
}

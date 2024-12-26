package com.jaafarfora.customers.dao;

import com.jaafarfora.customers.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

  boolean existsByEmail(String email);
}

package com.jaafarfora.customers.dao.jpa;

import com.jaafarfora.customers.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

  boolean existsCustomerByEmail(String email);
}

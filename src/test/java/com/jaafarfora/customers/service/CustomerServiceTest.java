package com.jaafarfora.customers.service;

import com.jaafarfora.customers.dao.CustomerDao;
import com.jaafarfora.customers.dto.CustomerDTO;
import com.jaafarfora.customers.entity.Customer;
import com.jaafarfora.customers.exception.DuplicateResourceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

  @Mock
  private CustomerDao customerDao;

  private CustomerService underTest;

  @BeforeEach
  void setUp() {
    underTest = new CustomerService(customerDao);
  }


  @Test
  void shouldCreateCustomer() {

    // Given
    CustomerDTO request = new CustomerDTO(null,"firstName", "lastName", "email@email.com", "address");
    when(customerDao.existCustomerByEmail(request.email())).thenReturn(false);

    // When
    underTest.registerCustomer(request);

    // Then
    ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);
    verify(customerDao).addCustomer(customerArgumentCaptor.capture());
    Customer capturedCustomer = customerArgumentCaptor.getValue();

    assertThat(capturedCustomer.getId()).isNull();
    assertThat(capturedCustomer.getFirstName()).isEqualTo(request.firstName());
    assertThat(capturedCustomer.getLastName()).isEqualTo(request.lastName());
    assertThat(capturedCustomer.getEmail()).isEqualTo(request.email());
    assertThat(capturedCustomer.getAddress()).isEqualTo(request.address());
  }

  @Test
  void shouldThrowWhenCreateCustomerWithExistingEmail() {
    // Given
    CustomerDTO request = new CustomerDTO(null,"firstName", "lastName", "email@email.com", "address");
    when(customerDao.existCustomerByEmail(request.email())).thenReturn(true);

    //  Then
    assertThatThrownBy(() -> underTest.registerCustomer(request))
        .isInstanceOf(DuplicateResourceException.class)
        .hasMessage("Customer with this email already exists");

    verify(customerDao, never()).addCustomer(any());
  }

  }

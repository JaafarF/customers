package com.jaafarfora.customers.service;

import com.jaafarfora.customers.dao.CustomerRepository;
import com.jaafarfora.customers.dto.CustomerDTO;
import com.jaafarfora.customers.dto.CustomerMapper;
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
  private CustomerRepository customerRepository;

  private CustomerServiceImpl underTest;

  @BeforeEach
  void setUp() {
    underTest = new CustomerServiceImpl(customerRepository);
  }


  @Test
  void shouldCreateCustomer() {

    // Given
    CustomerDTO request = new CustomerDTO(null,"firstName", "lastName", "email@email.com", "address");
    CustomerDTO registered = new CustomerDTO(1L,"firstName", "lastName", "email@email.com", "address");
    when(customerRepository.existsByEmail(request.email())).thenReturn(false);
    when(customerRepository.save(CustomerMapper.convertToEntity(request))).thenReturn(CustomerMapper.convertToEntity(registered));

    // When
    underTest.registerCustomer(request);

    // Then
    ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);
    verify(customerRepository).save(customerArgumentCaptor.capture());
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
    when(customerRepository.existsByEmail(request.email())).thenReturn(true);

    //  Then
    assertThatThrownBy(() -> underTest.registerCustomer(request))
        .isInstanceOf(DuplicateResourceException.class)
        .hasMessage("Customer with this email already exists");

    verify(customerRepository, never()).save(any());
  }

}

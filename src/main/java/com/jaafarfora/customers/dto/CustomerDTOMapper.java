package com.jaafarfora.customers.dto;

import com.jaafarfora.customers.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerDTOMapper {

  CustomerDTOMapper INSTANCE = Mappers.getMapper(CustomerDTOMapper.class);

  CustomerDTO modelToDTO(Customer customer);
  Customer dtoToModel(CustomerDTO customerDTO);
}

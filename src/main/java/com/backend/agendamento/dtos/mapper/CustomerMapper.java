package com.backend.agendamento.DTOs.mapper;


import com.backend.agendamento.DTOs.request.CustomerRequest;
import com.backend.agendamento.DTOs.response.CustomerResponse;
import com.backend.agendamento.entity.Customer;
import lombok.experimental.UtilityClass;


@UtilityClass
public class CustomerMapper {


    public Customer toDtoRequest(CustomerRequest request){
        return Customer.builder()
                .name(request.name())
                .phone(request.phone())
                .email(request.email())
                .build();
    }

    public CustomerResponse toDtoResponse(Customer customer){
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .phone(customer.getPhone())
                .email(customer.getEmail())
                .createdAt(customer.getCreatedAt())
                .build();
    }
}

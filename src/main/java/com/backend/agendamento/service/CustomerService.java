package com.backend.agendamento.service;

import com.backend.agendamento.DTOs.mapper.CustomerMapper;
import com.backend.agendamento.DTOs.request.CustomerRequest;
import com.backend.agendamento.DTOs.response.CustomerResponse;
import com.backend.agendamento.entity.Customer;
import com.backend.agendamento.exception.ConflictException;
import com.backend.agendamento.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;


    public Customer saveCustomer(Customer customer){

        verifyByemail(customer.getEmail());

        return customerRepository.save(customer);


    }


    public void verifyByemail(String email){
        if (existsEmail(email)){
            throw new ConflictException("This email address is not available.");
        }
    }

    public boolean existsEmail(String email){
        return customerRepository.existsByEmail(email);
    }


}

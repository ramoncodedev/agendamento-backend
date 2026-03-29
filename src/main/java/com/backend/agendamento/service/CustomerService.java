package com.backend.agendamento.service;


import com.backend.agendamento.entity.Customer;
import com.backend.agendamento.exception.ConflictException;
import com.backend.agendamento.exception.NoCustomersAvailableException;
import com.backend.agendamento.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;


    @Transactional
    public Customer saveCustomer(Customer customer){

        verifyByemail(customer.getEmail());

        return customerRepository.save(customer);


    }

    @Transactional(readOnly = true)
    public void verifyByemail(String email){
        if (existsEmail(email)){
            throw new ConflictException("This email address is not available.");
        }
    }

    @Transactional(readOnly = true)
    public boolean existsEmail(String email){
        return customerRepository.existsByEmail(email);
    }

    @Transactional(readOnly = true)
    public List<Customer> findAll(){
        List<Customer> customers = customerRepository.findAll();

        if (customers.isEmpty()){
            throw new NoCustomersAvailableException("Não há clientes disponíveis.");
        }

        return customers;
    }


}

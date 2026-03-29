package com.backend.agendamento.controller;


import com.backend.agendamento.DTOs.mapper.CustomerMapper;
import com.backend.agendamento.DTOs.request.CustomerRequest;
import com.backend.agendamento.DTOs.response.CustomerResponse;
import com.backend.agendamento.entity.Customer;
import com.backend.agendamento.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CustomerRequest request){

        Customer customer = CustomerMapper.toDtoRequest(request);
        Customer save = customerService.saveCustomer(customer);

        return ResponseEntity.status(HttpStatus.CREATED).body(CustomerMapper.toDtoResponse(save));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CustomerResponse>> findAllCustomes(){
        List<Customer> customerList = customerService.findAll();
        List<CustomerResponse> responses = customerList.stream()
                .map(customer -> CustomerMapper.toDtoResponse(customer)).toList();

        return ResponseEntity.ok().body(responses);
    }
}

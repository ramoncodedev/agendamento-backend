package com.backend.agendamento.controller;


import com.backend.agendamento.DTOs.mapper.CustomerMapper;
import com.backend.agendamento.DTOs.request.CustomerRequest;
import com.backend.agendamento.DTOs.response.CustomerResponse;
import com.backend.agendamento.entity.Customer;
import com.backend.agendamento.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest request){

        Customer customer = CustomerMapper.toDtoRequest(request);
        Customer save = customerService.saveCustomer(customer);

        return ResponseEntity.status(HttpStatus.CREATED).body(CustomerMapper.toDtoResponse(save));
    }
}

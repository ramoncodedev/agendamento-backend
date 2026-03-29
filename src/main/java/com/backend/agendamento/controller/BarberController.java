package com.backend.agendamento.controller;


import com.backend.agendamento.DTOs.mapper.BarberMapper;
import com.backend.agendamento.DTOs.request.BarberRequest;
import com.backend.agendamento.DTOs.response.BarberResponse;
import com.backend.agendamento.entity.Barber;
import com.backend.agendamento.service.BarberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/barber")
public class BarberController {

    private final BarberService barberService;

    @PostMapping("/create")
    public ResponseEntity<BarberResponse> createBarbe( @Valid @RequestBody BarberRequest barberRequest){
        Barber barber = BarberMapper.toDtoRequest(barberRequest);
        Barber save = barberService.saveBarber(barber);

        return ResponseEntity.status(HttpStatus.CREATED).body(BarberMapper.toDtoResponse(save));
    }

    @GetMapping("/barbers")
    public ResponseEntity<List<BarberResponse>> findBarber(){
        List<Barber> barbers = barberService.findAll();
        List<BarberResponse> responses = barbers.stream().map(barber -> BarberMapper.toDtoResponse(barber)).toList();

        return ResponseEntity.ok().body(responses);
    }

    @GetMapping()
    public ResponseEntity<List<BarberResponse>> findByName( @RequestParam String name){
        List<Barber> barbers = barberService.findByName(name);
        List<BarberResponse> barberResponses = barbers.stream().map(barber -> BarberMapper.toDtoResponse(barber)).toList();

        return ResponseEntity.ok().body(barberResponses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBarber( @PathVariable Long id){
        barberService.deleteBarber(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

package com.backend.agendamento.controller;

import com.backend.agendamento.DTOs.mapper.AppointmentMapper;
import com.backend.agendamento.DTOs.request.AppointmentRequest;
import com.backend.agendamento.DTOs.response.AppointmentResponse;
import com.backend.agendamento.entity.Appointment;
import com.backend.agendamento.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;



    @PostMapping
    public ResponseEntity<AppointmentResponse> createAppointment( @Valid @RequestBody AppointmentRequest appointmentRequest ){
        Appointment saveAppointment = AppointmentMapper.toRequest(appointmentRequest);
        Appointment newAppointment = appointmentService.saveAppointment(saveAppointment);

        return ResponseEntity.status(HttpStatus.CREATED).body(AppointmentMapper.toResponse(newAppointment));
    }

    @GetMapping("/all")
    public ResponseEntity<List<AppointmentResponse>> getAllAppointments() {
        List<Appointment> appointments = appointmentService.findAllAppointments();
        List<AppointmentResponse> findResponse = appointments.stream()
                .map(appointment -> AppointmentMapper.toResponse(appointment)).toList();

        return ResponseEntity.ok().body(findResponse);
    }


    @GetMapping("/by-customer-name")
    public ResponseEntity<List<AppointmentResponse>> getAppointmentsByCustomerName(
            @RequestParam(name = "name") String customerName) {

            List<Appointment> appointments = appointmentService.findByCustomerName(customerName);

            List<AppointmentResponse> responses = appointments.stream()
                    .map(AppointmentMapper::toResponse).toList();
            return ResponseEntity.ok(responses);

    }


}


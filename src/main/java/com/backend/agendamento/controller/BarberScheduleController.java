package com.backend.agendamento.controller;


import com.backend.agendamento.DTOs.mapper.BarberScheduleMapper;
import com.backend.agendamento.DTOs.request.BarberScheduleRequest;
import com.backend.agendamento.DTOs.response.BarberScheduleResponse;
import com.backend.agendamento.entity.BarberSchedule;
import com.backend.agendamento.service.BarberScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/schedule")
public class BarberScheduleController {

    private final BarberScheduleService barberScheduleService;

    @PostMapping("/create")
    public ResponseEntity<BarberScheduleResponse> createSchedule( @Valid  @RequestBody BarberScheduleRequest scheduleRequest){

        BarberSchedule schedule = BarberScheduleMapper.toRequest(scheduleRequest);
        BarberSchedule newShedule = barberScheduleService.saveSchedule(schedule);

        return ResponseEntity.status(HttpStatus.CREATED).body(BarberScheduleMapper.toResponse(newShedule));
    }

    @GetMapping("/all")
    public ResponseEntity<List<BarberScheduleResponse>> findListSchedule(){
        List<BarberSchedule> schedules = barberScheduleService.findAllSchedule();
        List<BarberScheduleResponse> scheduleResponses = schedules.stream()
                .map(schedule -> BarberScheduleMapper.toResponse(schedule)).toList();
        return ResponseEntity.ok().body(scheduleResponses);
    }
}

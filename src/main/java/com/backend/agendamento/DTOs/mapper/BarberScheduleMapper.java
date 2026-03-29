package com.backend.agendamento.DTOs.mapper;

import com.backend.agendamento.DTOs.request.BarberScheduleRequest;
import com.backend.agendamento.DTOs.response.BarberScheduleResponse;
import com.backend.agendamento.entity.BarberSchedule;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BarberScheduleMapper {


    public BarberSchedule toRequest(BarberScheduleRequest scheduleRequest){
        return BarberSchedule.builder()
                .barber(scheduleRequest.barber())
                .weekday(scheduleRequest.weekday())
                .startTime(scheduleRequest.startTime())
                .endTime(scheduleRequest.endTime())
                .active(scheduleRequest.active())
                .build();
    }

    public BarberScheduleResponse toResponse(BarberSchedule schedule){
        return BarberScheduleResponse.builder()
                .id(schedule.getId())
                .weekday(schedule.getWeekday())
                .active(schedule.getActive())
                .barber(schedule.getBarber())
                .startTime(schedule.getStartTime())
                .endTime(schedule.getEndTime())
                .build();
    }
}

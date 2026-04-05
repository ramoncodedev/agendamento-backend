package com.backend.agendamento.DTOs.mapper;


import com.backend.agendamento.DTOs.request.AppointmentRequest;
import com.backend.agendamento.DTOs.response.AppointmentResponse;
import com.backend.agendamento.entity.Appointment;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AppointmentMapper {

    public Appointment toRequest(AppointmentRequest appointmentRequest){
        return Appointment.builder()
                .customer(appointmentRequest.customer())
                .service(appointmentRequest.service())
                .barber(appointmentRequest.barber())
                .notes(appointmentRequest.notes())
                .status(appointmentRequest.status())
                .startAt(appointmentRequest.startAt())
                .endAt(appointmentRequest.endAt())
                .build();
    }

    public AppointmentResponse toResponse(Appointment appointment){
        return AppointmentResponse.builder()
                .id(appointment.getId())
                .customer(appointment.getCustomer())
                .service(appointment.getService())
                .startAt(appointment.getStartAt())
                .endAt(appointment.getEndAt())
                .notes(appointment.getNotes())
                .status(appointment.getStatus())
                .createdAt(appointment.getCreatedAt())
                .updateAt(appointment.getUpdatedAt())
                .build();
    }


}

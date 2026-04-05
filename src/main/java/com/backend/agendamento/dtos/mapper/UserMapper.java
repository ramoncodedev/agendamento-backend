package com.backend.agendamento.dtos.mapper;


import com.backend.agendamento.dtos.request.UserRequest;
import com.backend.agendamento.dtos.response.UserResponse;
import com.backend.agendamento.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public User toRequest(UserRequest userRequest){
        return User.builder()
                .name(userRequest.name())
                .email(userRequest.email())
                .password(userRequest.password())
                .build();
    }

    public UserResponse toResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}

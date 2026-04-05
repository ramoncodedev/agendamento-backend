package com.backend.agendamento.controller;


import com.backend.agendamento.config.TokenService;
import com.backend.agendamento.dtos.mapper.UserMapper;
import com.backend.agendamento.dtos.request.LoginRequest;
import com.backend.agendamento.dtos.request.UserRequest;
import com.backend.agendamento.dtos.response.LoginResponse;
import com.backend.agendamento.dtos.response.UserResponse;
import com.backend.agendamento.entity.User;
import com.backend.agendamento.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/auth")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/create")
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest userRequest){
        User user = UserMapper.toRequest(userRequest);
        User responseUser = userService.createUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toResponse(responseUser));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        UsernamePasswordAuthenticationToken UserAndPass = new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password());

        Authentication authentication = authenticationManager.authenticate(UserAndPass);

        User user = (User) authentication.getPrincipal();

        String token = tokenService.genereteToken(user);

        return ResponseEntity.ok().body(new LoginResponse(token));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> findAllUser(){
        List<User> users = userService.findAll();
        List<UserResponse> userResponses = users.stream().map(user -> UserMapper.toResponse(user)).toList();

        return ResponseEntity.ok().body(userResponses);
    }
}

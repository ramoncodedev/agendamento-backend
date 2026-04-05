package com.backend.agendamento.service;

import com.backend.agendamento.entity.User;
import com.backend.agendamento.exception.ConflictException;
import com.backend.agendamento.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User createUser(User user){

        String password = passwordEncoder.encode(user.getPassword());
            user.setPassword(password);


        verifyEmail(user.getEmail());

        return userRepository.save(user);
    }

    public void verifyEmail(String email){


        if (existsEmail(email)){
            throw new ConflictException("This email address is not available.");
        }
    }

    boolean existsEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }
}

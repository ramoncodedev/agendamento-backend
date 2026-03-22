package com.backend.agendamento.entity;


import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "customers")
public class Customer {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String email;

    private LocalDateTime createdAt = LocalDateTime.now();
}

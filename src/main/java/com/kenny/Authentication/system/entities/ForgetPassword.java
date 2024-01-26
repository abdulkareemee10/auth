package com.kenny.Authentication.system.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "forgetPassword_tbl")

public class ForgetPassword {
    @Id

    private String id;
    private String emailAddress;
    private String otp;
    private LocalDateTime timeCreated;
    private LocalDateTime timeExpired;
    private Long userId;

}

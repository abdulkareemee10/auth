package com.kenny.Authentication.system.repository;

import com.kenny.Authentication.system.entities.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, Long> {

Otp findByEmail(String email);
}

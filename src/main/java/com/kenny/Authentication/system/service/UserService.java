package com.kenny.Authentication.system.service;

import com.kenny.Authentication.system.dto.ForgotPasswordRequest;
import com.kenny.Authentication.system.dto.LoginRequest;
import com.kenny.Authentication.system.dto.Request;
import com.kenny.Authentication.system.dto.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    ResponseEntity<Response> signUp(Request request);
    Response login(LoginRequest request);

    Response sendOtp();
    Response validateOtp();
    Response resetPassword();
    Response changePassword(ForgotPasswordRequest forgotPasswordRequest, String email);

}

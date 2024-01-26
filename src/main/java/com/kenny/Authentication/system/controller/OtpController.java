package com.kenny.Authentication.system.controller;

import com.kenny.Authentication.system.dto.OtpRequest;
import com.kenny.Authentication.system.dto.OtpResponse;
import com.kenny.Authentication.system.dto.OtpValidationRequest;
import com.kenny.Authentication.system.dto.Response;
import com.kenny.Authentication.system.service.OtpService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/otp")
public class OtpController {

    private final OtpService otpService;

    @PostMapping("/send")
    public Response sendOtp(@RequestBody OtpRequest otpRequest){

        return otpService.sendOtp(otpRequest);

    }

    @PostMapping("/validateOtp")
    public Response validateOtp(@RequestBody OtpValidationRequest otpValidationRequest){
        return otpService.validateOtp(otpValidationRequest);
    }
}

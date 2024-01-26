package com.kenny.Authentication.system.service;


import com.kenny.Authentication.system.Utils.AppUtils;
import com.kenny.Authentication.system.dto.*;
import com.kenny.Authentication.system.entities.Otp;
import com.kenny.Authentication.system.entities.User;
import com.kenny.Authentication.system.repository.OtpRepository;
import com.kenny.Authentication.system.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class OtpService {


    private final OtpRepository otpRepository;

    private final UserRepository userRepository;

    private final EmailService emailService;

    public Response sendOtp(OtpRequest otpRequest) {
        //generate the otp
        //send the otp
        //save the otp
        //to have just one otp saved for one email * even if multiple otp is sent to this same email, only one is saved
        Otp existingOtp = otpRepository.findByEmail(otpRequest.getEmail());
        if (existingOtp != null) {
            otpRepository.delete(existingOtp);
        }

        Optional<User> existingEmail = userRepository.findByEmail(otpRequest.getEmail());
        if (existingEmail.isPresent()) {


            String otp = AppUtils.generateOtp();
            //ideally,  an otp should not be logged because you don't want to expose an otp to the terminal


            log.info("Otp:{}", otp);
            otpRepository.save(Otp.builder().email(otpRequest.getEmail())
                    .otp(otp).expiresAt(LocalDateTime.now().plusMinutes(2)).build());

            emailService.sendEmail(EmailDetails.builder()
                    .subject("testing otp").recipient(otpRequest.getEmail())
                    .messageBody("hello , "+ existingEmail.get().getEmail() + "  shipoka has sent you an otp, " +
                            "it takes only two minutes" +
                            " before it expires  " + otp).build());
            return Response.builder().responseMessage("SUCCESS").build();

        } return Response.builder().responseMessage("you need to enter the email " +
                "you used to signup " +
                "before you can have an otp ").build();
    }

    //ascertain that the user actually sent an otp
    //ascertain that the otp has not expired
    //ascertain that the otp is correct
    //check if the email has an otp

    public Response validateOtp(OtpValidationRequest otpValidationRequest) {


        Otp otp = otpRepository.findByEmail(otpValidationRequest.getEmail());
        log.info("Email:{}", otpValidationRequest.getEmail());
        if (otp == null) {
            return Response.builder().responseMessage("you have not sent an otp").build();
        }
        if (otp.getExpiresAt().isBefore(LocalDateTime.now())) {
            return Response.builder().responseMessage("the otp has expired").build();
        }
        if (!otp.getOtp().equals(otpValidationRequest.getOtp())) {
            return Response.builder()
                    .responseMessage("invalid otp").build();
        }
        return Response.builder().responseMessage("SUCCESS")
                .otpResponse(OtpResponse.builder()
                .isOtpValid(true).build()).build();

    }
}

package com.kenny.Authentication.system.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Request {

    private String email;
    private String password;
    private String confirmPassword;
    private Boolean isTermsAndCondition;

}

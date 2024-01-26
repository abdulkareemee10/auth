package com.kenny.Authentication.system.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PersonalValidationRequest {
   private Long userId;
   private Long userCategoryId;
    private String firstName;
   private String lastName;
   private String phoneNo;
   private String imageUpload;
    private String pin;
    private String confirmPin;

}

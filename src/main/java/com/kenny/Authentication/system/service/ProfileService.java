package com.kenny.Authentication.system.service;

import com.kenny.Authentication.system.Utils.AppUtils;
import com.kenny.Authentication.system.dto.PersonalValidationRequest;
import com.kenny.Authentication.system.entities.Profile;
import com.kenny.Authentication.system.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



     public ResponseEntity<String> savePersonalPersonalProfile(PersonalValidationRequest personalValidationRequest){
        if (personalValidationRequest.getPin().equals(personalValidationRequest.getConfirmPin())){
            Profile profile = Profile.builder()
                    .userId(personalValidationRequest.getUserId())
                    .userCategoryId(personalValidationRequest.getUserCategoryId())
                    .firstName(personalValidationRequest.getFirstName())
                    .lastName(personalValidationRequest.getLastName())
                    .pin(passwordEncoder.encode(personalValidationRequest.getPin()))
                    .upload(personalValidationRequest.getImageUpload())
                    .phoneNo(personalValidationRequest.getPhoneNo())
                    .build();
            profileRepository.save(profile);
            return new ResponseEntity<>( "Pin successfully created", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Pin does not match ", HttpStatus.BAD_REQUEST);
     }

     public ResponseEntity<String> save(Profile profile){
         final String ids = UUID.randomUUID().toString().replace("-", "");
         profile.setId(ids);
         profileRepository.save(profile);
         return new ResponseEntity<>( "new pin successfully created", HttpStatus.CREATED);
     }

    public void changePhoneNumber(Profile profile, String id) {
         Profile existingProfile = profileRepository.findById(id).orElseThrow(()
                -> new DataRetrievalFailureException("not found"));

        existingProfile.setPhoneNo(profile.getPhoneNo());
        profileRepository.save(existingProfile);

    }

  }

package com.kenny.Authentication.system.controller;

import com.kenny.Authentication.system.dto.PersonalValidationRequest;
import com.kenny.Authentication.system.entities.Profile;
import com.kenny.Authentication.system.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/prof")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

//        @PostMapping("/save")
//    public ResponseEntity<String> saveProfile(@RequestBody PersonalValidationRequest personalValidationRequest){
//        profileService.savePersonalPersonalProfile(personalValidationRequest);
//        return new ResponseEntity<>("saved", HttpStatus.OK);
//
//    }
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Profile profile) {
        profileService.save(profile);
        return new ResponseEntity<>("saved", HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public void changeNumber(@PathVariable("id") String id, @RequestBody Profile profile){
        try

        {
             profileService.changePhoneNumber(profile, id);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
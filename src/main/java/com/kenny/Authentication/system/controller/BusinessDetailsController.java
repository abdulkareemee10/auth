package com.kenny.Authentication.system.controller;


import com.kenny.Authentication.system.entities.BusinessDetails;
import com.kenny.Authentication.system.service.BusinessDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/businessDetails")
public class BusinessDetailsController {

    @Autowired
    private BusinessDetailsService businessDetailsService;

    @PostMapping("/post")
    public String saveBusinessDetails(@RequestBody BusinessDetails businessDetails){

        return  businessDetailsService.saveBusinessDetails(businessDetails);
    }
}

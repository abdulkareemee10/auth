package com.kenny.Authentication.system.service;


import com.kenny.Authentication.system.entities.BusinessDetails;
import com.kenny.Authentication.system.entities.BusinessType;
import com.kenny.Authentication.system.repository.BusinessDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessDetailsService {

    @Autowired
    private BusinessDetailsRepository businessDetailsRepository;

    public String saveBusinessDetails(BusinessDetails businessDetails){
        businessDetailsRepository.save(businessDetails);
        return "saved";
    }

}

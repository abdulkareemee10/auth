package com.kenny.Authentication.system.service;

import com.kenny.Authentication.system.entities.BusinessType;
import com.kenny.Authentication.system.repository.BusinessTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessTypeService {

    @Autowired
    private BusinessTypeRepository businessTypeRepository;

    public ResponseEntity<String> saveBusinessType(BusinessType businessType){
        businessTypeRepository.save(businessType);
        return new ResponseEntity<>("saved", HttpStatus.CREATED);
    }

    public List<BusinessType> readBusinessType(){
        List<BusinessType> businessTypes = businessTypeRepository.findAll() ;
        return  businessTypes;
    }

    public BusinessType updateBusinessType(BusinessType businessType, Long id) {
        BusinessType existingBusinessType = businessTypeRepository.findById(id).orElseThrow(()
                -> new DataRetrievalFailureException("Business Category Not Found"));

        existingBusinessType.setBusinessName(businessType.getBusinessName());


        businessTypeRepository.save(existingBusinessType);
        return existingBusinessType;
    }

    public void deleteBusinessById(Long id) {
        BusinessType businessType = businessTypeRepository.findById(id).orElseThrow(()
                -> new DataRetrievalFailureException("Business Category Not Found"));
        businessTypeRepository.deleteById(id);
    }
}

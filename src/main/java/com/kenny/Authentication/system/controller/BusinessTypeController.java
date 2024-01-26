package com.kenny.Authentication.system.controller;

import com.kenny.Authentication.system.entities.BusinessType;
import com.kenny.Authentication.system.service.BusinessTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/business")
public class BusinessTypeController {
    @Autowired
    private BusinessTypeService businessTypeService;

    @PostMapping("/post")
    public ResponseEntity<String> saveBusinessType(@RequestBody BusinessType businessType){

        return  businessTypeService.saveBusinessType(businessType);
    }
    @GetMapping("/get")
    public ResponseEntity<List<BusinessType>> getAllBusiness() {
        try {
            return new ResponseEntity<> (businessTypeService.readBusinessType(), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BusinessType> updateBusinessType(@PathVariable("id") Long id, @RequestBody BusinessType businessType){
        try

        {
            return new ResponseEntity<BusinessType>(businessTypeService.updateBusinessType(businessType, id), HttpStatus.CREATED);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>( businessType, HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deleteBusinessType(@PathVariable("id") Long id){
        businessTypeService.deleteBusinessById(id);
        return new ResponseEntity<>("business type deleted", HttpStatus.OK);
    }

}

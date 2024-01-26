package com.kenny.Authentication.system.service;


import com.kenny.Authentication.system.entities.UserCategory;
import com.kenny.Authentication.system.repository.UserCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCategoryService {

    @Autowired
    private UserCategoryRepository userCategoryRepository;

    public String addCategory(UserCategory userCategory){
;


                userCategoryRepository.save(userCategory);
                return "saved";
    }

    public ResponseEntity<List<UserCategory>> getAllCategories() {
        List<UserCategory> userCategory = userCategoryRepository.findAll();
        return new ResponseEntity<>(userCategory, HttpStatus.OK);

    }
}

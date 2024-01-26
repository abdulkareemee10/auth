package com.kenny.Authentication.system.controller;

import com.kenny.Authentication.system.entities.UserCategory;
import com.kenny.Authentication.system.service.UserCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/usercategory")
public class UserCategoryController {

    @Autowired
    private UserCategoryService userCategoryService;

@PostMapping("/add")
 public String addCategory(@RequestBody UserCategory userCategory){
   userCategoryService.addCategory(userCategory);
   return "saved";
}

@GetMapping("/get")
    public ResponseEntity<List<UserCategory>> getCategory(){
     return userCategoryService.getAllCategories();

    }


}

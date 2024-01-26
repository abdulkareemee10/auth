package com.kenny.Authentication.system.controller;

import com.kenny.Authentication.system.entities.EmployeeSize;
import com.kenny.Authentication.system.service.EmployeeSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeeSizeController {

    @Autowired
    private EmployeeSizeService employeeSizeService;

    @PostMapping("/post")
    public ResponseEntity<String> saveEmployeeSize(@RequestBody EmployeeSize employeeSize){

        return  employeeSizeService.saveEmployeeSize(employeeSize);
    }
    @GetMapping("/get")
    public ResponseEntity<List<EmployeeSize>> getAllEmployeeSize() {
        try {
            return new ResponseEntity<> (employeeSizeService.readEmployeeType(), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeSize> updateEmployeeSize(@PathVariable("id") Long id, @RequestBody EmployeeSize employeeSize){
        try

        {
            return employeeSizeService.updateEmployeeSize(employeeSize, id);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>( employeeSize, HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deleteEmployeeSize(@PathVariable("id") Long id){
        employeeSizeService.deleteEmployeeById(id);
        return new ResponseEntity<>("employee size deleted", HttpStatus.OK);
    }
}

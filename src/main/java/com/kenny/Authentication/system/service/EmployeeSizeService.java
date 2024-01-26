package com.kenny.Authentication.system.service;

import com.kenny.Authentication.system.entities.BusinessType;
import com.kenny.Authentication.system.entities.EmployeeSize;
import com.kenny.Authentication.system.repository.BusinessTypeRepository;
import com.kenny.Authentication.system.repository.EmployeeSizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeSizeService {

    @Autowired
    private EmployeeSizeRepository employeeSizeRepository;

    public ResponseEntity<String> saveEmployeeSize(EmployeeSize employeeSize){
        employeeSizeRepository.save(employeeSize);
        return new ResponseEntity<>("saved", HttpStatus.CREATED);
    }

    public List<EmployeeSize> readEmployeeType(){
        List<EmployeeSize> employeeSizes = employeeSizeRepository.findAll() ;
        return employeeSizes;
    }

    public ResponseEntity<EmployeeSize> updateEmployeeSize(EmployeeSize employeeSize, Long id) {
        EmployeeSize existingEmployeeSize = employeeSizeRepository.findById(id).orElseThrow(()
                -> new DataRetrievalFailureException("Employee Size Not Found"));

        existingEmployeeSize.setEmployeeSize(employeeSize.getEmployeeSize());


        employeeSizeRepository.save(existingEmployeeSize);
        return new ResponseEntity<>(existingEmployeeSize, HttpStatus.OK);
    }

    public void deleteEmployeeById(Long id) {
        EmployeeSize employeeSize = employeeSizeRepository.findById(id).orElseThrow(()
                -> new DataRetrievalFailureException("employee size Not Found"));
        employeeSizeRepository.deleteById(id);
    }
}

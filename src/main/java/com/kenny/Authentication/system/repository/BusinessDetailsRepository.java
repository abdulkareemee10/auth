package com.kenny.Authentication.system.repository;

import com.kenny.Authentication.system.entities.BusinessDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessDetailsRepository extends JpaRepository<BusinessDetails, String> {
}

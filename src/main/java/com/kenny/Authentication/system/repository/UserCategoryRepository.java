package com.kenny.Authentication.system.repository;

import com.kenny.Authentication.system.entities.UserCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCategoryRepository extends JpaRepository<UserCategory, Long> {

Optional<UserCategory> findByCategoryName(String name);
}

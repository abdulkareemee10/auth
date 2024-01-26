package com.kenny.Authentication.system.repository;

import com.kenny.Authentication.system.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, String> {
}

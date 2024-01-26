package com.kenny.Authentication.system.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity(name = "profile_tbl")
public class Profile {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    @Size(max = 4, min = 4)
    private String pin;
    private String contactEmailAddress;
    private String contactFirstName;
    private String contactLastName;
    private String phoneNo;
    private String upload;
    private Long userId;
    private Long userCategoryId;
}

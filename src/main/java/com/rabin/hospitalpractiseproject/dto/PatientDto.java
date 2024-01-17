package com.rabin.hospitalpractiseproject.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
public class PatientDto {
    private Long id;
    @NotBlank(message = "patient name should not be null or empty")
    private String name;
    private LocalDate birthDate;
    @NotBlank(message = "patient address should not be null or empty")
    private String address;
    @NotBlank(message = "patient address should not be null or empty")
    private String email;
    @NotBlank(message = "patient password should not be null or empty")
    private String password;
    private Long hospitalId;
}

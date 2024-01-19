package com.rabin.hospitalpractiseproject.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;


import java.time.LocalDate;
import java.util.Date;


@Data
@ToString
public class PatientDto {
    private Long id;
    @NotBlank(message = "patient name should not be null or empty")
    private String name;
    @NotNull(message = "patient date of birth should not be null")
//    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
//    private Date birthDate;
    @NotBlank(message = "patient address should not be null or empty")
    private String address;
    @NotBlank(message = "patient address should not be null or empty")
    private String email;
    @NotBlank(message = "patient password should not be null or empty")
    private String password;
    private Long hospitalId;
}

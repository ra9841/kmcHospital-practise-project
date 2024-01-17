package com.rabin.hospitalpractiseproject.dto;

import lombok.Data;

import lombok.ToString;

@Data
@ToString
public class DoctorDto {
    private Long id;

    private String doctorName;
    private String dept;
    private String doctorEPNumber;
    private String doctorEmail;
    private String password;
    private String address;
}

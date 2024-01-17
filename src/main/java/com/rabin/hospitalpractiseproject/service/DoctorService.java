package com.rabin.hospitalpractiseproject.service;

import com.rabin.hospitalpractiseproject.dto.DoctorDto;

import java.util.List;
import java.util.Map;

public interface DoctorService {
    DoctorDto savingDoctorInfo(DoctorDto doctorDto);

    List<DoctorDto> getAllDoctorRecord();

    DoctorDto updatingDoctorInformationFromDataBase(DoctorDto doctorDto, String doctorEPNumber);


}

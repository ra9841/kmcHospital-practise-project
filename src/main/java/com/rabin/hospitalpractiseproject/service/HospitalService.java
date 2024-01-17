package com.rabin.hospitalpractiseproject.service;

import com.rabin.hospitalpractiseproject.dto.HospitalDto;

import java.util.List;

public interface HospitalService {
    HospitalDto savingHospitalInformationInDataBase(HospitalDto hospitalDto);

    List<HospitalDto> listOfHospitalInfo();

    HospitalDto updatingHospitalinformationInDataBase(String name, HospitalDto hospitalDto);

    String deletingRecordFromDataBase(String name);
}

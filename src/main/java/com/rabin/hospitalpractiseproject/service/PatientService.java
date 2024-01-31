package com.rabin.hospitalpractiseproject.service;

import com.rabin.hospitalpractiseproject.dto.PatientDto;

import java.util.List;

public interface PatientService {
    PatientDto savingPatentRecordIntoDataBase(PatientDto patientDto);

    List<PatientDto> gettingAllListOfRecord();

    PatientDto editingThePatientRecord(PatientDto patientDto, String email);

    List<PatientDto> gettingAllListOfRecordOnPagingBasis(int page, int limit);

    List<PatientDto> gettingAllListOfRecordOnPagingBasisNextVersion(int pageSize, int pageNumber);
}

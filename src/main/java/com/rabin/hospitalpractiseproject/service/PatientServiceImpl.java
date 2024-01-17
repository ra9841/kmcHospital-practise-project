package com.rabin.hospitalpractiseproject.service;

import com.rabin.hospitalpractiseproject.dto.PatientDto;
import com.rabin.hospitalpractiseproject.entity.Patient;
import com.rabin.hospitalpractiseproject.exception.InternalServerError;
import com.rabin.hospitalpractiseproject.exception.NullEmailNotAllowException;
import com.rabin.hospitalpractiseproject.exception.PatientAlreadyExistException;
import com.rabin.hospitalpractiseproject.exception.SingleMethodForAllException;
import com.rabin.hospitalpractiseproject.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public PatientDto savingPatentRecordIntoDataBase(PatientDto patientDto) {
        try {

            Optional<Patient> existingPatient = patientRepository.findByEmail(patientDto.getEmail().toLowerCase());
            if (existingPatient.isPresent()) {
                throw new PatientAlreadyExistException("Patient with this email already exists in the records");
            }
//            if(patientDto.getEmail()==null || patientDto.getEmail().isEmpty()){
//                throw new NullEmailNotAllowException("Patient email is null or empty so it is not allow");
//            }

            Patient patient = new Patient();

            patient.setEmail(patientDto.getEmail().toLowerCase());
            patient.setName(patientDto.getName().toLowerCase());
            patient.setAddress(patientDto.getAddress());
            patient.setPassword(passwordEncoder.encode(patientDto.getPassword()));
            patient.setBirthDate(patientDto.getBirthDate());
            patient.setCreateDate(LocalDateTime.now());
            patient.setModifiedDate(LocalDateTime.now());

            Patient savedPatient = patientRepository.save(patient);
            log.info("Patient record save in database: {}", patient);
            PatientDto savedPatientDto = new PatientDto();
            BeanUtils.copyProperties(savedPatient, savedPatientDto);
            log.info("Patient data from database to passing to controller by dto: {}", savedPatientDto);
            return savedPatientDto;
        } catch (Exception exception) {
            log.error("Internal server error while processing registration: {}", exception.getMessage());
            throw new SingleMethodForAllException(exception.getMessage());
        }
    }

}

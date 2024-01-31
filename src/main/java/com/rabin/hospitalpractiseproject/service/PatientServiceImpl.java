package com.rabin.hospitalpractiseproject.service;


import com.rabin.hospitalpractiseproject.annotation.LogPayloads;
import com.rabin.hospitalpractiseproject.annotation.TrackExecutionTime;
import com.rabin.hospitalpractiseproject.dto.PatientDto;
import com.rabin.hospitalpractiseproject.entity.Patient;

import com.rabin.hospitalpractiseproject.exception.PatientAlreadyExistException;
import com.rabin.hospitalpractiseproject.exception.SingleMethodForAllException;
import com.rabin.hospitalpractiseproject.repository.HospitalRepository;
import com.rabin.hospitalpractiseproject.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private HospitalRepository hospitalRepository;


    //joinpoint
    //pointcut (com.rabin.hospitalpractiseproject.service.savingPatentRecordIntoDataBase.*())
    @Override
    //Before Advice
    @TrackExecutionTime
    @LogPayloads
    public PatientDto savingPatentRecordIntoDataBase(PatientDto patientDto) {
        //(--AOP--)
        //TransactionAspect
        //loggingAspect
        //validationAspect
        //auditingAspect
        //notificationAspect

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


    //After Advice -> consider Exception
    //After returning Advice -> No Exception
    //After throwing advice -> if any exception occurs
    //around advice -> Before + After returning


    @Override
    @TrackExecutionTime
    public List<PatientDto> gettingAllListOfRecord() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(patient -> {
            PatientDto patientDto = new PatientDto();
            BeanUtils.copyProperties(patient, patientDto);
            return patientDto;
        }).toList();
    }

    @Override
    @TrackExecutionTime
    public PatientDto editingThePatientRecord(PatientDto patientDto, String email) {
        Optional<Patient> existPatient = patientRepository.findByEmail(email);
        if (existPatient.isPresent()) {
            Patient patient = existPatient.get();
            patient.setAddress(patientDto.getAddress());
            patient.setName(patientDto.getName());
            patient.setBirthDate(patientDto.getBirthDate());
            patient.setEmail(patientDto.getEmail());
            patient.setPassword(passwordEncoder.encode(patientDto.getPassword()));
            patient.setCreateDate(LocalDateTime.now());
            patient.setModifiedDate(LocalDateTime.now());
            log.info("Patient record save in database {}", patient);
            Patient patient1 = patientRepository.save(patient);
            PatientDto patientDto1 = new PatientDto();
            BeanUtils.copyProperties(patient1, patientDto1);
            log.info("Patient record sending from database to controller {}", patientDto1);
            return patientDto1;
        }
        throw new PatientAlreadyExistException("Patient with this email already exists in the records");
    }

    @Override
    @TrackExecutionTime
    public List<PatientDto> gettingAllListOfRecordOnPagingBasis(int page, int limit) {
        Pageable pageRequest = PageRequest.of(page - 1, limit); // Adjusting page to 0-based index

        Page<Patient> patientPage = patientRepository.findAll(pageRequest);
        List<Patient> patients = patientPage.getContent();

        return patients.stream().map(patient -> {
            PatientDto patientDto = new PatientDto();
            BeanUtils.copyProperties(patient, patientDto);
            return patientDto;
        }).toList();
    }

    @Override
    @TrackExecutionTime
    public List<PatientDto> gettingAllListOfRecordOnPagingBasisNextVersion(int pageSize, int pageNumber) {
        Page<Patient> patients = patientRepository.findAll(PageRequest.of(pageNumber, pageSize));
        return patients.stream().map(patient -> {
            PatientDto patientDto = new PatientDto();
            BeanUtils.copyProperties(patient, patientDto);
            log.info("List of Record {}", patientDto);
            return patientDto;
        }).toList();

    }


}

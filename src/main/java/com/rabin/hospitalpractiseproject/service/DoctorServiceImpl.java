package com.rabin.hospitalpractiseproject.service;

import com.rabin.hospitalpractiseproject.dto.DoctorDto;
import com.rabin.hospitalpractiseproject.entity.Doctor;
import com.rabin.hospitalpractiseproject.exception.DoctorEPNumberAlreadyExistException;
import com.rabin.hospitalpractiseproject.exception.DoctorEmailAlreadyExistException;
import com.rabin.hospitalpractiseproject.exception.DoctorEpNumberDoesNotMatch;
import com.rabin.hospitalpractiseproject.exception.InternalServerError;
import com.rabin.hospitalpractiseproject.repository.DoctorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public DoctorDto savingDoctorInfo(DoctorDto doctorDto) {
        try {
            Doctor doctor = new Doctor();
            BeanUtils.copyProperties(doctorDto, doctor);
            Optional<Doctor> existDoctor = doctorRepository.findByDoctorEPNumber(doctor.getDoctorEPNumber());
            if (existDoctor.isPresent()) {
                throw new DoctorEPNumberAlreadyExistException("Doctor EP Number is already present");
            }

            Optional<Doctor> existDoctorEmail = doctorRepository.findByDoctorEmail(doctor.getDoctorEmail());
            if (existDoctorEmail.isPresent()) {
                throw new DoctorEmailAlreadyExistException("Doctor email is already present");
            }
            // Convert name,address to lowercase for case-insensitive check
            String lowerCaseName = doctor.getDoctorName().toLowerCase();
            String lowerCaseAddress = doctor.getAddress().toLowerCase();
            String lowerCaseEmail = doctor.getDoctorEmail().toLowerCase();

            doctor.setDoctorName(lowerCaseName);
            doctor.setAddress(lowerCaseAddress);
            doctor.setDoctorEmail(lowerCaseEmail);
            doctor.setCreateDate(LocalDateTime.now());
            doctor.setModifiedDate(LocalDateTime.now());
            doctor.setPassword(passwordEncoder.encode(doctorDto.getPassword()));
            Doctor doctor1 = doctorRepository.save(doctor);
            log.info("Registration successful for email: {}", doctor1);
            DoctorDto doctorDto1 = new DoctorDto();
            BeanUtils.copyProperties(doctor1, doctorDto1);
            return doctorDto1;
        } catch (DoctorEmailAlreadyExistException | DoctorEPNumberAlreadyExistException e) {
            log.error("Invalid doctor information {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Internal server error while processing registration", e);
            throw new InternalServerError("Internal server error");
        }
    }

    @Override
    public List<DoctorDto> getAllDoctorRecord() {
        try {
            List<Doctor> doctors = doctorRepository.findAll();
            return doctors.stream().map(doctor -> {
                DoctorDto doctorDto = new DoctorDto();
                BeanUtils.copyProperties(doctor, doctorDto);
                return doctorDto;
            }).toList();
        } catch (Exception e) {
            log.error("Internal server error while processing registration", e);
            throw new InternalServerError("Internal server error");
        }
    }

    @Override
    public DoctorDto updatingDoctorInformationFromDataBase(DoctorDto doctorDto, String doctorEPNumber) {
        try {
            Optional<Doctor> existDoctor = doctorRepository.findByDoctorEPNumber(doctorEPNumber);
            if (existDoctor.isPresent()) {
                Doctor doctor = existDoctor.get();
                doctor.setDoctorName(doctorDto.getDoctorName().toLowerCase());
                doctor.setDoctorEmail(doctorDto.getDoctorEmail().toLowerCase());
                doctor.setAddress(doctorDto.getAddress().toLowerCase());
                doctor.setDept(doctorDto.getDept());
                doctor.setPassword(passwordEncoder.encode(doctorDto.getPassword()));
                Doctor doctor1 = doctorRepository.save(doctor);
                DoctorDto doctorDto1 = new DoctorDto();
                BeanUtils.copyProperties(doctor1, doctorDto1);
                return doctorDto1;
            }
            throw new DoctorEpNumberDoesNotMatch("Doctor Ep Number does not match");
        } catch (DoctorEpNumberDoesNotMatch e) {
            log.error("Invalid doctor information {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Internal server error while processing registration", e);
            throw new InternalServerError("Internal server error");
        }
    }


}

package com.rabin.hospitalpractiseproject.service;

import com.rabin.hospitalpractiseproject.dto.HospitalDto;
import com.rabin.hospitalpractiseproject.entity.Hospital;
import com.rabin.hospitalpractiseproject.exception.HospitalNameNotFoundException;
import com.rabin.hospitalpractiseproject.exception.InternalServerError;
import com.rabin.hospitalpractiseproject.exception.hosptalNameIsAlreadyPresent;
import com.rabin.hospitalpractiseproject.repository.HospitalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Override
    public HospitalDto savingHospitalInformationInDataBase(HospitalDto hospitalDto) {
        try {
            // Check if hospital with the same name already exists
            Optional<Hospital> existHospital = hospitalRepository.findByName(hospitalDto.getName());
            if (existHospital.isPresent()) {
                throw new hosptalNameIsAlreadyPresent("Hospital name is already present in record");
            }

            // Create a new Hospital entity and set its properties
            Hospital hospital = new Hospital();
            BeanUtils.copyProperties(hospitalDto, hospital);
            hospital.setName(hospitalDto.getName().toUpperCase());
            hospital.setCreateDate(LocalDateTime.now());
            hospital.setModifiedDate(LocalDateTime.now());

            // Save the hospital entity to the database
            Hospital savedHospital = hospitalRepository.save(hospital);

            // Convert the saved entity back to DTO and return
            HospitalDto savedHospitalDto = new HospitalDto();
            BeanUtils.copyProperties(savedHospital, savedHospitalDto);
            return savedHospitalDto;
        } catch (hosptalNameIsAlreadyPresent e) {
            // Handle specific exception for duplicate hospital name
            log.error("Hospital name is already present in record", e);
            throw e; // or handle it in some specific way
        } catch (Exception e) {
            // Handle other exceptions
            log.error("Internal server error while processing registration", e);
            throw new InternalServerError("Internal server error");
        }
    }


    @Override
    public List<HospitalDto> listOfHospitalInfo() {
        try {
            List<Hospital> hospitals = hospitalRepository.findAll();
            return hospitals.stream().map(hospital -> {
                HospitalDto hospitalDto = new HospitalDto();
                BeanUtils.copyProperties(hospital, hospitalDto);
                log.info("data from database {}" , hospitalDto);
                return hospitalDto;
            }).toList();
        } catch (Exception e) {
            log.error("Internal server error while processing registration", e);
            throw new InternalServerError("Internal server error");
        }
    }

    @Override
    public HospitalDto updatingHospitalinformationInDataBase(String name, HospitalDto hospitalDto) {
        try {
            Optional<Hospital> existHospital = hospitalRepository.findByName(name);
            if (existHospital.isPresent()) {
                Hospital hospital = existHospital.get();
                hospital.setName(hospitalDto.getName().toUpperCase());
                hospital.setModifiedDate(LocalDateTime.now());
                Hospital hospital1 = hospitalRepository.save(hospital);
                HospitalDto hospitalDto1 = new HospitalDto();
                BeanUtils.copyProperties(hospital1, hospitalDto1);

                return hospitalDto1;
            } else {
                throw new HospitalNameNotFoundException("Hospital name is not in record");
            }
        } catch (HospitalNameNotFoundException e) {
            // Handle specific exception for duplicate hospital name
            log.error("Hospital name not found in record", e);
            throw e; // or handle it in some specific way

        } catch (Exception e) {
            log.error("Internal server error while processing registration", e);
            throw new InternalServerError("Internal server error");
        }
    }

    @Override
    public String deletingRecordFromDataBase(String name) {
        try {
            Optional<Hospital> existHospital = hospitalRepository.findByName(name);
            if (existHospital.isPresent()) {
                Hospital hospital = existHospital.get();
                hospitalRepository.delete(hospital);
                return "deleted successfully";
            } else {
                throw new HospitalNameNotFoundException("Hospital name is not in record");
            }
        } catch (HospitalNameNotFoundException e) {
            // Handle specific exception for duplicate hospital name
            log.error("Hospital name not found in record", e);
            throw e; // or handle it in some specific way
        } catch (Exception e) {
            log.error("Internal server error while processing registration", e);
            throw new InternalServerError("Internal server error");
        }
    }


}

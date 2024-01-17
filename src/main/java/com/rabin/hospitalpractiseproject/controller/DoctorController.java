package com.rabin.hospitalpractiseproject.controller;

import com.rabin.hospitalpractiseproject.dto.DoctorDto;
import com.rabin.hospitalpractiseproject.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//https://github.com/cartrrz/Backend-Hospital.git
@RestController
@RequestMapping("doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping
    public ResponseEntity<DoctorDto> registeringDoctorInfo(@RequestBody DoctorDto doctorDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.savingDoctorInfo(doctorDto));
    }

    @GetMapping
    public ResponseEntity<List<DoctorDto>> gettingListOfDoctorRecord() {
        return ResponseEntity.ok(doctorService.getAllDoctorRecord());
    }

    @PutMapping("/{doctorEPNumber}")
    public ResponseEntity<DoctorDto> updatingDoctorInfo(@RequestBody DoctorDto doctorDto,@PathVariable String doctorEPNumber){
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.updatingDoctorInformationFromDataBase(doctorDto,doctorEPNumber));
    }



}

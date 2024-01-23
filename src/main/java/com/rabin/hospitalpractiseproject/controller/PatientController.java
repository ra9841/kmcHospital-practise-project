package com.rabin.hospitalpractiseproject.controller;

import com.rabin.hospitalpractiseproject.dto.PatientDto;
import com.rabin.hospitalpractiseproject.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping
    public ResponseEntity<PatientDto> registeringPatientInfo(@RequestBody @Valid PatientDto patientDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.savingPatentRecordIntoDataBase(patientDto));
    }

    @GetMapping
    public List<PatientDto> gettingAllRecordOfPatient(){
        return patientService.gettingAllListOfRecord();
    }

}

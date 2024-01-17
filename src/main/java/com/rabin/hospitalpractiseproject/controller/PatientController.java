package com.rabin.hospitalpractiseproject.controller;

import com.rabin.hospitalpractiseproject.dto.PatientDto;
import com.rabin.hospitalpractiseproject.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping
    public PatientDto registeringPatientInfo(@RequestBody @Valid PatientDto patientDto){
        return patientService.savingPatentRecordIntoDataBase(patientDto);
    }

}

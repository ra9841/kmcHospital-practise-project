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
    public ResponseEntity<List<PatientDto>> ListOfRecordOfPatient(){
        return ResponseEntity.status(HttpStatus.OK).body(patientService.gettingAllListOfRecord());
    }

    @PutMapping("/{email}")
    public PatientDto updatingThePatientRecord(@RequestBody PatientDto patientDto, @PathVariable String email){
        return patientService.editingThePatientRecord( patientDto,email);

    }

    @GetMapping("/shows")//localhost:8080/patients/shows?page=1&limit=3
    public ResponseEntity<List<PatientDto>> ListOfRecordOfPatientOnPagingBasis(@RequestParam(value="page", defaultValue ="0")int page,
                                                                               @RequestParam(value = "limit", defaultValue = "2")int limit){
        return ResponseEntity.status(HttpStatus.OK).body(patientService.gettingAllListOfRecordOnPagingBasis(page,limit));
    }

    @GetMapping("/shows/v")//localhost:8080/patients/shows/v?pageSize=4&pageNo=1
    public ResponseEntity<List<PatientDto>> ListOfRecordOfPatientOnPagingBasisNextVersion(@RequestParam("pageSize")int pageSize,
                                                                               @RequestParam("pageNo")int pageNumber){
        return ResponseEntity.status(HttpStatus.OK).body(patientService.gettingAllListOfRecordOnPagingBasisNextVersion(pageSize,pageNumber));
    }

    @GetMapping("/ascending")//localhost:8080/patients/ascending?pageSize=4&pageNo=0
    public ResponseEntity<List<PatientDto>> ListOfRecordOfPatientOnPagingOnAscendingOrder(@RequestParam("pageSize")int pageSize,
                                                                                          @RequestParam("pageNo")int pageNumber){
        return ResponseEntity.status(HttpStatus.OK).body(patientService.gettingAllListOfRecordOnPagingOnAscendingOrder(pageSize,pageNumber));
    }

    @GetMapping("/descending")//localhost:8080/patients/descending?pageSize=4&pageNo=0
    public ResponseEntity<List<PatientDto>> ListOfRecordOfPatientOnPagingOnDescendingOrder(@RequestParam("pageSize")int pageSize,
                                                                                          @RequestParam("pageNo")int pageNumber){
        return ResponseEntity.status(HttpStatus.OK).body(patientService.gettingAllListOfRecordOnPagingOnDescendingOrder(pageSize,pageNumber));
    }


}

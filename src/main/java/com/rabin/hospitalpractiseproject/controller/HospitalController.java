package com.rabin.hospitalpractiseproject.controller;

import com.rabin.hospitalpractiseproject.dto.HospitalDto;
import com.rabin.hospitalpractiseproject.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospitals")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @PostMapping
    public ResponseEntity<HospitalDto> registeringHospitalInformation(@RequestBody HospitalDto hospitalDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hospitalService.savingHospitalInformationInDataBase(hospitalDto));
    }

    @GetMapping
    public ResponseEntity<List<HospitalDto>> gettingAllHospitalInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(hospitalService.listOfHospitalInfo());
    }

//    localhost:8080/hospitals?name=Thimi Hospital
    @PutMapping
    public ResponseEntity<HospitalDto> updatingHospitalInfo(@RequestParam("name") String name, @RequestBody HospitalDto hospitalDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hospitalService.updatingHospitalinformationInDataBase(name, hospitalDto));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<String> deletingInfo(@PathVariable String name) {
        return ResponseEntity.status(HttpStatus.OK).body(hospitalService.deletingRecordFromDataBase(name));
    }
}

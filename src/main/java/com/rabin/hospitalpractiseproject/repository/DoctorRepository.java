package com.rabin.hospitalpractiseproject.repository;

import com.rabin.hospitalpractiseproject.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {

    Optional<Doctor> findByDoctorEPNumber(String doctorEPNumber);

    Optional<Doctor> findByDoctorEmail(String doctorEmail);
}

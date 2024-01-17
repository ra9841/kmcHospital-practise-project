package com.rabin.hospitalpractiseproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@Entity
@Table(name = "doctor_tbl")
public class Doctor extends BaseEntity {

    @Column(name = "doctor_name", nullable = false)
    private String doctorName;
    @Column(name = "doctor_dept", nullable = false)
    private String dept;
    @Column(name = "doctor_epNumber", nullable = false)
    private String doctorEPNumber;
    @Column(name = "doctor_address", nullable = false)
    private String address;
    @Column(name = "doctor_email", nullable = false)
    private String doctorEmail;
    @Column(name = "doctor_password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<VisitNote> visitNotes;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JsonIgnore
    @JoinTable(
            name = "doctor_speciality",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "speciality_id")
    )
    private Set<Speciality> specialities;

}

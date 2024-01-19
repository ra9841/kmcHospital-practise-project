package com.rabin.hospitalpractiseproject.entity;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


import java.time.LocalDate;
import java.util.Date;
import java.util.Set;
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@Entity
@Table(name = "patient_tbl")
public class Patient extends BaseEntity {
    @Column(name = "patient_name")
    private String name;
    @Column(name = "patient_dob", nullable = false)
    private LocalDate birthDate;
//    private Date birthDate;
    @Column(name = "patient_address")
    private String address;
    @Column(name = "patient_email", nullable = false)
    private String email;
    @Column(name = "patient_password")
    private String password;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<VisitNote> visitNotes;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;
}

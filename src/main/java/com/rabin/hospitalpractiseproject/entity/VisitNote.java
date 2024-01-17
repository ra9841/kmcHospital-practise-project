package com.rabin.hospitalpractiseproject.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.print.Doc;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@Entity
@Table(name = "visitNote_tbl")
public class VisitNote extends BaseEntity {
    @Column(name = "visit_description", nullable = false)
    private String description;
    @Column(name = "visit_date", nullable = false)
    private LocalDate dateVisit;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
}

package com.rabin.hospitalpractiseproject.dto;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
public class VisitNoteDto {
    private Long id;
    private LocalDate visitDate;
    private String description;
    private Long patientId;
    private Long doctorId;
}

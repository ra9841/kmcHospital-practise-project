package com.rabin.hospitalpractiseproject.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SpecialtyDto {
    private Long id;
    private String name;
    private String description;
}

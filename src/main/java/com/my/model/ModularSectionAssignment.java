package com.my.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModularSectionAssignment {
    private ModularSection modularSection;
    private Section section;
    private String assigner;
    private LocalDateTime assignedDateTime;
}

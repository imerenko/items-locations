package com.my.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModularSectionAssignment {
    private ModularSection modularSection;
    private Section section;
    private String assigner;
}

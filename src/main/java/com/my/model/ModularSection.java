package com.my.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModularSection {
    private long id;
    private int sectionNumber;
    private Modular modular;
    private List<ModularSectionItem> modularSectionItems;
    private List<ModularSectionAssignment> modularSectionAssignments;

    public ModularSection(long id, int sectionNumber, Modular modular) {
        this.id = id;
        this.sectionNumber = sectionNumber;
        this.modular = modular;
    }

    public ModularSection(long id) {
        this.id = id;
    }
}

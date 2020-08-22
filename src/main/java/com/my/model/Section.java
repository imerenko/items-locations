package com.my.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Section {

    private String sgln;
    private String sgln195;
    private String parentSgln;
    private String name;
    private Store store;
    private String legacyId;
    private List<ModularSectionAssignment> modularSectionAssignments;
    private List<Flex> flexes;
    private List<Item> items;


    public Section(String sgln) {
        this.sgln = sgln;
    }

    public Section(String sgln, String parentSgln, String name, Store store) {
        this.sgln = sgln;
        this.parentSgln = parentSgln;
        this.name = name;
        this.store = store;
    }

}

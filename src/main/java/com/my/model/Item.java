package com.my.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private String gtin;
    private List<Flex> flexes;
    //private List<ModularSectionAssignment> modularSectionAssignments;
   // private List<Section> sections;
    private List<ModularSectionItem> modularSectionItems;
    private Store store;

    public Item(String gtin) {
        this.gtin = gtin;
    }
}

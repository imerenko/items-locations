package com.my.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModularSectionItem {
    private int sequenceNumber;
    private String gtin;
    private ModularSection modularSection;
}

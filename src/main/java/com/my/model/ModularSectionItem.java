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
    private int capacityCnt;
    private int horizFacingCnt;
    private int vertFacingCnt;
    private int merchandiseStyleCode;

    public ModularSectionItem(int sequenceNumber, String gtin,  ModularSection modularSection) {
        this.setSequenceNumber(sequenceNumber);
        this.setGtin(gtin);
        this.setModularSection(modularSection);
    }
}

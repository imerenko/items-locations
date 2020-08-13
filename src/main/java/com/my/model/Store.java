package com.my.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Store {

    private String gln;
    private String countryCode;
    private int storeNumber;

    public Store (String gln) {
        this.gln = gln;
    }

    public Store (String countryCode, int storeNumber) {
        this.countryCode = countryCode;
        this.storeNumber = storeNumber;
    }
}

package com.my.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flex {
    private String gtin;
    private Section section;
    private String assigner;
}

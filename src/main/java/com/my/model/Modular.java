package com.my.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Modular {

    private long id;
    private long planId;
    private Store store;
    private String modularName;
    private List<ModularSection> modularSections;

    public Modular(long id) {
        this.id = id;
    }

    public Modular(long id, long planId, Store store, String modularName ) {
        this.id = id;
        this.planId = planId;
        this.store = store;
        this.modularName = modularName;
    }


}

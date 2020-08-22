package com.my.resolver.inmemory;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.my.model.Modular;
import com.my.repository.inmemory.ModularInMemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class ModularQueryInMemoryResolver implements GraphQLQueryResolver {

    @Autowired
    private ModularInMemoryRepository modularInMemoryRepository;

    public List<Modular> getModulars() {
        return modularInMemoryRepository.getModulars();
    }

    public Modular getModular(String countryCode, int storeNumber, long planId) {
        return modularInMemoryRepository.getModular(countryCode, storeNumber, planId);
    }

}

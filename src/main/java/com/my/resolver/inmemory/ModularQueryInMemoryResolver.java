package com.my.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.my.model.Modular;
import com.my.repository.ModularRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ModularQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private ModularRepository modularRepository;

    public List<Modular> getModulars() {
        return modularRepository.getModulars();
    }

    public Modular getModular(String countryCode, int storeNumber, long planId) {
        return modularRepository.getModular(countryCode, storeNumber, planId);
    }

}

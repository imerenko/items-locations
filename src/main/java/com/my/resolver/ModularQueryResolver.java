package com.my.resolver;

import com.my.model.Modular;
import com.my.repository.ModularRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModularQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private ModularRepository modularRepository;

    public Modular getModular(String countryCode, int storeNumber, long planId) {
        Modular result;
        result = modularRepository.getModularByPlanId(countryCode, storeNumber, planId);
        return result;
    }

}

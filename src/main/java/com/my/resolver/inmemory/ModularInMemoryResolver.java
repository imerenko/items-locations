package com.my.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.my.model.Modular;
import com.my.model.ModularSection;
import com.my.model.Store;
import com.my.repository.ModularSectionRepository;
import com.my.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ModularResolver implements GraphQLResolver<Modular> {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ModularSectionRepository modularSectionRepository;

    public Store getStore(Modular modular) {
        return storeRepository.getStoreByGln(modular.getStore().getGln());
    }

    public List<ModularSection> getModularSections(Modular modular) {
         return modularSectionRepository.getModularSections(modular.getId());
    }
}

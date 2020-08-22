package com.my.resolver.inmemory;

import graphql.kickstart.tools.GraphQLResolver;
import com.my.model.Modular;
import com.my.model.ModularSection;
import com.my.model.Store;
import com.my.repository.inmemory.ModularSectionInMemoryRepository;
import com.my.repository.inmemory.StoreInMemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class ModularInMemoryResolver implements GraphQLResolver<Modular> {

    @Autowired
    private StoreInMemoryRepository storeInMemoryRepository;

    @Autowired
    private ModularSectionInMemoryRepository modularSectionInMemoryRepository;

    public Store getStore(Modular modular) {
        return storeInMemoryRepository.getStoreByGln(modular.getStore().getGln());
    }

    public List<ModularSection> getModularSections(Modular modular) {
         return modularSectionInMemoryRepository.getModularSections(modular.getId());
    }
}

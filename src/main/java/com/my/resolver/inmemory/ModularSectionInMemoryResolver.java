package com.my.resolver.inmemory;

import graphql.kickstart.tools.GraphQLResolver;
import com.my.model.Modular;
import com.my.model.ModularSection;
import com.my.model.ModularSectionAssignment;
import com.my.model.ModularSectionItem;
import com.my.repository.inmemory.ModularInMemoryRepository;
import com.my.repository.inmemory.ModularSectionAssignmentInMemoryRepository;
import com.my.repository.inmemory.ModularSectionItemInMemoryRepository;
import com.my.repository.inmemory.ModularSectionInMemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class ModularSectionInMemoryResolver implements GraphQLResolver<ModularSection> {

    @Autowired
    private ModularSectionItemInMemoryRepository modularSectionItemInMemoryRepository;

    @Autowired
    private ModularInMemoryRepository modularInMemoryRepository;

    @Autowired
    private ModularSectionAssignmentInMemoryRepository modularSectionAssignmentInMemoryRepository;

    @Autowired
    private ModularSectionInMemoryRepository modularSectionInMemoryRepository;

    public List<ModularSectionItem> getModularSectionItems(ModularSection modularSection) {
       return  modularSectionItemInMemoryRepository.getModularSectionItems(modularSection.getId());
    }

    public List<ModularSectionAssignment> getModularSectionAssignments(ModularSection modularSection) {
        return modularSectionAssignmentInMemoryRepository.getModularSectionAssignments(modularSection.getId());
    }

    public Modular getModular(ModularSection modularSection) {
        ModularSection fullModularSection = modularSectionInMemoryRepository.getModularSection(modularSection.getId());
        return modularInMemoryRepository.getModular(fullModularSection.getModular().getId());
    }
}

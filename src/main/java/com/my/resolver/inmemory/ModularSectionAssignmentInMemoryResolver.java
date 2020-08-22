package com.my.resolver.inmemory;

import graphql.kickstart.tools.GraphQLResolver;
import com.my.model.ModularSection;
import com.my.model.ModularSectionAssignment;
import com.my.model.Section;
import com.my.repository.inmemory.ModularSectionInMemoryRepository;
import com.my.repository.inmemory.SectionInMemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class ModularSectionAssignmentInMemoryResolver implements GraphQLResolver<ModularSectionAssignment> {

    @Autowired
    private SectionInMemoryRepository sectionInMemoryRepository;

    @Autowired
    private ModularSectionInMemoryRepository modularSectionInMemoryRepository;

    public Section getSection(ModularSectionAssignment modularSectionAssignment) {
        return sectionInMemoryRepository.getSectionBySgln(modularSectionAssignment.getSection().getSgln());
    }

    public ModularSection getModularSection(ModularSectionAssignment modularSectionAssignment) {
       return modularSectionInMemoryRepository.getModularSection(modularSectionAssignment.getModularSection().getId());
    }
}

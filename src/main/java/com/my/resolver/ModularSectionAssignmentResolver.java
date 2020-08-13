package com.my.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.my.model.ModularSection;
import com.my.model.ModularSectionAssignment;
import com.my.model.Section;
import com.my.repository.ModularSectionRepository;
import com.my.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ModularSectionAssignmentResolver implements GraphQLResolver<ModularSectionAssignment> {

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private ModularSectionRepository modularSectionRepository;

    public Section getSection(ModularSectionAssignment modularSectionAssignment) {
        return sectionRepository.getSectionBySgln(modularSectionAssignment.getSection().getSgln());
    }

    public ModularSection getModularSection(ModularSectionAssignment modularSectionAssignment) {
       return modularSectionRepository.getModularSection(modularSectionAssignment.getModularSection().getId());
    }
}

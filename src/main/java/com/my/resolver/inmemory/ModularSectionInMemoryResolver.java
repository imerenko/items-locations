package com.my.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.my.model.Modular;
import com.my.model.ModularSection;
import com.my.model.ModularSectionAssignment;
import com.my.model.ModularSectionItem;
import com.my.repository.ModularRepository;
import com.my.repository.ModularSectionAssignmentRepository;
import com.my.repository.ModularSectionItemRepository;
import com.my.repository.ModularSectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ModularSectionResolver implements GraphQLResolver<ModularSection> {

    @Autowired
    private ModularSectionItemRepository modularSectionItemRepository;

    @Autowired
    private ModularRepository modularRepository;

    @Autowired
    private ModularSectionAssignmentRepository modularSectionAssignmentRepository;

    @Autowired
    private ModularSectionRepository modularSectionRepository;

    public List<ModularSectionItem> getModularSectionItems(ModularSection modularSection) {
       return  modularSectionItemRepository.getModularSectionItems(modularSection.getId());
    }

    public List<ModularSectionAssignment> getModularSectionAssignments(ModularSection modularSection) {
        return modularSectionAssignmentRepository.getModularSectionAssignments(modularSection.getId());
    }

    public Modular getModular(ModularSection modularSection) {
        ModularSection fullModularSection = modularSectionRepository.getModularSection(modularSection.getId());
        return modularRepository.getModular(fullModularSection.getModular().getId());
    }
}

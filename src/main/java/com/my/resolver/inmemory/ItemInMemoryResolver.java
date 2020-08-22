package com.my.resolver.inmemory;

import graphql.kickstart.tools.GraphQLResolver;
import com.my.model.*;
import com.my.repository.inmemory.FlexInMemoryRepository;
import com.my.repository.inmemory.ModularSectionAssignmentInMemoryRepository;
import com.my.repository.inmemory.ModularSectionInMemoryRepository;
import com.my.repository.inmemory.SectionInMemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@Component
public class ItemInMemoryResolver implements GraphQLResolver<Item> {

    @Autowired
    private FlexInMemoryRepository flexInMemoryRepository;

    @Autowired
    private ModularSectionAssignmentInMemoryRepository modularSectionAssignmentInMemoryRepository;

    @Autowired
    private ModularSectionInMemoryRepository modularSectionInMemoryRepository;

    @Autowired
    private SectionInMemoryRepository sectionInMemoryRepository;


    public List<Flex> getFlexes(Item item) {
        return flexInMemoryRepository.getFlexByGtin(item.getStore().getCountryCode(), item.getStore().getStoreNumber(), item.getGtin());
    }

    public List<ModularSectionAssignment> getModularSectionAssignments(Item item) {
        return modularSectionAssignmentInMemoryRepository.getModularSectionAssignments(
                item.getStore().getCountryCode(), item.getStore().getStoreNumber(), item.getGtin());
    }

    public List<Section> getSections(Item item) {
        List<Flex> flexes = flexInMemoryRepository.getFlexByGtin(item.getStore().getCountryCode(), item.getStore().getStoreNumber(), item.getGtin());
        List<ModularSectionAssignment> modularSectionAssignments = modularSectionAssignmentInMemoryRepository.getModularSectionAssignments(
                item.getStore().getCountryCode(), item.getStore().getStoreNumber(), item.getGtin());
        Set<Section> sections = new HashSet<>();

        flexes.stream().forEach(f -> {
            sections.add(sectionInMemoryRepository.getSectionBySgln(f.getSection().getSgln()));
        });
        modularSectionAssignments.stream().forEach(msa -> {
            sections.add(sectionInMemoryRepository.getSectionBySgln(msa.getSection().getSgln()));
        });

        return new ArrayList<>(sections);
    }

    public List<ModularSection> getModularSections(Item item) {
        return modularSectionInMemoryRepository.getModularSectionsByGtin(item.getGtin());
    }

}

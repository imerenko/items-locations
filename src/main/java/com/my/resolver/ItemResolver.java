package com.my.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.my.model.*;
import com.my.repository.FlexRepository;
import com.my.repository.ModularSectionAssignmentRepository;
import com.my.repository.ModularSectionRepository;
import com.my.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class ItemResolver implements GraphQLResolver<Item> {

    @Autowired
    private FlexRepository flexRepository;

    @Autowired
    private ModularSectionAssignmentRepository modularSectionAssignmentRepository;

    @Autowired
    private ModularSectionRepository modularSectionRepository;

    @Autowired
    private SectionRepository sectionRepository;


    public List<Flex> getFlexes(Item item) {
        return flexRepository.getFlexByGtin(item.getStore().getCountryCode(), item.getStore().getStoreNumber(), item.getGtin());
    }

    public List<ModularSectionAssignment> getModularSectionAssignments(Item item) {
        return modularSectionAssignmentRepository.getModularSectionAssignments(
                item.getStore().getCountryCode(), item.getStore().getStoreNumber(), item.getGtin());
    }

    public List<Section> getSections(Item item) {
        List<Flex> flexes = flexRepository.getFlexByGtin(item.getStore().getCountryCode(), item.getStore().getStoreNumber(), item.getGtin());
        List<ModularSectionAssignment> modularSectionAssignments = modularSectionAssignmentRepository.getModularSectionAssignments(
                item.getStore().getCountryCode(), item.getStore().getStoreNumber(), item.getGtin());
        Set<Section> sections = new HashSet<>();

        flexes.stream().forEach(f -> {
            sections.add(sectionRepository.getSectionBySgln(f.getSection().getSgln()));
        });
        modularSectionAssignments.stream().forEach(msa -> {
            sections.add(sectionRepository.getSectionBySgln(msa.getSection().getSgln()));
        });

        return new ArrayList<>(sections);
    }

    public List<ModularSection> getModularSections(Item item) {
        return modularSectionRepository.getModularSectionsByGtin(item.getGtin());
    }

}

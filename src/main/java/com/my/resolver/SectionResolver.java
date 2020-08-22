package com.my.resolver;

import com.my.model.*;
import com.my.repository.FlexRepository;
import com.my.repository.GraphShortCutRepository;
import com.my.repository.ModularRepository;
import com.my.repository.inmemory.FlexInMemoryRepository;
import com.my.repository.inmemory.ModularSectionAssignmentInMemoryRepository;
import com.my.repository.inmemory.ModularSectionItemInMemoryRepository;
import com.my.repository.inmemory.StoreInMemoryRepository;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SectionResolver implements GraphQLResolver<Section> {

    @Autowired
    private FlexRepository flexRepository;

    @Autowired
    private ModularRepository modularRepository;

    @Autowired
    private GraphShortCutRepository graphShortCutRepository;


    public List<Flex> getFlexes(Section section) {
        return flexRepository.getFlexesBySectionName
                (section.getStore().getCountryCode(), section.getStore().getStoreNumber(), section.getName());
    }

    public List<ModularSectionAssignment> getModularSectionAssignments(Section section) {
        return modularRepository.getModularSectionAssignmentsBySectionName
                (section.getStore().getCountryCode(), section.getStore().getStoreNumber(), section.getName());
    }

    public List<Item> getItems(Section section) {

        return graphShortCutRepository.getItemsBySection
                (section.getStore().getCountryCode(), section.getStore().getStoreNumber(), section.getName());
//        List<Item> items = new ArrayList<>();
//        List<Flex> flexes = flexInMemoryRepository.getFlexBySgln(section.getSgln());
//        List<ModularSectionAssignment> modularSectionAssignments =
//                modularSectionAssignmentInMemoryRepository.getModularSectionAssignmentsBySgln(section.getSgln());
//
//        flexes.stream().forEach(flex -> {
//            items.add(new Item(flex.getGtin()));
//        });
//
//        modularSectionAssignments.stream().forEach(msa -> {
//            modularSectionItemInMemoryRepository.getModularSectionItems(msa.getModularSection().getId()).stream().forEach(
//                    msi -> {
//                        items.add(new Item(msi.getGtin()));
//                    });
//        });
//
//        return items;
    }


}

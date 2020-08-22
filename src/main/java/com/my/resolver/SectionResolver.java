package com.my.resolver.inmemory;

import graphql.kickstart.tools.GraphQLResolver;
import com.my.model.*;
import com.my.repository.inmemory.FlexInMemoryRepository;
import com.my.repository.inmemory.ModularSectionAssignmentInMemoryRepository;
import com.my.repository.inmemory.ModularSectionItemInMemoryRepository;
import com.my.repository.inmemory.StoreInMemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//@Component
public class SectionInMemoryResolver implements GraphQLResolver<Section> {

    @Autowired
    private StoreInMemoryRepository storeInMemoryRepository;

    @Autowired
    private FlexInMemoryRepository flexInMemoryRepository;

    @Autowired
    private ModularSectionAssignmentInMemoryRepository modularSectionAssignmentInMemoryRepository;

    @Autowired
    private ModularSectionItemInMemoryRepository modularSectionItemInMemoryRepository;

    public Store getStore(Section section) {
        return storeInMemoryRepository.getStoreByGln(section.getStore().getGln());
    }

    public List<Flex> getFlexes(Section section) {
        return flexInMemoryRepository.getFlexBySgln(section.getSgln());
    }

    public List<ModularSectionAssignment> getModularSectionAssignments(Section section) {
        return modularSectionAssignmentInMemoryRepository.getModularSectionAssignmentsBySgln(section.getSgln());
    }

    public List<Item> getItems(Section section) {
        List<Item> items = new ArrayList<>();
        List<Flex> flexes = flexInMemoryRepository.getFlexBySgln(section.getSgln());
        List<ModularSectionAssignment> modularSectionAssignments =
                modularSectionAssignmentInMemoryRepository.getModularSectionAssignmentsBySgln(section.getSgln());

        flexes.stream().forEach(flex -> {
            items.add(new Item(flex.getGtin()));
        });

        modularSectionAssignments.stream().forEach(msa -> {
            modularSectionItemInMemoryRepository.getModularSectionItems(msa.getModularSection().getId()).stream().forEach(
                    msi -> {
                        items.add(new Item(msi.getGtin()));
                    });
        });

        return items;
    }


}

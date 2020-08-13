package com.my.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.my.model.*;
import com.my.repository.FlexRepository;
import com.my.repository.ModularSectionAssignmentRepository;
import com.my.repository.ModularSectionItemRepository;
import com.my.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SectionResolver implements GraphQLResolver<Section> {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private FlexRepository flexRepository;

    @Autowired
    private ModularSectionAssignmentRepository modularSectionAssignmentRepository;

    @Autowired
    private ModularSectionItemRepository modularSectionItemRepository;

    public Store getStore(Section section) {
        return storeRepository.getStoreByGln(section.getStore().getGln());
    }

    public List<Flex> getFlexes(Section section) {
        return flexRepository.getFlexBySgln(section.getSgln());
    }

    public List<ModularSectionAssignment> getModularSectionAssignments(Section section) {
        return modularSectionAssignmentRepository.getModularSectionAssignmentsBySgln(section.getSgln());
    }

    public List<Item> getItems(Section section) {
        List<Item> items = new ArrayList<>();
        List<Flex> flexes = flexRepository.getFlexBySgln(section.getSgln());
        List<ModularSectionAssignment> modularSectionAssignments =
                modularSectionAssignmentRepository.getModularSectionAssignmentsBySgln(section.getSgln());

        flexes.stream().forEach(flex -> {
            items.add(new Item(flex.getGtin()));
        });

        modularSectionAssignments.stream().forEach(msa -> {
            modularSectionItemRepository.getModularSectionItems(msa.getModularSection().getId()).stream().forEach(
                    msi -> {
                        items.add(new Item(msi.getGtin()));
                    });
        });

        return items;
    }


}

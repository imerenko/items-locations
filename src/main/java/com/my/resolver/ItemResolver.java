package com.my.resolver;

import com.my.model.Flex;
import com.my.model.Item;
import com.my.model.ModularSectionItem;
import com.my.model.Section;
import com.my.repository.FlexRepository;
import com.my.repository.GraphShortCutRepository;
import com.my.repository.ModularRepository;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemResolver implements GraphQLResolver<Item> {

    @Autowired
    private FlexRepository flexRepository;

    @Autowired
    private ModularRepository modularRepository;

    @Autowired
    private GraphShortCutRepository graphShortCutRepository;


    public List<Flex> getFlexes(Item item) {
        return flexRepository.getFlexesByGtin(item.getStore().getCountryCode(), item.getStore().getStoreNumber(), item.getGtin());
    }

    public List<ModularSectionItem> getModularSectionItems(Item item) {
        return modularRepository.getModularSectionItemsByGtin(
                item.getStore().getCountryCode(), item.getStore().getStoreNumber(), item.getGtin());
    }

    public List<Section> getSections(Item item) {
        return graphShortCutRepository.getSectionsByItem
                (item.getStore().getCountryCode(), item.getStore().getStoreNumber(), item.getGtin());
//        List<Flex> flexes = flexInMemoryRepository.getFlexByGtin(item.getStore().getCountryCode(), item.getStore().getStoreNumber(), item.getGtin());
//        List<ModularSectionAssignment> modularSectionAssignments = modularSectionAssignmentInMemoryRepository.getModularSectionAssignments(
//                item.getStore().getCountryCode(), item.getStore().getStoreNumber(), item.getGtin());
//        Set<Section> sections = new HashSet<>();
//
//        flexes.stream().forEach(f -> {
//            sections.add(sectionInMemoryRepository.getSectionBySgln(f.getSection().getSgln()));
//        });
//        modularSectionAssignments.stream().forEach(msa -> {
//            sections.add(sectionInMemoryRepository.getSectionBySgln(msa.getSection().getSgln()));
//        });
//
//        return new ArrayList<>(sections);

    }



}

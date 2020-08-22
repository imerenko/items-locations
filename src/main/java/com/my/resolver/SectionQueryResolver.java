package com.my.resolver;

import com.my.model.Section;
import com.my.model.Store;
import com.my.repository.SectionRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SectionQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private SectionRepository sectionRepository;

    public Section getSection(String countryCode, int storeNumber, String name) {
        Section section = null;
         section = sectionRepository.getSectionByName(countryCode, storeNumber, name);
        if (section != null) {
            Store store = new Store(countryCode, storeNumber);
            section.setStore(store);
        }
        return section;
    }
}

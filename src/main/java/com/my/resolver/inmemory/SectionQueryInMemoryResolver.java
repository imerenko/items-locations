package com.my.resolver.inmemory;

import graphql.kickstart.tools.GraphQLQueryResolver;
import com.my.model.Section;
import com.my.repository.inmemory.SectionInMemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class SectionQueryInMemoryResolver implements GraphQLQueryResolver {

    @Autowired
    private SectionInMemoryRepository sectionInMemoryRepository;

    public List<Section> getSections(String gln) {
        return sectionInMemoryRepository.getSections(gln);
    }

    public Section getSectionBySgln(String sgln) {
        return sectionInMemoryRepository.getSectionBySgln(sgln);
    }

    public Section getSection(String countryCode, int storeNumber, String name) {
        return sectionInMemoryRepository.getSection(countryCode, storeNumber, name);
    }
}

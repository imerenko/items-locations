package com.my.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.my.model.Section;
import com.my.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SectionQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private SectionRepository sectionRepository;

    public List<Section> getSections(String gln) {
        return sectionRepository.getSections(gln);
    }

    public Section getSectionBySgln(String sgln) {
        return sectionRepository.getSectionBySgln(sgln);
    }

    public Section getSection(String countryCode, int storeNumber, String name) {
        return sectionRepository.getSection(countryCode, storeNumber, name);
    }
}

package com.my.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.my.model.Flex;
import com.my.model.Section;
import com.my.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlexResolver implements GraphQLResolver<Flex> {

    @Autowired
    private SectionRepository sectionRepository;

    public Section getSection(Flex flex) {
        return sectionRepository.getSectionBySgln(flex.getSection().getSgln());
    }
}

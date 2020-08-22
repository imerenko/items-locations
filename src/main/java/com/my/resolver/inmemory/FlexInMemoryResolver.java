package com.my.resolver.inmemory;

import graphql.kickstart.tools.GraphQLResolver;
import com.my.model.Flex;
import com.my.model.Section;
import com.my.repository.inmemory.SectionInMemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class FlexInMemoryResolver implements GraphQLResolver<Flex> {

    @Autowired
    private SectionInMemoryRepository sectionInMemoryRepository;

    public Section getSection(Flex flex) {
        return sectionInMemoryRepository.getSectionBySgln(flex.getSection().getSgln());
    }
}

package com.my.resolver;

import graphql.kickstart.tools.GraphQLQueryResolver;
import com.my.model.Flex;
import com.my.repository.FlexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FlexQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private FlexRepository flexRepository;


    public List<Flex> getFlexesByGtin(String countryCode, int storeNumber, String gtin) {
        return flexRepository.getFlexesByGtin(countryCode, storeNumber, gtin);
    }

    public List<Flex> getFlexesBySectionName(String countryCode, int storeNumber, String sectionName) {
        return flexRepository.getFlexesBySectionName(countryCode, storeNumber, sectionName);
    }
}

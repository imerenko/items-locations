package com.my.repository;

import com.my.db.DBData;
import com.my.model.Flex;
import com.my.model.Section;
import com.my.model.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Flex repository
 */
@Repository
public class FlexRepository {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private SectionRepository sectionRepository;

    public List<Flex> getFlexBySgln(String sgln) {
        return DBData.flexes.stream().filter(f -> (f.getSection().getSgln().equals(sgln))).collect(Collectors.toList());
    }

    public List<Flex> getFlexByGtin(String countryCode, int storeNumber, String gtin) {
        Store store = storeRepository.getStoreByCountryCodeAndStoreNumber(countryCode, storeNumber);
        return DBData.flexes.stream().filter(f -> {
                    Section section = sectionRepository.getSectionBySgln(f.getSection().getSgln());
                    return f.getGtin().equals(gtin) && section.getStore().getGln().equals(store.getGln());
                }

        ).collect(Collectors.toList());
    }
}

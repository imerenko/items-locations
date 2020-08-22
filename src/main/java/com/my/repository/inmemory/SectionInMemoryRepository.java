package com.my.repository;

import com.my.db.DBData;
import com.my.model.Section;
import com.my.model.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class SectionRepository {

    @Autowired
    private StoreRepository storeRepository;

    public Section getSectionBySgln(String sgln) {
       return DBData.sections.stream().filter(s -> (s.getSgln().equals(sgln))).findFirst().orElse(null);
    }

    public Section getSection (String countryCode, int storeNumber, String name) {
        return DBData.sections.stream().filter(s -> {
           Store store = storeRepository.getStoreByCountryCodeAndStoreNumber(countryCode, storeNumber);
           return s.getStore().getGln().equals(store.getGln())  && s.getName().equals(name);
         }).findFirst().orElse(null);
    }

    public List<Section> getSections(String gln) {
        return DBData.sections.stream().filter(s -> (s.getStore().getGln().equals(gln))).collect(Collectors.toList());
    }
}

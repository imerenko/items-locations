package com.my.repository;

import com.my.db.DBData;
import com.my.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ModularSectionAssignmentRepository {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ModularSectionItemRepository modularSectionItemRepository;

    @Autowired
    private ModularRepository modularRepository;

    @Autowired
    private ModularSectionRepository modularSectionRepository;

    public List<ModularSectionAssignment> getModularSectionAssignments(long modularSectionId) {
        return DBData.modularSectionAssignments.stream().filter(msa -> (msa.getModularSection().getId() == modularSectionId)).collect(Collectors.toList());
    }

    public List<ModularSectionAssignment> getModularSectionAssignmentsBySgln(String sgln) {
        return DBData.modularSectionAssignments.stream().filter(msa -> (msa.getSection().getSgln().equals(sgln))).collect(Collectors.toList());
    }

    public List<ModularSectionAssignment> getModularSectionAssignments(String countryCode, int storeNumber, String gtin) {
        Store store = storeRepository.getStoreByCountryCodeAndStoreNumber(countryCode, storeNumber);
        return DBData.modularSectionAssignments.stream().filter(msa -> {
            ModularSection modularSection = modularSectionRepository.getModularSection(msa.getModularSection().getId());
            Modular modular = modularRepository.getModular(modularSection.getModular().getId());
            if (modular.getStore().getGln().equals(store.getGln())) {
                List<ModularSectionItem> modularSectionItems = modularSectionItemRepository.getModularSectionItems(msa.getModularSection().getId());
                return modularSectionItems.stream().anyMatch(msi -> (msi.getGtin().equals(gtin)));
            } else {
                return false;
            }
        }).collect(Collectors.toList());
    }
}

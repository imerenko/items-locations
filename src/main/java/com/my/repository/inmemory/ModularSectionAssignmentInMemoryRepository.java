package com.my.repository.inmemory;

import com.my.db.DBData;
import com.my.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ModularSectionAssignmentInMemoryRepository {

    @Autowired
    private StoreInMemoryRepository storeInMemoryRepository;

    @Autowired
    private ModularSectionItemInMemoryRepository modularSectionItemInMemoryRepository;

    @Autowired
    private ModularInMemoryRepository modularInMemoryRepository;

    @Autowired
    private ModularSectionInMemoryRepository modularSectionInMemoryRepository;

    public List<ModularSectionAssignment> getModularSectionAssignments(long modularSectionId) {
        return DBData.modularSectionAssignments.stream().filter(msa -> (msa.getModularSection().getId() == modularSectionId)).collect(Collectors.toList());
    }

    public List<ModularSectionAssignment> getModularSectionAssignmentsBySgln(String sgln) {
        return DBData.modularSectionAssignments.stream().filter(msa -> (msa.getSection().getSgln().equals(sgln))).collect(Collectors.toList());
    }

    public List<ModularSectionAssignment> getModularSectionAssignments(String countryCode, int storeNumber, String gtin) {
        Store store = storeInMemoryRepository.getStoreByCountryCodeAndStoreNumber(countryCode, storeNumber);
        return DBData.modularSectionAssignments.stream().filter(msa -> {
            ModularSection modularSection = modularSectionInMemoryRepository.getModularSection(msa.getModularSection().getId());
            Modular modular = modularInMemoryRepository.getModular(modularSection.getModular().getId());
            if (modular.getStore().getGln().equals(store.getGln())) {
                List<ModularSectionItem> modularSectionItems = modularSectionItemInMemoryRepository.getModularSectionItems(msa.getModularSection().getId());
                return modularSectionItems.stream().anyMatch(msi -> (msi.getGtin().equals(gtin)));
            } else {
                return false;
            }
        }).collect(Collectors.toList());
    }
}

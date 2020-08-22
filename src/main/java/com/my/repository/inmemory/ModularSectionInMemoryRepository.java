package com.my.repository.inmemory;

import com.my.db.DBData;
import com.my.model.ModularSection;
import com.my.model.ModularSectionItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class ModularSectionInMemoryRepository {

    @Autowired
    private ModularSectionItemInMemoryRepository modularSectionItemInMemoryRepository;

    public List<ModularSection> getModularSections(long modularId) {
        return DBData.modularSections.stream().filter(m -> (m.getModular().getId() == modularId)).collect(Collectors.toList());
    }

    public ModularSection getModularSection(long modularSectionId) {
        return DBData.modularSections.stream().filter(m -> (m.getId() == modularSectionId)).findFirst().orElse(null);
    }

    public List<ModularSection> getModularSectionsByGtin(String gtin) {
        List<ModularSectionItem> modularSectionItems = modularSectionItemInMemoryRepository.getModularSectionItemsByGtin(gtin);
        Set<Long> modSectionsIds = modularSectionItems.stream().map(msi -> msi.getModularSection().getId()).collect(Collectors.toSet());

        return DBData.modularSections.stream().filter(m -> (modSectionsIds.contains(m.getId()))).collect(Collectors.toList());
    }
}

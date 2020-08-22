package com.my.repository;

import com.my.db.DBData;
import com.my.model.ModularSection;
import com.my.model.ModularSectionItem;
import org.springframework.stereotype.Repository;
import sun.security.pkcs11.Secmod;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ModularSectionItemRepository {

    public List<ModularSectionItem> getModularSectionItems(long modularSectionId) {
        return DBData.modularSectionItems.stream().filter(msi -> (msi.getModularSection().getId() == modularSectionId)).collect(Collectors.toList());
    }

    public List<ModularSectionItem> getModularSectionItemsByGtin(String gtin) {
        return DBData.modularSectionItems.stream().filter(msi -> (msi.getGtin().equals(gtin))).collect(Collectors.toList());
    }
}

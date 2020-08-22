package com.my.repository.inmemory;

import com.my.db.DBData;
import com.my.model.Modular;
import com.my.model.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ModularInMemoryRepository {

    @Autowired
    private StoreInMemoryRepository storeInMemoryRepository;

    public List<Modular> getModulars() {
        return DBData.modulars;
    }

    public Modular getModular(String countryCode, int storeNumber, long planId) {
        return DBData.modulars.stream().filter(m -> {
            Store store = storeInMemoryRepository.getStoreByGln(m.getStore().getGln());
            return store.getCountryCode().equals(countryCode) && store.getStoreNumber() == storeNumber && m.getPlanId() == planId;
        })
                .findFirst()
                .orElse(null);
    }

    public Modular getModular(long id) {
        return DBData.modulars.stream().filter(m -> (m.getId() == id))
                .findFirst()
                .orElse(null);
    }
}

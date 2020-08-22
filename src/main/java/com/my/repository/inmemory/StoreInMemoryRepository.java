package com.my.repository.inmemory;

import com.my.db.DBData;
import com.my.model.Store;
import org.springframework.stereotype.Repository;

@Repository
public class StoreInMemoryRepository {

    public Store getStoreByGln(String gln) {
       return DBData.stores.stream().filter(store -> (
                store.getGln().equals(gln)))
                .findFirst()
                .orElse(null);
    }

    public Store getStoreByCountryCodeAndStoreNumber(String countryCode, int storeNumber) {
        return DBData.stores.stream().filter(store -> (
                store.getCountryCode().equals(countryCode) && store.getStoreNumber() == storeNumber))
                .findFirst()
                .orElse(null);
    }
}

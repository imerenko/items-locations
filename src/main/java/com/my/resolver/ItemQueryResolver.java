package com.my.resolver;

import com.my.model.Item;
import com.my.model.Store;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

@Component
public class ItemQueryResolver implements GraphQLQueryResolver {

    public Item getItem(String countryCode, int storeNumber, String gtin) {
        Item item = new Item(gtin);
        Store store = new Store(countryCode, storeNumber);
        item.setStore(store);
        return item;
    }

}

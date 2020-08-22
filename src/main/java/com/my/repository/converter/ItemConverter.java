package com.my.repository.converter;

import com.couchbase.client.java.json.JsonObject;
import com.my.model.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemConverter {

    public Item convertJsonObjectToItem(JsonObject jsonObject) {
        Item item = new Item();
        item.setGtin(jsonObject.getString("gtin"));
        return item;
    }

}

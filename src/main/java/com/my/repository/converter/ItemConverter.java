package com.my.repository.converter;

import com.couchbase.client.java.json.JsonObject;
import com.my.model.Item;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GraphShortCutConverter {

    public Item convertJsonObjectToItem(JsonObject jsonObject) {
        Item item = new Item();

        return item;
    }
}

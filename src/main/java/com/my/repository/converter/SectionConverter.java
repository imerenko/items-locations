package com.my.repository.converter;

import com.couchbase.client.java.json.JsonObject;
import com.my.model.Section;
import org.springframework.stereotype.Component;

@Component
public class SectionConverter {

    public Section convertJsonObjectToSection(JsonObject jsonObject) {
        if (jsonObject == null) {
            return null;
        }
        Section result = new Section();
        result.setSgln(jsonObject.getString("sgln"));
        result.setSgln195(jsonObject.getString("sgln195"));
        result.setLegacyId(jsonObject.getString("legacyId"));
        result.setName(jsonObject.getString("name"));

        return result;
    }
}

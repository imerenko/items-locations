package com.my.repository.converter;

import com.couchbase.client.java.json.JsonObject;
import com.my.model.Flex;
import com.my.model.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlexConverter {

    @Autowired
    private SectionConverter sectionConverter;

    public Flex convertJsonObjectToFlex(JsonObject jsonObject) {
        Flex result = new Flex();
        result.setGtin(jsonObject.getString("gtin"));
        result.setAssigner(jsonObject.getString("assigner") != null ? jsonObject.getString("assigner") : "system");

        JsonObject sectionJson = jsonObject.getObject("section");

        result.setSection(sectionConverter.convertJsonObjectToSection(sectionJson));

        return result;
    }
}

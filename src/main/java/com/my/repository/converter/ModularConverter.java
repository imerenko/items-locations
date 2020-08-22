package com.my.repository.converter;

import com.couchbase.client.java.json.JsonArray;
import com.couchbase.client.java.json.JsonObject;
import com.my.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Component
public class ModularConverter {

    @Autowired
    private SectionConverter sectionConverter;

    public Modular convertJsonObjectToModular(JsonObject jsonObject) {
        Modular result = new Modular();

        result.setCategory(convertJsonObjectToCategory(jsonObject.getObject("category")));
        result.setPlanId(jsonObject.getLong("planId"));
        result.setCkbPlanId(jsonObject.getLong("ckbPlanId"));
        result.setModularName(jsonObject.getString("modularName"));
        result.setStatus(jsonObject.getString("status"));

        JsonArray sections = (JsonArray)jsonObject.get("sections");

        if (sections != null && !sections.isEmpty()) {
            // no stream support and toList returns List<HashMap>
            List<ModularSection> modularSections = new ArrayList<>();
            for (int i = 0, size = sections.size(); i < size; i++) {
                modularSections.add(convertJsonObjectToModularSection(sections.getObject(i)));
            }

            result.setModularSections(modularSections);

        }
        return result;
    }

    public ModularSectionItem convertFlatItemToModularSectionItem(JsonObject jsonObject) {
        ModularSectionItem result = convertJsonObjectToModularSectionItem(jsonObject);
        ModularSection modularSection = convertJsonObjectToModularSection(jsonObject);
        List<ModularSectionAssignment> assignments = convertJsonArrayToModularSectionAssignments((JsonArray)jsonObject.get("assignments"));
        modularSection.setModularSectionAssignments(assignments);
        Modular modular = convertJsonObjectToModular(jsonObject);
        Category category = convertJsonObjectToCategory(jsonObject.getObject("category"));
        modular.setCategory(category);
        modularSection.setModular(modular);
        result.setModularSection(modularSection);
        return result;
    }

    public ModularSectionAssignment convertFlatAssignmentToModularSectionAssignment(JsonObject jsonObject) {
        ModularSectionAssignment result = convertJsonObjectToModularSectionAssignment(jsonObject);
        ModularSection modularSection = convertJsonObjectToModularSection(jsonObject);
        List<ModularSectionItem> items = convertJsonArrayToModularSectionItems((JsonArray)jsonObject.get("items"));
        modularSection.setModularSectionItems(items);
        Modular modular = convertJsonObjectToModular(jsonObject);
        Category category = convertJsonObjectToCategory(jsonObject.getObject("category"));
        modular.setCategory(category);
        modularSection.setModular(modular);
        result.setModularSection(modularSection);
        return result;
    }

    private Category convertJsonObjectToCategory(JsonObject jsonObject) {
        Category result = new Category();
        result.setName(jsonObject.getString("categoryName"));
        result.setNumber(jsonObject.getInt("number"));
        result.setDepartment(convertJsonObjectToDepartment(jsonObject.getObject("department")));
        return result;
    }

    private Department convertJsonObjectToDepartment(JsonObject jsonObject) {
        Department result = new Department();
        result.setName(jsonObject.getString("departmentName"));
        result.setNumber(jsonObject.getInt("number"));
        return result;
    }

    private ModularSection convertJsonObjectToModularSection(JsonObject jsonObject) {
        ModularSection result = new ModularSection();

        result.setSectionNumber(jsonObject.getInt("number"));

        JsonArray items = (JsonArray)jsonObject.get("items");
        result.setModularSectionItems(convertJsonArrayToModularSectionItems(items));

        JsonArray assignments = (JsonArray)jsonObject.get("assignments");
        result.setModularSectionAssignments(convertJsonArrayToModularSectionAssignments(assignments));

        return result;
    }

    private List<ModularSectionItem> convertJsonArrayToModularSectionItems(JsonArray items) {
        List<ModularSectionItem> result = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            // no stream support and toList returns List<HashMap>
            for (int i = 0, size = items.size(); i < size; i++) {
                result.add(convertJsonObjectToModularSectionItem(items.getObject(i)));
            }
        }
        return result;
    }

    private List<ModularSectionAssignment> convertJsonArrayToModularSectionAssignments(JsonArray assignments) {
        List<ModularSectionAssignment> result = new ArrayList<>();
        if (assignments != null && !assignments.isEmpty()) {
            // no stream support and toList returns List<HashMap>
            for (int i = 0, size = assignments.size(); i < size; i++) {
                result.add(convertJsonObjectToModularSectionAssignment(assignments.getObject(i)));
            }
        }
        return result;
    }

    private ModularSectionAssignment convertJsonObjectToModularSectionAssignment(JsonObject jsonObject) {
        ModularSectionAssignment result = new ModularSectionAssignment();

        result.setAssigner(jsonObject.getString("assigner"));
        result.setAssignedDateTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(jsonObject.getLong("assignedDatetime")), ZoneOffset.UTC));

        result.setSection(sectionConverter.convertJsonObjectToSection(jsonObject.getObject("section")));

        return result;
    }

    private ModularSectionItem convertJsonObjectToModularSectionItem(JsonObject jsonObject) {
        ModularSectionItem result = new ModularSectionItem();

        result.setGtin(jsonObject.getString("gtin"));
        result.setSequenceNumber(jsonObject.getInt("seqNumber"));
        result.setCapacityCnt(jsonObject.getInt("capacityCnt"));
        result.setHorizFacingCnt(jsonObject.getInt("horizFacingCnt"));
        result.setVertFacingCnt(jsonObject.getInt("vertFacingCnt"));
        result.setMerchandiseStyleCode(jsonObject.getInt("merchandiseStyleCode"));
        result.setCapacityCnt(jsonObject.getInt("capacityCnt"));

        return result;
    }

}

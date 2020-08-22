package com.my.repository;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.json.JsonObject;
import com.couchbase.client.java.query.QueryResult;
import com.my.model.Modular;
import com.my.model.ModularSectionAssignment;
import com.my.model.ModularSectionItem;
import com.my.repository.common.ClusterServiceImpl;
import com.my.repository.converter.ModularConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ModularRepository {

    @Autowired
    private ClusterServiceImpl clusterService;

    @Autowired
    private ModularConverter modularConverter;

    public Modular getModularByPlanId(String countryCode, int storeNumber, long planId) {
        Modular result = null;

        Cluster cluster = clusterService.getCluster();
        QueryResult queryResult = cluster.query("select r.* from rampart r " +
                " where r.type = 'modular' and r.store.countryCode = '" + countryCode + "' and r.store.storeNumber = " + storeNumber
                + " and r.planId = " + planId);

        if (!queryResult.rowsAsObject().isEmpty()) {
            List<JsonObject> list = queryResult.rowsAsObject();
            result = modularConverter.convertJsonObjectToModular(list.get(0));
        }
        return result;
    }

    public List<ModularSectionItem> getModularSectionItemsByGtin(String countryCode, int storeNumber, String gtin) {


        Cluster cluster = clusterService.getCluster();
        QueryResult queryResult = cluster.query("select si.*, r.planId, r.category, r.ckbPlanId, r.modularName, " +
                "ms.`number`, ms.assignments " +
                "  from rampart as r UNNEST r.sections AS ms UNNEST ms.items AS si" +
                " where r.type = 'modular' and r.store.countryCode = '" + countryCode + "' and r.store.storeNumber = " + storeNumber
                + " and si.gtin = '" + gtin + "'");


        if (!queryResult.rowsAsObject().isEmpty()) {
            List<JsonObject> list = queryResult.rowsAsObject();
            return list.stream().map(modularConverter::convertFlatItemToModularSectionItem).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }

    public List<ModularSectionAssignment> getModularSectionAssignmentsBySectionName(String countryCode, int storeNumber, String sectionName) {


        Cluster cluster = clusterService.getCluster();
        QueryResult queryResult = cluster.query("select sa.*, ms.`number`, ms.items, r.planId, r.category, r.ckbPlanId, r.modularName " +
                " from rampart as r UNNEST r.sections AS ms UNNEST ms.assignments AS sa " +
                " where r.type = 'modular' and r.store.countryCode = '" + countryCode + "' and r.store.storeNumber = " + storeNumber
                + " and sa.section.name = '" + sectionName + "'");


        if (!queryResult.rowsAsObject().isEmpty()) {
            List<JsonObject> list = queryResult.rowsAsObject();
            return list.stream().map(modularConverter::convertFlatAssignmentToModularSectionAssignment).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }



}

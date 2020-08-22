package com.my.repository;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.json.JsonObject;
import com.couchbase.client.java.query.QueryResult;
import com.my.model.Section;
import com.my.repository.common.ClusterServiceImpl;
import com.my.repository.converter.SectionConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SectionRepository {

    @Autowired
    private ClusterServiceImpl clusterService;

    @Autowired
    private SectionConverter sectionConverter;

    public Section getSectionByName(String countryCode, int storeNumber, String sectionName) {
        Section result = null;

        Cluster cluster = clusterService.getCluster();
        QueryResult queryResult = cluster.query("select s.* from rampart r UNNEST r.aisles as a UNNEST a.sections as s " +
                " where r.type = 'location' and r.store.countryCode = '" + countryCode + "' and r.store.storeNumber = " + storeNumber
                + " and s.name = '" + sectionName + "'");

        if (!queryResult.rowsAsObject().isEmpty()) {
            List<JsonObject> list = queryResult.rowsAsObject();
            result = sectionConverter.convertJsonObjectToSection(list.get(0));
        }
        return result;
    }

}

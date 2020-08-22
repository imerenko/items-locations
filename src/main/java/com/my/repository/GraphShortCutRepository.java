package com.my.repository;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.query.QueryResult;
import com.my.model.Item;
import com.my.model.Section;
import com.my.repository.common.ClusterServiceImpl;
import com.my.repository.converter.ItemConverter;
import com.my.repository.converter.SectionConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class GraphShortCutRepository {

    @Autowired
    private ClusterServiceImpl clusterService;

    @Autowired
    private ItemConverter itemConverter;

    @Autowired
    private SectionConverter sectionConverter;

    public List<Item> getItemsBySection(String countryCode, int storeNumber, String sectionName) {
        List<Item> result;

        Cluster cluster = clusterService.getCluster();
        QueryResult queryResult = cluster.query("select si.gtin  " +
                " from rampart as r UNNEST r.sections AS ms UNNEST ms.assignments AS sa UNNEST ms.items AS si" +
                " where r.type = 'modular' and r.store.countryCode = '" + countryCode + "' and r.store.storeNumber = " + storeNumber
                + " and sa.section.name = '" + sectionName + "'" +
                " UNION " +
                " select r.gtin from rampart r" +
                " where type = 'flex' and store.countryCode = '" + countryCode + "' and store.storeNumber = " + storeNumber +
                " and section.name = '" + sectionName + "'");

        result = queryResult.rowsAsObject().stream().map(itemConverter::convertJsonObjectToItem).collect(Collectors.toList());
        return result;
    }

    public List<Section> getSectionsByItem(String countryCode, int storeNumber, String gtin) {
        List<Section> result = null;

        Cluster cluster = clusterService.getCluster();
        QueryResult queryResult = cluster.query("select  msa.section " +
                "  from rampart as r UNNEST r.sections AS ms UNNEST ms.items AS si UNNEST ms.assignments AS msa " +
                " where r.type = 'modular' and r.store.countryCode = '" + countryCode + "' and r.store.storeNumber = " + storeNumber
                + " and si.gtin = '" + gtin + "'" +
                " UNION " +
                "select r.section from rampart r" +
                " where type = 'flex' and store.countryCode = '" + countryCode + "' and store.storeNumber = " + storeNumber +
                " and gtin = '" + gtin + "'");

        result = queryResult.rowsAsObject().stream().map(jsonObject -> sectionConverter.
                convertJsonObjectToSection(jsonObject.getObject("section"))).collect(Collectors.toList());
        return result;
    }
}

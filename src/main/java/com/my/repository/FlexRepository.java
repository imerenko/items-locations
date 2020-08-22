package com.my.repository;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.query.QueryResult;
import com.my.model.Flex;
import com.my.repository.common.ClusterServiceImpl;
import com.my.repository.converter.FlexConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FlexRepository {

    @Autowired
    private ClusterServiceImpl clusterService;

    @Autowired
    private FlexConverter flexConverter;

    public List<Flex> getFlexesByGtin(String countryCode, int storeNumber, String gtin) {
        List<Flex> result;

        Cluster cluster = clusterService.getCluster();
        QueryResult queryResult = cluster.query("select r.* from rampart r" +
                " where type = 'flex' and store.countryCode = '" + countryCode + "' and store.storeNumber = " + storeNumber + " and gtin = '" + gtin + "'");

        result = queryResult.rowsAsObject().stream().map(flexConverter::convertJsonObjectToFlex).collect(Collectors.toList());
        return result;
    }

    public List<Flex> getFlexesBySectionName(String countryCode, int storeNumber, String sectionName) {
        List<Flex> result;

        Cluster cluster = clusterService.getCluster();
        QueryResult queryResult = cluster.query("select r.* from rampart r" +
                " where type = 'flex' and store.countryCode = '" + countryCode + "' and store.storeNumber = " + storeNumber + " and section.name = '" + sectionName + "'");

        result = queryResult.rowsAsObject().stream().map(flexConverter::convertJsonObjectToFlex).collect(Collectors.toList());
        return result;
    }

}

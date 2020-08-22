package com.my.repository.common;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.my.properties.CouchbaseProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Service
public class ClusterServiceImpl {

    @Autowired
    private CouchbaseProperties couchbaseProperties;

    private Cluster cluster;

    @PostConstruct
    private void init() {
        cluster = Cluster.connect(couchbaseProperties.getHost(), couchbaseProperties.getUser(), couchbaseProperties.getPassword());
    }

    public Bucket getRampartBucket() {
        return cluster.bucket(couchbaseProperties.getBucket());
    }

    public Cluster getCluster() {
        if (cluster == null) {
            init();
        }
        return cluster;
    }
}

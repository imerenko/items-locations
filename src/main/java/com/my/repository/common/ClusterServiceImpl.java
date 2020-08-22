package com.my.repository.common.impl;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.my.repository.common.ClusterService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class ClusterServiceImpl implements ClusterService {
    private Map<String, Bucket> buckets = new ConcurrentHashMap<>();


    private Cluster cluster;

    @PostConstruct
    private void init() {
        cluster = Cluster.connect("localhost:8091", "imerenko", "imerenko");
    }

    @Override
    synchronized public Bucket openBucket(String name) {
        if (!buckets.containsKey(name)) {
            Bucket bucket = cluster.bucket(name);
            buckets.put(name, bucket);
        }
        return buckets.get(name);
    }
}

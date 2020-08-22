package com.my.properties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
//@EnableConfigurationProperties
//@ConfigurationProperties
public class CouchbaseProperties {

    @Value("${couchbase.host}")
    private String host;

    @Value("${couchbase.user}")
    private String user;

    @Value("${couchbase.password}")
    private String password;

    @Value("${couchbase.bucket}")
    private String bucket;

    @Value("${value1}")
    private String value1;

}

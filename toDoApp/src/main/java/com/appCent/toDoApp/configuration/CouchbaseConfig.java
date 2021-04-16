package com.appCent.toDoApp.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

@Configuration
@EnableCouchbaseRepositories(basePackages = {"com.appCent.toDoApp.model"})
public class CouchbaseConfig extends AbstractCouchbaseConfiguration {

    @Override
    protected boolean autoIndexCreation(){
        return true;
    }

    @Override
    public String getConnectionString(){return "couchbase";}

    @Override
    public String getUserName() {
        return "productDBName";
    }

    @Override
    public String getPassword() {
        return "productDBPassword";
    }

    @Override
    public String getBucketName() {
        return "TodoApp";
    }

}

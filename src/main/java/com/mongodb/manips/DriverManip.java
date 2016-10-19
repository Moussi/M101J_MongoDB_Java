package com.mongodb.manips;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;

/**
 * Created by amoussi on 19/10/16.
 */
public class DriverManip {
  public static void main(String[] args) {
    // First Way to creat Mongo Client
    MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));
    // Second Way to creat Mongo Client
    //MongoClient client2 = new MongoClient("localhost", 27017);

    // Connected to a cluster list of servers
    //MongoClient client3 = new MongoClient(Arrays.asList(new ServerAddress("localhost", 27017)));

    // MongoClientUri
    MongoClient client4 = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));

    // Set Options
    MongoClientOptions options = MongoClientOptions.builder()
        .connectTimeout(1000)
        .connectionsPerHost(500)
        .maxWaitTime(5000)
        .build();
    MongoClient client5 = new MongoClient(new ServerAddress("localhost", 27017), options);
  }
}

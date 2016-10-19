package com.mongodb.driver;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import java.util.Arrays;

/**
 * Created by amoussi on 19/10/16.
 */
public class driver {
  public static void main(String[] args) {
    // First Way to creat Mongo Client
    MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));
    // Second Way to creat Mongo Client
    //MongoClient client2 = new MongoClient("localhost", 27017);

    // Connected to a cluster list of servers
    MongoClient client3 = new MongoClient(Arrays.asList(new ServerAddress("localhost", 27017)));
  }
}

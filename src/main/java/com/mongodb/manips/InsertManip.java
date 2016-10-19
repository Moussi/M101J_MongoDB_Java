package com.mongodb.manips;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import java.util.Arrays;

import static com.mongodb.util.Helpers.printJson;

/**
 * Created by amoussi on 19/10/16.
 */
public class InsertManip {
  public static void main(String[] args) {
    MongoClient client = new MongoClient();

    MongoDatabase db = client.getDatabase("course");
    MongoCollection<Document> coll = db.getCollection("insertTest");

    coll.drop();

    Document solo = new Document("name", "Solo")
        .append("age", 30)
        .append("profession", "engineer");
    Document moussi = new Document("name", "Moussi")
        .append("age", 26)
        .append("profession", "engineer");
    Document aymen = new Document("name", "Aymen")
        .append("age", 26)
        .append("profession", "engineer");

    printJson(moussi);
    printJson(aymen);
    printJson(solo);
    coll.insertOne(solo);
    coll.insertMany(Arrays.asList(moussi, aymen));
    printJson(moussi);
    printJson(aymen);
    printJson(solo);
  }
}

package com.mongodb.manips;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.Helpers;

import org.bson.Document;

import java.util.ArrayList;
import java.util.stream.IntStream;

import static com.mongodb.util.Helpers.printJson;

/**
 * Created by amoussi on 19/10/16.
 */
public class FindManip {
  public static void main(String[] args) {
    MongoClient client = new MongoClient();

    MongoDatabase db = client.getDatabase("course");
    MongoCollection<Document> coll = db.getCollection("findTest");
    coll.drop();

    IntStream.range(0, 3).forEach(
        nbr -> coll.insertOne(new Document("x", nbr))
    );

    System.out.println("==========FInd One==========");
    printJson(coll.find().first());
    System.out.println("==========Find all with into=========");
    coll.find().into(new ArrayList<>()).stream().forEach(Helpers::printJson);

    System.out.println("=========Find all By Iterator===========");
    MongoCursor mongoCursor = coll.find().iterator();

    try {
      while (mongoCursor.hasNext()) {
        Document doc = (Document) mongoCursor.next();
        printJson(doc);
      }
    } finally {
      mongoCursor.close();
    }

  }
}

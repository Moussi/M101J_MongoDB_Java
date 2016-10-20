package com.mongodb.manips;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.util.Helpers;

import org.bson.Document;

import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 * Created by amoussi on 19/10/16.
 */
public class DeleteManip {
  public static void main(String[] args) {
    MongoClient client = new MongoClient();

    MongoDatabase db = client.getDatabase("course");
    MongoCollection<Document> coll = db.getCollection("deleteTest");

    dropAndFillCollection(coll);


    System.out.println("==========delete all with into=========");
    System.out.println("Colection size before delete = " + coll.find().into(new ArrayList<>()).size());
    coll.deleteMany(new Document());
    System.out.println("Colection size after delete = " + coll.find().into(new ArrayList<>()).size());

    dropAndFillCollection(coll);


    System.out.println("==========deleteOne with into=========");
    System.out.println("Colection size before delete = " + coll.find().into(new ArrayList<>()).size());
    coll.deleteOne(Filters.eq("x", 2));
    System.out.println("Colection size after delete = " + coll.find().into(new ArrayList<>()).size());
    coll.find().into(new ArrayList<>()).stream().forEach(Helpers::printJson);


  }

  private static void dropAndFillCollection(MongoCollection<Document> coll) {
    coll.drop();
    IntStream.range(0, 3).forEach(
        nbr -> coll.insertOne(new Document("x", nbr))
    );
  }
}

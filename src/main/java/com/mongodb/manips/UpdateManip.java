package com.mongodb.manips;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.util.Helpers;

import org.bson.Document;

import java.util.ArrayList;
import java.util.stream.IntStream;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.inc;
import static com.mongodb.client.model.Updates.set;

/**
 * Created by amoussi on 19/10/16.
 */
public class UpdateManip {
  public static void main(String[] args) {
    MongoClient client = new MongoClient();

    MongoDatabase db = client.getDatabase("course");
    MongoCollection<Document> coll = db.getCollection("updateTest");
    coll.drop();

    fillColl(coll);


    System.out.println("==========Find all with into=========");
    coll.find().into(new ArrayList<>()).stream().forEach(Helpers::printJson);

    coll.drop();
    fillColl(coll);
    System.out.println("===========ReplaceOne and add new field===============");
    coll.replaceOne(eq("x", 2), new Document("x", 20).append("updated", false));
    coll.find().into(new ArrayList<>()).stream().forEach(Helpers::printJson);

    coll.drop();
    fillColl(coll);
    System.out.println("===========UpdateOne and add new field Using $set ===============");
    coll.updateOne(eq("x", 2), new Document("$set", new Document("x", 20).append("updated", false)));
    coll.find().into(new ArrayList<>()).stream().forEach(Helpers::printJson);

    coll.drop();
    fillColl(coll);
    System.out.println("===========UpdateOne and add new field Using Updates Class===============");
    coll.updateOne(eq("x", 2), combine(set("x", 20), set("updated", true)));
    coll.find().into(new ArrayList<>()).stream().forEach(Helpers::printJson);

    coll.drop();
    fillColl(coll);
    System.out.println("===========UpdateOne and add new field Using Updates Class And Upserts===============");
    UpdateOptions updateOptions = new UpdateOptions();
    coll.updateOne(eq("_id", 5), combine(set("x", 20), set("updated", true)), updateOptions.upsert(true));
    coll.find().into(new ArrayList<>()).stream().forEach(Helpers::printJson);

    coll.drop();
    fillColl(coll);
    System.out.println("===========UpdateMany ===============");
    coll.updateMany(Filters.gte("x", 1), inc("x", 100));
    coll.find().into(new ArrayList<>()).stream().forEach(Helpers::printJson);
  }

  private static void fillColl(MongoCollection<Document> coll) {
    IntStream.range(0, 3).forEach(
        nbr -> coll.insertOne(new Document()
            .append("_id", nbr)
            .append("x", nbr)
            .append("y", true))
    );
  }
}

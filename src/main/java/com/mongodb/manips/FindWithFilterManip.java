package com.mongodb.manips;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.Helpers;

import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.lt;

/**
 * Created by amoussi on 19/10/16.
 */
public class FindWithFilterManip {
  public static void main(String[] args) {
    MongoClient client = new MongoClient();

    MongoDatabase db = client.getDatabase("course");
    MongoCollection<Document> coll = db.getCollection("findWithFilterTest");
    coll.drop();

    //Define filter
    Bson bson = new Document("x", 0)
        .append("y", new Document("$gt", 10).append("$lt", 90));
    Bson filter = and(eq("x", 0), and(lt("y", 90), gt("y", 10)));

    IntStream.range(0, 5).forEach(
        nbr -> coll.insertOne(new Document()
                .append("x", new Random().nextInt(2))
                .append("y", new Random().nextInt(100))
        )
    );

    ArrayList<Document> allBsonFilter = coll.find(bson).into(new ArrayList<>());
    System.out.println("Result Size After BSON Filtering = " + allBsonFilter.size());
    allBsonFilter.stream().forEach(Helpers::printJson);

    System.out.println("================================");

    ArrayList<Document> allMongoFilter = coll.find(filter).into(new ArrayList<>());
    System.out.println("Result Size After Filters Filtering = " + allBsonFilter.size());
    allMongoFilter.stream().forEach(Helpers::printJson);
  }
}

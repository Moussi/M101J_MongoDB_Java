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
import static com.mongodb.client.model.Projections.exclude;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;

/**
 * Created by amoussi on 19/10/16.
 */
public class FindWithFilterProjectionManip {
  public static void main(String[] args) {
    MongoClient client = new MongoClient();

    MongoDatabase db = client.getDatabase("course");
    MongoCollection<Document> coll = db.getCollection("findWithFilterProjectionTest");
    coll.drop();

    //Define filter
    Bson filter = and(eq("x", 0), and(lt("y", 90), gt("y", 10)));
    Bson projection = new Document("x", 0).append("_id", 0);
    Bson projection2 = exclude("x", "_id");
    Bson projection3 = fields(include("i", "y"), excludeId());

    IntStream.range(0, 5).forEach(
        nbr -> coll.insertOne(new Document()
                .append("x", new Random().nextInt(2))
                .append("y", new Random().nextInt(100))
                .append("i", nbr)
        )
    );

    System.out.println("==============With Bson Document==================");

    ArrayList<Document> allMongoFilter = coll.find(filter).projection(projection).into(new ArrayList<>());
    System.out.println("Result Size After Filters Filtering = " + allMongoFilter.size());
    allMongoFilter.stream().forEach(Helpers::printJson);

    System.out.println("==============With Projections Class==================");

    allMongoFilter = coll.find(filter).projection(projection2).into(new ArrayList<>());
    System.out.println("Result Size After Filters Filtering = " + allMongoFilter.size());
    allMongoFilter.stream().forEach(Helpers::printJson);

    System.out.println("==============With Projections Class Combination Include Exclude==================");

    allMongoFilter = coll.find(filter).projection(projection3).into(new ArrayList<>());
    System.out.println("Result Size After Filters Filtering = " + allMongoFilter.size());
    allMongoFilter.stream().forEach(Helpers::printJson);
  }
}

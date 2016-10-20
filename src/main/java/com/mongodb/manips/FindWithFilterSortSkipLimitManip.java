package com.mongodb.manips;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;
import com.mongodb.util.Helpers;

import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.stream.IntStream;

import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;

/**
 * Created by amoussi on 19/10/16.
 */
public class FindWithFilterSortSkipLimitManip {
  public static void main(String[] args) {
    MongoClient client = new MongoClient();

    MongoDatabase db = client.getDatabase("course");
    MongoCollection<Document> coll = db.getCollection("findWithFilterSortSkipLimitTest");
    coll.drop();


    Bson projection = fields(include("i", "j"), excludeId());
    Bson sort = new Document("i", 1).append("j", 1);
    Bson sort2 = Sorts.orderBy(Sorts.ascending("i"), Sorts.descending("j"));
    Bson sort3 = Sorts.ascending("i", "j");

    IntStream.range(0, 3).forEach(
        i ->
            IntStream.range(0, 3).forEach(
                j -> coll.insertOne(new Document()
                        .append("i", i)
                        .append("j", j)
                ))
    );


    System.out.println("==============With Simple Sort Document==================");

    ArrayList<Document> allMongoFilter = coll
        .find()
        .projection(projection)
        .sort(sort2)
        .limit(3) // limit
        .skip(3) //skip
        .into(new ArrayList<>());
    System.out.println("Result Size After Filters Filtering = " + allMongoFilter.size());
    allMongoFilter.stream().forEach(Helpers::printJson);


  }
}

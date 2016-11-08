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

import static com.mongodb.client.model.Projections.*;

/**
 * Created by amoussi on 19/10/16.
 */
public class FindHw23Manip {
  public static void main(String[] args) {
    MongoClient client = new MongoClient();

    MongoDatabase db = client.getDatabase("students");
    MongoCollection<Document> coll = db.getCollection("grades");


      Bson sort = Sorts.orderBy(Sorts.ascending("student_id"), Sorts.ascending("score"));


    System.out.println("==============With Simple Sort Document==================");

    ArrayList<Document> allMongoFilter = coll
        .find(new Document("type", "homework"))
        .sort(sort).into(new ArrayList<>());
    System.out.println("Result Size After Filters Filtering = " + allMongoFilter.size());
    allMongoFilter.stream().forEach(Helpers::printJson);
    /*int currentStudent = -1;
    for( Document document : allMongoFilter){
      if(document.getInteger("student_id")!=currentStudent){
        currentStudent = document.getInteger("student_id");
        coll.deleteOne(document);
      }
    }*/

  }
}

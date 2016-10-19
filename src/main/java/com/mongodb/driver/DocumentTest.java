package com.mongodb.driver;

import com.mongodb.util.Helpers;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by amoussi on 19/10/16.
 */
public class DocumentTest {
  public static void main(String[] args) {
    Document document = new Document()
        .append("str", "")
        .append("l", 1L)
        .append("double", 1.1)
        .append("b", false)
        .append("date", new Date())
        .append("objectId", new ObjectId())
        .append("embeddedDoc", new Document("x", 0))
        .append("list", Arrays.asList(1, 2, 3))
        .append("int", 42);

    Helpers.printJson(document);
  }
}

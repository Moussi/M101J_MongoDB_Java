package com.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import org.bson.Document;

import java.io.IOException;
import java.io.StringWriter;

import static com.mongodb.util.Helpers.printJson;
import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.port;

/**
 * Hello world!
 */
public class HelloWorldSparkFreeMarkStyle {
  public static void main(String[] args) {

    MongoCollection<Document> collection = initMongoDatabaseCollection();

    final Configuration configuration = new Configuration();
    configuration.setClassForTemplateLoading(HelloWorldSparkFreeMarkStyle.class, "/");
    port(9000);

    Document moussi = new Document("name", "Moussi");
    collection.insertOne(moussi);

    get("/", (req, res) -> getFreeMarkTemplate(configuration, collection)
    );
  }

  private static MongoCollection<Document> initMongoDatabaseCollection() {
    MongoClient client = new MongoClient();

    MongoDatabase db = client.getDatabase("course");
    MongoCollection<Document> coll = db.getCollection("findTest");
    coll.drop();
    return coll;
  }

  private static Object getFreeMarkTemplate(Configuration configuration, MongoCollection<Document> collection) {
    StringWriter sw = new StringWriter();

    try {
      Document user = collection.find().first();
      printJson(user);
      Template template = configuration.getTemplate("hello.ftl");
      template.process(user, sw);
    } catch (IOException | TemplateException e) {
      halt(500);
      e.printStackTrace();
    }
    return sw;
  }
}

package com.mongodb;


import static spark.Spark.get;
import static spark.Spark.port;

/**
 * Hello world!
 */
public class HelloWorldSparkStyle {
  public static void main(String[] args) {
    port(9000);
    get("/", (req, res) -> "Hello World!");
  }
}

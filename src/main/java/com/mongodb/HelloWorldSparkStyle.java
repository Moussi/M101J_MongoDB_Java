package com.mongodb;

import org.apache.log4j.BasicConfigurator;

import static spark.Spark.get;
import static spark.Spark.port;

/**
 * Hello world!
 */
public class HelloWorldSparkStyle {
  public static void main(String[] args) {
    BasicConfigurator.configure();
    port(9000);
    get("/", (req, res) -> "Hello World!");
  }
}

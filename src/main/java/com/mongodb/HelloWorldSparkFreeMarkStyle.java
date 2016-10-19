package com.mongodb;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import org.apache.log4j.BasicConfigurator;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.port;

/**
 * Hello world!
 */
public class HelloWorldSparkFreeMarkStyle {
  public static void main(String[] args) {

    final Configuration configuration = new Configuration();
    configuration.setClassForTemplateLoading(HelloWorldSparkFreeMarkStyle.class, "/");
    BasicConfigurator.configure();
    port(9000);
    get("/", (req, res) -> getFreeMarkTemplate(configuration)
    );
  }

  private static Object getFreeMarkTemplate(Configuration configuration) {
    StringWriter sw = new StringWriter();

    Map<String, Object> helloMap = new HashMap<>();
    helloMap.put("name", "Moussi");

    try {
      Template template = configuration.getTemplate("hello.ftl");
      template.process(helloMap, sw);
    } catch (IOException | TemplateException e) {
      e.printStackTrace();
    }
    return sw;
  }
}

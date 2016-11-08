package com.mongodb;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 */
public class HelloWorldFreeMarkerStyle {
  public static void main(String[] args) {
    Configuration configuration = new Configuration();
    configuration.setClassForTemplateLoading(HelloWorldSparkStyle.class, "/");

    StringWriter sw = new StringWriter();

    Map<String, Object> helloMap = new HashMap<>();
    helloMap.put("name", "Moussi");


    try {
      Template template = configuration.getTemplate("hello.ftl");
      template.process(helloMap, sw);
      System.out.println(sw);
    } catch (IOException | TemplateException e) {
      e.printStackTrace();
    }
  }
}

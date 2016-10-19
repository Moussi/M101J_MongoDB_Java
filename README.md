Week 1 : Overview, Design Goals, the Mongo Shell, JSON Intro, Installing Tools, Overview of Blog Project. Maven, Spark and Freemarker Intro

```java
//Many Ways to instantiate Mongo Client
    //First Way to creat Mongo Client
    MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));
    // Second Way to creat Mongo Client
    MongoClient client2 = new MongoClient("localhost", 27017);

    //Connected to a cluster list of servers
    MongoClient client3 = new MongoClient(Arrays.asList(new ServerAddress("localhost", 27017)));

    // MongoClientUri
        MongoClient client4 = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));

        // More Options
            MongoClientOptions options = MongoClientOptions.builder()
                .connectTimeout(1000)
                .connectionsPerHost(500)
                .maxWaitTime(5000)
                .build();
            MongoClient client5 = new MongoClient(new ServerAddress("localhost", 27017), options);
```

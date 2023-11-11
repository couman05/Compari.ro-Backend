package com.youtube.jwt.controllerMongo;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.youtube.jwt.daoMongo.SearchDao;
import com.youtube.jwt.entityMongo.Product;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SearchController implements SearchDao {

    @Autowired
    MongoClient client;

    @Autowired
    MongoConverter converter;

    @Override
    public List<Product> findByText(String text) {

        final List<Product> products = new ArrayList<>();

        MongoDatabase database = client.getDatabase("laptop_data");
        MongoCollection<Document> collection = database.getCollection("laptops");

        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                        new Document("text",
                                new Document("query", text)
                                        .append("path", Arrays.asList("title", "link"))))));



                result.forEach(doc -> products.add(converter.read(Product.class, doc)));
        return products;

    }

}

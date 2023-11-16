package com.youtube.jwt.daoMongo;

import com.youtube.jwt.entityMongo.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SearchDao {

    List<Product> findByText(String text);



}

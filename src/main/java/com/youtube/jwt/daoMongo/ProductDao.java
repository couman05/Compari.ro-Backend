package com.youtube.jwt.daoMongo;

import com.youtube.jwt.entityMongo.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends MongoRepository<Product,String> {
}

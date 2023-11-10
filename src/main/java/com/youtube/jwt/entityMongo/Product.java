package com.youtube.jwt.entityMongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "laptops")
@Data
public class Product {
    @Id
    private String id;
    private String site;
    private String title;
    private double price;
    private String link;
}

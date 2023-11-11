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

    public void updateProduct(Product newProduct) {
        if (newProduct.getSite() != null) {
            this.site = newProduct.getSite();
        }
        if (newProduct.getTitle() != null) {
            this.title = newProduct.getTitle();
        }
        if (newProduct.getPrice() > 0) {
            this.price = newProduct.getPrice();
        }
        if (newProduct.getLink() != null) {
            this.link = newProduct.getLink();
        }
    }

}

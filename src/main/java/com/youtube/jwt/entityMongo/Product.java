package com.youtube.jwt.entityMongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "laptops")
@Data
public class Product {
    @Id
    private String id;
    private String site;
    private String category;
    private String title;
    private double price;
    private String link;
    private List<String> image_urls;
    private List<String> reviews;
    private List<String> description;
    private List<String> users;

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
        if(newProduct.getCategory() !=null){
            this.category=newProduct.getCategory();
        }
        if(newProduct.getImage_urls() !=null){
            this.image_urls=newProduct.getImage_urls();
        }
        if(newProduct.getReviews() !=null){
            this.reviews=newProduct.getReviews();
        }
        if(newProduct.getDescription() !=null){
            this.description=newProduct.getDescription();
        }
        if(newProduct.getUsers() !=null){
            this.users=newProduct.getUsers();
        }

    }

}

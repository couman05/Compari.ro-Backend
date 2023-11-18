package com.youtube.jwt.entityMongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "laptops")
@Data
public class Product {
    @Id
    private String id;
    private String site;
    private String title;
    private double price;
    private String link;
    private String[] users;

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


//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Product product = (Product) o;
//        return Objects.equals(id, product.id);
//        // Add other fields if necessary for a complete comparison
//    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//        // Add other fields if necessary for a complete hash
//    }
//    public void addToWishlist(String userId) {
//        String[] newUsers = new String[this.users.length + 1];
//        for (int i = 0; i < this.users.length; i++) {
//            newUsers[i] = this.users[i];
//        }
//        newUsers[this.users.length] = userId;
//        this.users = newUsers;
//    }

}

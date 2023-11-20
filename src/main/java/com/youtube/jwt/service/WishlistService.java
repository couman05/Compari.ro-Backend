package com.youtube.jwt.service;


import com.youtube.jwt.dao.UserDao;
import com.youtube.jwt.daoMongo.ProductDao;
import com.youtube.jwt.entity.User;
import com.youtube.jwt.entityMongo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishlistService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ProductDao productDao;

    public void addToWishlist(String userName, String productId) {
        Product product = productDao.findById(productId).orElse(null);
        User user = userDao.findByUserName(userName);

        System.out.println(user);

        if(user != null && product != null)
        {
            product.getUsers().add(userName);
            productDao.save(product);

        }
    }


}

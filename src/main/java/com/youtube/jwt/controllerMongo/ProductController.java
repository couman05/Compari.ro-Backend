package com.youtube.jwt.controllerMongo;

import com.youtube.jwt.daoMongo.ProductDao;
import com.youtube.jwt.entityMongo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductDao productDao;

    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/getAllProducts")
    public List<Product> getAllProducts()
    {
        return productDao.findAll();
    }
}

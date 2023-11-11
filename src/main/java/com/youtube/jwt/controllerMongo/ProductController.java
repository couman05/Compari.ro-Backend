package com.youtube.jwt.controllerMongo;

import com.youtube.jwt.daoMongo.ProductDao;
import com.youtube.jwt.daoMongo.SearchDao;
import com.youtube.jwt.entityMongo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private SearchDao searchDao;

    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/getAllProducts")
    public List<Product> getAllProducts()
    {
        return productDao.findAll();
    }


    // Beggining of search implementation

    @PreAuthorize("hasAnyRole('Admin', 'User')")
    @GetMapping("/getProduct/{text}")
    public List<Product> search(@PathVariable String text){
        return searchDao.findByText(text);
    }
}

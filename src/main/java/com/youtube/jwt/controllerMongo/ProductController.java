package com.youtube.jwt.controllerMongo;

import com.youtube.jwt.daoMongo.ProductDao;
import com.youtube.jwt.daoMongo.SearchDao;
import com.youtube.jwt.entityMongo.Product;
import com.youtube.jwt.service.PriceChangeService;
import com.youtube.jwt.service.UserService;
import com.youtube.jwt.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;


import java.util.*;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private SearchDao searchDao;


    @Autowired
    private PriceChangeService priceChangeService;

    @Autowired
    private UserService userService;

    @Autowired
    private WishlistService wishlistService;

    


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

    @PreAuthorize("hasRole('Admin')")
    @PutMapping("/admin/{id}")
    public Product updateProduct(@PathVariable String id, @RequestBody Product updatedProduct) {
        Product existingProduct = productDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        existingProduct.updateProduct(updatedProduct);
        return productDao.save(existingProduct);



    }
    @GetMapping("/getProductById/{id}")
    public Optional<Product> getProductById(@PathVariable String id)
    {
        return productDao.findById(id);
    }

    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping("/deleteProduct/{id}")
    public void deleteProduct(@PathVariable String id)
    {
        productDao.deleteById(id);
    }



    @PreAuthorize("hasRole('User')")
    @PostMapping("/addToWishlist/{productId}")
    public ResponseEntity<String> addToWishlist(@PathVariable String productId)
    {
        String userName = userService.getCurrentUsername();
        wishlistService.addToWishlist(userName, productId);
        return ResponseEntity.ok("Product added to wishlist successfully.");

    @GetMapping("/getAllCategories")
    public Set<String> getAllCategories()
    {
        List<Product> products = productDao.findAll();

        Set<String> categories = products.stream()
                .map(Product::getCategory)
                .collect(Collectors.toSet());

        return categories;

    }



}



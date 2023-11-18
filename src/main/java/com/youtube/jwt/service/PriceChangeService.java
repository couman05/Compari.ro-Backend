package com.youtube.jwt.service;

import com.youtube.jwt.controllerMongo.ProductController;
import com.youtube.jwt.dao.UserDao;
import com.youtube.jwt.daoMongo.ProductDao;
import com.youtube.jwt.entityMongo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class PriceChangeService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;


//    @Scheduled(fixedRate = 300000)
    public List<Double> getOldPrice() {
        List<Double> oldPrices = new ArrayList<>();
        List<Product> products = productDao.findAll();

        for (Product product : products) {
            double oldPrice = product.getPrice();
            oldPrices.add(oldPrice);
//            }



        }
        return oldPrices;
    }



    public List<Double> getNewPrice()
    {

            List<Double> newPrices = new ArrayList<>();
            List<Product> products = productDao.findAll();

           for (Product product : products) {
                double newPrice = product.getPrice();
               newPrices.add(newPrice);
           }

            return newPrices;

    }



    @Scheduled(fixedRate = 6000)
    public void checkPrices()  {


//        for(;;) {

            List<Double> oldPrice = getOldPrice();
            List<Double> newPrice = getNewPrice();
            List<Product> products = productDao.findAll();

                oldPrice = getOldPrice();
                //System.out.println(oldPrice);
                System.out.println("Gathered, now sleeping");
                try {
                    TimeUnit.SECONDS.sleep(5); // Adjust the sleep duration as needed
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                newPrice = getNewPrice();


                for (int i = 0; i < oldPrice.size(); i++) {
                        if (!oldPrice.get(i).equals(newPrice.get(i))) {

                            System.out.println(!oldPrice.get(i).equals(newPrice.get(i)));
                            System.out.println(oldPrice);
                            System.out.println(newPrice);
//                    System.out.println("Price changed for product with id: " + product.getId());
//                    System.out.println("Old Price: " + oldPrice);
//                    System.out.println("New Price: " + newPrice);
             }
                    //Send email to all users that have the product in their wishlist and the price is lower than the new price
//            for (String userId : product.getUsers()) {
//                if(userDao.findById(userId).get().getWishlist().contains(product.getId()) && product.getPrice() < newPrice)
//                {
//                    System.out.println("Email sent to user with id: " + userId);
//                }


                }

        }

}

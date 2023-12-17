package com.youtube.jwt.service;

import com.youtube.jwt.controllerMongo.ProductController;
import com.youtube.jwt.dao.UserDao;
import com.youtube.jwt.daoMongo.ProductDao;
import com.youtube.jwt.entity.User;
import com.youtube.jwt.entityMongo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class PriceChangeService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private JavaMailSender mailSender;

    SimpleMailMessage message = new SimpleMailMessage();



    public List<Double> getOldPrice() {
        List<Double> oldPrices = new ArrayList<>();
        List<Product> products = productDao.findAll();

        for (Product product : products) {
            double oldPrice = product.getPrice();
            oldPrices.add(oldPrice);


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

    public void sendMails(String email, Product product)
    {
        message.setFrom("johncena.robot@gmail.com");
        message.setTo(email);
        message.setSubject("PRICE CHANGED !! ");
        message.setText("Hello! Price has dropped for the product: " + product.getTitle() + "!!! Hurry up and buy it from the link: " + product.getLink());

        mailSender.send(message);
        System.out.println("Message sent");
    }



    @Scheduled(fixedRate = 6000)
    public void checkPrices()  {

            List<Double> oldPrice = getOldPrice();
            List<Double> newPrice = getNewPrice();
            List<Product> products = productDao.findAll();
            List<User> users = (List<User>) userDao.findAll();
            List<String> userEmails = new ArrayList<>();
            String email = null;

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
                        if (!oldPrice.get(i).equals(newPrice.get(i)) && oldPrice.get(i) > newPrice.get(i)) {


                            System.out.println(!oldPrice.get(i).equals(newPrice.get(i)));
                            System.out.println(oldPrice);
                            System.out.println(newPrice);
                            Product product = products.get(i);
                            System.out.println("Price changed for product with id: " + product.getId());


                            List<String> wishlistUsers = product.getUsers();
                            System.out.println("Users that have in wishlist are: " + wishlistUsers + " ,sending mail to them");
                            for (String mongouser : wishlistUsers) {
                                for (User sqluser : users)
                                {
                                     if(mongouser.equals(sqluser.getUserName()))
                                    {
                                        System.out.println("User email checkpoint");
                                        email = sqluser.getEmail();
                                        System.out.println("Email for user with username: " + mongouser + " is: " + email);
                                        sendMails(email, product);

                                    }



                                }
                             }

             }



                }

        }

}

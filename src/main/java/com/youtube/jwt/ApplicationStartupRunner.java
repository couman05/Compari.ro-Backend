package com.youtube.jwt;

import com.youtube.jwt.service.PriceChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ApplicationStartupRunner implements ApplicationRunner {

    @Autowired
    private PriceChangeService priceChangeService;

    @Override
    public void run(ApplicationArguments args) {
        // Run your scheduled tasks on application startup


        priceChangeService.checkPrices();
    }
}

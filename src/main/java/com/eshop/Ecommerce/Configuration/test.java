package com.eshop.Ecommerce.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class test implements CommandLineRunner {

    @Value("${STRIPE_SECRET_KEY}")
    private String stripeSecretKey;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Stripe Secret Key: " + stripeSecretKey);
    }
}

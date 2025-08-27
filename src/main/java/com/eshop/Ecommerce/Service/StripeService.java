package com.eshop.Ecommerce.Service;


import com.eshop.Ecommerce.Payload.StripePaymentDto;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;


public interface StripeService {

    PaymentIntent paymentIntent(StripePaymentDto stripePaymentDto) throws StripeException;
}
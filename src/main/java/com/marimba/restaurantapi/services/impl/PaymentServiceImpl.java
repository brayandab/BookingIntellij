package com.marimba.restaurantapi.services.impl;

import com.marimba.restaurantapi.exceptions.MarimbaException;
import com.marimba.restaurantapi.jsons.PaymentConfirmRest;
import com.marimba.restaurantapi.jsons.PaymentIntentRest;
import com.marimba.restaurantapi.services.EmailService;
import com.marimba.restaurantapi.services.PaymentService;
import com.marimba.restaurantapi.services.ReservationService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Value("${stripe.key.secret}")
    String secretkey;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ReservationService reservationService;

    public enum Currency{
        USD,EUR;
    }

    public PaymentIntent paymentIntent(PaymentIntentRest paymentIntentRest) throws StripeException {
        Stripe.apiKey= secretkey;
        paymentIntentRest.setPrice(1000);
        Map<String, Object> params = new HashMap<>();
        params.put("currency", Currency.EUR);
        params.put("amount",paymentIntentRest.getPrice());
        //System.out.println("Precio de la reserva: "+paymentIntentRest.getPrice());
        params.put("description",paymentIntentRest.getDescription());
        System.out.println("Costo original: "+paymentIntentRest.getPrice());
        List<Object> paymentMethodTypes = new ArrayList<>();
        paymentMethodTypes.add("card");

        params.put("payment_method_types", paymentMethodTypes);
        return PaymentIntent.create(params);
    }


    public PaymentIntent paymentConfirm(PaymentConfirmRest paymentConfirmRest) throws StripeException, MarimbaException {

        Stripe.apiKey= secretkey;
        PaymentIntent paymentIntent = PaymentIntent.retrieve(PaymentConfirmRest.getPaymentId());

        Map<String, Object> params = new HashMap<>();
        params.put("payment_method","pm_card_visa");
        paymentIntent.confirm(params);

        reservationService.updateReservation(true,paymentConfirmRest.getLocator());
        emailService.processSendEmail(paymentConfirmRest.getEmail(),"PAYMENT",paymentConfirmRest.getName());
        return paymentIntent;
    }


    public PaymentIntent paymentCancel(String paymentId) throws StripeException {
        Stripe.apiKey= secretkey;
        PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentId);

        paymentIntent.cancel();
        return paymentIntent;

    }
}

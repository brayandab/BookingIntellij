package com.marimba.restaurantapi.services;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.marimba.restaurantapi.exceptions.MarimbaException;
import com.marimba.restaurantapi.jsons.PaymentConfirmRest;
import com.marimba.restaurantapi.jsons.PaymentIntentRest;
import com.stripe.exception.StripeException;

import com.stripe.model.PaymentIntent;

public interface PaymentService {

    public PaymentIntent paymentIntent (PaymentIntentRest paymentIntentRest) throws StripeException;

    public PaymentIntent paymentConfirm (PaymentConfirmRest PaymentConfirmRest) throws StripeException, MarimbaException;

    public PaymentIntent paymentCancel(String paymentId) throws StripeException;

}

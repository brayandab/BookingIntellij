package com.marimba.restaurantapi.controllers;

import com.marimba.restaurantapi.exceptions.MarimbaException;
import com.marimba.restaurantapi.jsons.PaymentConfirmRest;
import com.marimba.restaurantapi.jsons.PaymentIntentRest;
import com.marimba.restaurantapi.services.PaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path="/booking" +"/v1")
public class PaymentController {
    @Autowired
    PaymentService paymentService;
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value= "/paymentIntent", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> paymentIntent(@RequestBody PaymentIntentRest paymentIntentRest)throws StripeException{

        PaymentIntent paymentIntent = paymentService.paymentIntent(paymentIntentRest);

        String paymentString = paymentIntent.toJson();
        return  new ResponseEntity<String>(paymentString, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
   @RequestMapping(value= "/confirm", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<String> paymentConfirm(@RequestBody PaymentConfirmRest paymentConfirmRest ) throws StripeException, MarimbaException {

       PaymentIntent paymentIntent = paymentService.paymentConfirm(paymentConfirmRest);

       String paymentString = paymentIntent.toJson();
       return  new ResponseEntity<String>(paymentString, HttpStatus.OK);
   }
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value= "/cancel/{paymentId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> paymentCancel(@PathVariable("paymentId") String paymentId)throws StripeException{

        PaymentIntent paymentIntent = paymentService.paymentCancel(paymentId);

        String paymentString = paymentIntent.toJson();
        return  new ResponseEntity<String>(paymentString, HttpStatus.OK);
    }




}

package com.marimba.restaurantapi.controllers;


import com.marimba.restaurantapi.exceptions.MarimbaException;
import com.marimba.restaurantapi.responses.MarimbaResponse;
import com.marimba.restaurantapi.services.CancelReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path="/booking" +"/v1")
public class CancelReservationController {


    @Autowired
    CancelReservationService cancelReservationService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="deleteReservation", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public MarimbaResponse<String>  deleteReservation(@RequestParam String locator) throws MarimbaException {

        return new MarimbaResponse<>("Success", String.valueOf(HttpStatus.OK), "",
                cancelReservationService.deleteReservation(locator));
    }
}

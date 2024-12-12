package com.marimba.restaurantapi.controllers;

import com.marimba.restaurantapi.jsons.ReservationRest;
import com.marimba.restaurantapi.jsons.RestaurantRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.marimba.restaurantapi.exceptions.MarimbaException;
import com.marimba.restaurantapi.jsons.CreateReservationRest;
import com.marimba.restaurantapi.responses.MarimbaResponse;
import com.marimba.restaurantapi.services.ReservationService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path="/booking" +"/v1")
public class ReservationController {

	
	@Autowired
	ReservationService reservationService;

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value="reservation"+"/{" +"reservationId" +"}", method =RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public MarimbaResponse<ReservationRest> getReservationById(@PathVariable Long reservationId) throws MarimbaException{
		return new MarimbaResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
				reservationService.getReservationById(reservationId));

	}
	
	
	@ResponseStatus(HttpStatus.OK)
	 @RequestMapping(value="reservation", method =RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public MarimbaResponse<String> createReservation(@RequestBody CreateReservationRest createReservationRest) throws MarimbaException{
		return new MarimbaResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
		   reservationService.createReservation(createReservationRest));
	}
}

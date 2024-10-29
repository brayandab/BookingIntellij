package com.marimba.restaurantapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.marimba.restaurantapi.exceptions.MarimbaException;
import com.marimba.restaurantapi.jsons.RestaurantRest;
import com.marimba.restaurantapi.responses.MarimbaResponse;
import com.marimba.restaurantapi.services.RestaurantService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path="/booking" +"/v1") //Crear constante version.
public class RestaurantController {
	
	@Autowired
	private RestaurantService restaurantService;
	
	
	@ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="restaurant"+"/{" +"restaurantId" +"}", method =RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public MarimbaResponse<RestaurantRest> getRestaurantById(@PathVariable Long restaurantId) throws MarimbaException{
			return new MarimbaResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
				restaurantService.getRestaurantById(restaurantId));
		
	}
	
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value="restaurants", method =RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public MarimbaResponse<List<RestaurantRest>> getRestaurants() throws MarimbaException{
		return new MarimbaResponse<>("Success", String.valueOf(HttpStatus.OK), "OK", restaurantService.getRestaurants());
		
	}

}

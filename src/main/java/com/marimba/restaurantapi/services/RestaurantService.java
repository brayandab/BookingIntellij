package com.marimba.restaurantapi.services;

import java.util.List;

import com.marimba.restaurantapi.exceptions.MarimbaException;
import com.marimba.restaurantapi.jsons.RestaurantRest;

public interface RestaurantService {
	
	RestaurantRest getRestaurantById(Long restauranId) throws MarimbaException;
	
	public List<RestaurantRest> getRestaurants() throws MarimbaException;

}

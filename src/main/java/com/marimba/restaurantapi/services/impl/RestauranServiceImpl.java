package com.marimba.restaurantapi.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marimba.restaurantapi.entities.Restaurant;
import com.marimba.restaurantapi.exceptions.MarimbaException;
import com.marimba.restaurantapi.exceptions.NotFountException;
import com.marimba.restaurantapi.jsons.RestaurantRest;
import com.marimba.restaurantapi.repositories.RestaurantRepository;
import com.marimba.restaurantapi.services.RestaurantService;

@Service
public class RestauranServiceImpl implements RestaurantService{

	@Autowired
    RestaurantRepository restaurantRepository;
	
	public static final ModelMapper modelMapper = new ModelMapper();

	public RestaurantRest getRestaurantById(Long restauranId) throws MarimbaException {
	
		return modelMapper.map(getRestaurantEntity(restauranId), RestaurantRest.class);
	}
	
	
	public List<RestaurantRest> getRestaurants() throws MarimbaException {
		
		final List<Restaurant> restauranntsEntity = restaurantRepository.findAll();
		
		return restauranntsEntity.stream().map(service -> modelMapper.map(service, RestaurantRest.class))
				.collect(Collectors.toList());
	}
	
	
	private Restaurant getRestaurantEntity(Long restaurantId) throws MarimbaException {
		
		return restaurantRepository.findById(restaurantId).
				orElseThrow(()-> new NotFountException("SNOT-404-1", "RESTAURANT_NOT_FOUNT"));
		
	}


	

}

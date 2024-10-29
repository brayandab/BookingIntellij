package com.marimba.restaurantapi.services;

import com.marimba.restaurantapi.exceptions.MarimbaException;

public interface CancelReservationService {
	
	public String deleteReservation(String locator) throws MarimbaException;

}

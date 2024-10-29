package com.marimba.restaurantapi.services;

import com.marimba.restaurantapi.exceptions.MarimbaException;
import com.marimba.restaurantapi.jsons.CreateReservationRest;
import com.marimba.restaurantapi.jsons.ReservationRest;

public interface ReservationService {
	
	ReservationRest getReservationById(Long reservationId) throws MarimbaException;
	
	String createReservation(CreateReservationRest createReservationRest) throws MarimbaException;

	public void updateReservation(final Boolean payment, String locator) throws MarimbaException;

}

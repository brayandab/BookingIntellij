package com.marimba.restaurantapi.services.impl;

import com.marimba.restaurantapi.entities.Reservation;
import com.marimba.restaurantapi.services.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.marimba.restaurantapi.exceptions.InternalServerErrorException;
import com.marimba.restaurantapi.exceptions.MarimbaException;
import com.marimba.restaurantapi.exceptions.NotFountException;
import com.marimba.restaurantapi.repositories.ReservationRepository;
import com.marimba.restaurantapi.services.CancelReservationService;
import org.springframework.stereotype.Service;

@Service
public class  CancelReservationServiceImpl implements CancelReservationService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CancelReservationServiceImpl.class);
	
	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private EmailService emailService;

	@Override
	public String deleteReservation(String locator) throws MarimbaException {
		//buscar en base de datos la reservacion.
		Reservation reservation = reservationRepository.findByLocator(locator)
				.orElseThrow(()-> new NotFountException("LOCATOR_NOT_FOUNT", "LOCATOR_NOT_FOUNT"));
		
		try {
			reservationRepository.deleteByLocator(locator);
		}catch(final Exception e) {
			LOGGER.error("INTERNAL_SERVER_ERROR",e);
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
			
		}

		this.emailService.processSendEmail(reservation.getEmail(),"CANCEL",reservation.getName());

		return "LOCATOR_DELETE";
	}

}

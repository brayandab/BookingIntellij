package com.marimba.restaurantapi.services.impl;


import com.marimba.restaurantapi.entities.User;
import com.marimba.restaurantapi.jsons.UserRest;
import com.marimba.restaurantapi.services.EmailService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marimba.restaurantapi.entities.Reservation;
import com.marimba.restaurantapi.entities.Restaurant;
import com.marimba.restaurantapi.entities.Turn;
import com.marimba.restaurantapi.exceptions.InternalServerErrorException;
import com.marimba.restaurantapi.exceptions.MarimbaException;
import com.marimba.restaurantapi.exceptions.NotFountException;
import com.marimba.restaurantapi.jsons.CreateReservationRest;
import com.marimba.restaurantapi.jsons.ReservationRest;
import com.marimba.restaurantapi.repositories.ReservationRepository;
import com.marimba.restaurantapi.repositories.RestaurantRepository;
import com.marimba.restaurantapi.repositories.TurnRepository;
import com.marimba.restaurantapi.services.ReservationService;


@Service
public class ReservationServiceImpl implements ReservationService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);

	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private TurnRepository turnRepository;


	
	public static final ModelMapper modelMapper = new ModelMapper();
	@Autowired
	private EmailService emailService;

	public ReservationRest getReservationById(Long reservationId) throws MarimbaException {
		return modelMapper.map(getReservationEntity(reservationId), ReservationRest.class);
		
	}

	@Override
	public String createReservation(CreateReservationRest createReservationRest) throws MarimbaException {
		
		final Restaurant restauranId = restaurantRepository.findById(createReservationRest.getRestaurantId())
				.orElseThrow(()-> new NotFountException("RESTAURANT_NOT_FOUNT", "RESTAURANT_NOT_FOUNT"));
		
		final Turn turn = turnRepository.findById(createReservationRest.getTurnId())
				.orElseThrow(()-> new NotFountException("TURN_NOT_FOUNT", "TURN_NOT_FOUNT"));
		
		String locator = generateLocator(restauranId, createReservationRest);
		System.out.println("===================LOCATOR==================");
		System.out.println(locator);
		
		final Reservation reservation = new Reservation();
		reservation.setLocator(locator);
		reservation.setPerson(createReservationRest.getPerson());
		reservation.setDate(createReservationRest.getDate());
		reservation.setRestaurant(restauranId);
		reservation.setTurn(turn.getName());
		reservation.setName(createReservationRest.getName());
		reservation.setEmail(createReservationRest.getEmail());

		try {
		reservationRepository.save(reservation);

		}catch(final Exception e) {
			LOGGER.error("INTERNAL_SERVER_ERROR",e);
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
			
		}
		this.emailService.processSendEmail(createReservationRest.getEmail(),"RESERVATION",createReservationRest.getName());
		return locator;
	}

	public void updateReservation(final Boolean payment, String locator) throws MarimbaException{
		final Reservation reservation= reservationRepository.findByLocator(locator).orElseThrow(()->new NotFountException("CODE_LOCATOR_NOT_FOUND","LOCATOR_NOT_FOUND"));
		reservation.setPayment(true);
		try {
			reservationRepository.save(reservation);
		}catch (final Exception e){
			LOGGER.error("INTERNAL_SERVER_ERROR",e);
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","INTERNAL_SERVER_ERROR");
		}
	}
	
	private String generateLocator(Restaurant restaurantId, CreateReservationRest createReservationRest) 
			throws MarimbaException {
		return restaurantId.getName()+"_"+createReservationRest.getTurnId();
		
	}

	private Reservation getReservationEntity(Long reservationId)  throws MarimbaException {
		System.out.println("ID RESERVATION: "+reservationId);
		return reservationRepository.findById(reservationId).
				orElseThrow(()-> new NotFountException("SNOT-404-1", "RESTAURANT_NOT_FOUNT"));
	}


}

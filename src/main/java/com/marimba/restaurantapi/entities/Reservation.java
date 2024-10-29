package com.marimba.restaurantapi.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "RESERVATIONS")
public class Reservation {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;
	
	
	@Column(name = "LOCATOR")
	private String locator;
	
	
	@Column(name = "PERSON")
	private Long person;
	
	@Column(name = "DATE")
	private Date date;
	
	
	@Column(name = "TURN")
	private String turn;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RESTAURANT_ID", nullable = false)
	private Restaurant restaurant;


	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USERS_ID", nullable = false)
	private User user;
*/
	@Column(name = "PAYMENT")
	private boolean payment;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "reservation")
	private List<MenuItem> menuItems;

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getLocator() {
		return locator;
	}


	public void setLocator(String locator) {
		this.locator = locator;
	}


	public Long getPerson() {
		return person;
	}


	public void setPerson(Long person) {
		this.person = person;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Restaurant getRestaurant() {
		return restaurant;
	}


	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}


	public String getTurn() {
		return turn;
	}


	public void setTurn(String turn) {
		this.turn = turn;
	}


	/*public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
*/
	public boolean isPayment() {
		return payment;
	}

	public void setPayment(boolean payment) {
		this.payment = payment;
	}



	public List<MenuItem> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(List<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}

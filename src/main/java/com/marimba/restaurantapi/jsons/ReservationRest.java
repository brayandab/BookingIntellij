package com.marimba.restaurantapi.jsons;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationRest {
	
	@JsonProperty("id")
	private Long id;
	
	
	@JsonProperty("locator")
	private String locator;
	
	
	@JsonProperty("person")
	private Long person;
	
	@JsonProperty("date")
	private Date date;
	
	
	@JsonProperty("turn")
	private String turn;
	
	@JsonProperty("restaurantId")
	private Long restaurantId;

	@JsonProperty("user")
	private UserRest user;

	@JsonProperty("menuItems")
	private List<MenuItemRest> menuItems;


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

	public String getTurn() {
		return turn;
	}

	public void setTurn(String turn) {
		this.turn = turn;
	}

	public Long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}

	public UserRest getUser() {
		return user;
	}

	public void setUser(UserRest user) {
		this.user = user;
	}

	public List<MenuItemRest> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(List<MenuItemRest> menuItems) {
		this.menuItems = menuItems;
	}
}

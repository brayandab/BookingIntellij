package com.marimba.restaurantapi.controller;

import com.marimba.restaurantapi.controllers.RestaurantController;
import com.marimba.restaurantapi.exceptions.MarimbaException;
import com.marimba.restaurantapi.jsons.RestaurantRest;
import com.marimba.restaurantapi.responses.MarimbaResponse;
import com.marimba.restaurantapi.services.RestaurantService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestaurantControllerTest {

    private static final Long RESTAURANT_ID = 1L;

    private static final String SUCCESS_STATUS = "SUCCESS";

    private static final String SUCCESS_CODE = "200 OK";

    private static final String OK = "OK";

    private static final String DATA = "OK";

    @Mock
    private RestaurantService restaurantService;

    @InjectMocks
    RestaurantController restaurantController;

    @BeforeAll
    public void init() throws MarimbaException{
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void getRestaurantByIdTest() throws MarimbaException{
        final MarimbaResponse<RestaurantRest> response = restaurantController.getRestaurantById(RESTAURANT_ID);
        assertEquals(response.getStatus(), SUCCESS_STATUS);
        assertEquals(response.getCode(), SUCCESS_CODE);
        assertEquals(response.getMessage(), OK);
        assertEquals(response.getData(), DATA);
    }
}

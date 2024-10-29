package com.marimba.restaurantapi.services;

import com.marimba.restaurantapi.exceptions.MarimbaException;

public interface EmailService {
    //Proceso para mandar el email
    public String processSendEmail(final String receiver, String templateCode, String currentName)
            throws MarimbaException;
}

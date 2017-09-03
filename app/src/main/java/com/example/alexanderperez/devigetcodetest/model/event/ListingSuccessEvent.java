package com.example.alexanderperez.devigetcodetest.model.event;

import com.example.alexanderperez.devigetcodetest.model.dto.Listing;

/**
 * Created by alexander.perez on 9/2/2017.
 */

public class ListingSuccessEvent {

    private Listing listing;

    public ListingSuccessEvent(Listing response) {
        listing = response;
    }

    public Listing getListing() {
        return listing;
    }
}

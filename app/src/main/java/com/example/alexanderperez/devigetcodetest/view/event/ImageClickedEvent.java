package com.example.alexanderperez.devigetcodetest.view.event;

/**
 * Created by alexander.perez on 9/2/2017.
 */

public class ImageClickedEvent {

    private String imageUrl;

    public ImageClickedEvent(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}

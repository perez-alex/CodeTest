package com.example.alexanderperez.devigetcodetest.model.dto;

/**
 * Created by alexander.perez on 9/2/2017.
 */

public class Image {
    ImageSource source;
    VariantsObject variants;

    public ImageSource getSource() {
        return source;
    }

    public VariantsObject getVariants() {
        return variants;
    }

    public boolean hasVariants() {
        return variants != null && (variants.getMp4() != null || variants.getGif() != null);
    }
}

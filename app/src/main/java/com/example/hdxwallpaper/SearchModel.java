package com.example.hdxwallpaper;

import java.util.Collections;
import java.util.List;

public class SearchModel {
    private final List<ImageModel> photos;

    public SearchModel(List<ImageModel> photos) {
        this.photos = Collections.unmodifiableList(photos);
    }

    public List<ImageModel> getPhotos() {
        return photos;
    }
}

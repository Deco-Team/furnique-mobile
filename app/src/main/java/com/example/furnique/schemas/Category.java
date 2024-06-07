package com.example.furnique.schemas;

import com.example.furnique.contracts.Constants;

public class Category {
    String _id;
    String name;
    String description;
    String image;
    Constants.Status status;

    public Category(String _id, String name, String description, String image, Constants.Status status) {
        this._id = _id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.status = status;
    }

    public String getImage() {
        return image;
    }
}

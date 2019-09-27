package com.android.mba;

import android.graphics.Bitmap;
import android.media.Image;

public class Team {
    private int id;
    private String name;
    private String coach;
    private String location;
    private int position;
    private int image_id;
    private String imagePath;

    public Team(int id, String name, String coach, String location, int position, String imagePath) {
        this.id = id;
        this.name = name;
        this.coach = coach;
        this.location = location;
        this.position = position;
        this.image_id = image_id;
        this.imagePath = imagePath;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public void setImage(String imagePath) {
        this.imagePath = imagePath;
    }

    public Team(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCoach() {
        return coach;
    }

    public String getLocation() {
        return location;
    }

    public int getPosition() {
        return position;
    }

    public int getImage_id() {
        return image_id;
    }

    public String getImagePath() {
        return imagePath;
    }
}

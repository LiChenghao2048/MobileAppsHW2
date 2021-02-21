package com.example.mobileappshw2;

public class Beer {

    private String id;
    private String name;
    private String description;
    private String image_url;
    private boolean like;

    public Beer(String name, String description, String image_url, String id) {
        this.name = name;
        this.description = description;
        this.image_url = image_url;
        this.id = id;
        like = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

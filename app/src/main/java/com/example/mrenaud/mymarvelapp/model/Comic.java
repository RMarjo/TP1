package com.example.mrenaud.mymarvelapp.model;

import android.graphics.Bitmap;

public class Comic {

    private String id;
    private String title;
    private String diamondCode;
    private Bitmap thumbnail;
    private String image;
    private String creatorList;
    private String dates;

    public Comic() {

    }


    public Comic(String id, String title, String diamondCode, Bitmap thumbnail, String image, String creatorList, String dates) {
        this.id = id;
        this.title = title;
        this.diamondCode = diamondCode;
        this.thumbnail = thumbnail;
        this.image = image;
        this.creatorList = creatorList;
        this.dates = dates;
    }

    // SET

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDiamondCode(String diamondCode) {
        this.diamondCode = diamondCode;
    }

    public void setThumbnail(Bitmap thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCreatorList(String creatorList) {
        this.creatorList = creatorList;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    // GET


    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDiamondCode() {
        return diamondCode;
    }

    public Bitmap getThumbnail() {
        return  thumbnail;
    }

    public String getImage() {
        return image;
    }

    public String getCreatorList() {
        return creatorList;
    }

    public String getDates() {
        return dates;
    }
}

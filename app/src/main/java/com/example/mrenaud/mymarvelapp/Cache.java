package com.example.mrenaud.mymarvelapp;

import com.example.mrenaud.mymarvelapp.model.Comic;

import java.util.List;

public class Cache {

    private static Cache instance;

    private List <Comic> comicList;

    public static Cache getInstance() {
        if (instance == null) {
            instance = new Cache();
        }
        return instance;
    }

    public void setComicList(List <Comic> comicList) {
        this.comicList = comicList;
    }

    public List <Comic> getComicList() {
        return comicList;
    }

    public Comic getItemAtPosition( int position ){
        if (comicList == null || position < 0){
            return new Comic();
        }
        return comicList.get(position);
    }
}

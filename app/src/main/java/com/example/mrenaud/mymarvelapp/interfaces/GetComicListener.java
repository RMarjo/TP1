package com.example.mrenaud.mymarvelapp.interfaces;

import com.example.mrenaud.mymarvelapp.model.Comic;

import java.util.List;

public interface GetComicListener {
    void onComicLoaded(List<Comic> comicList);
}

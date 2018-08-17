package com.example.mrenaud.mymarvelapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mrenaud.mymarvelapp.interfaces.ItemClickListener;
import com.example.mrenaud.mymarvelapp.model.Comic;

import java.util.ArrayList;
import java.util.List;

public class ComicAdapter extends RecyclerView.Adapter <ComicListViewHolder> {

    private Context context;
    private List <Comic> comic;
    ItemClickListener itemClickListener;

    public ComicAdapter(Context context, ItemClickListener itemClickListener) {
        this.context = context;
        this.comic = new ArrayList <>();
        this.itemClickListener = itemClickListener;
    }


    @NonNull
    @Override
    public ComicListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int itemType) {
        View view = LayoutInflater.from(context).inflate(R.layout.comic_list_item, parent, false);
        return new ComicListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComicListViewHolder holder, int position) {

        final Comic current = comic.get(position);
        holder.bind(current);

        holder.setClickListener(itemClickListener);

    }

    public String getItemIdAtPosition(int position) {
        return comic.get(position).getId();
    }


    public void setItemList(List <Comic> comicList) {
        comic.clear();
        comic.addAll(comicList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return comic.size();
    }

}

package com.example.mrenaud.mymarvelapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mrenaud.mymarvelapp.interfaces.ItemClickListener;
import com.example.mrenaud.mymarvelapp.model.Comic;

public class ComicListViewHolder extends RecyclerView.ViewHolder {

    private TextView textTitleView;
    private TextView textDateView;
    private ImageView imageThumbnailView;
    private TextView autorRoleView;

    private View currentView;


    public ComicListViewHolder(View itemView) {
        super(itemView);

        textTitleView = itemView.findViewById(R.id.one_comic_title);
        textDateView = (TextView) itemView.findViewById(R.id.one_comic_date);
        imageThumbnailView = (ImageView) itemView.findViewById(R.id.one_comic_thumbnail);


        currentView = itemView;
    }

    public void bind(Comic comic) {
        textTitleView.setText(comic.getTitle());
        textDateView.setText(comic.getDates());
        imageThumbnailView.setImageBitmap(comic.getThumbnail());
    }

    // c'est la que se pose le clicklistener
    public void setClickListener(final ItemClickListener itemClickListener) {
        textTitleView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                itemClickListener.onComicsTextClick(getAdapterPosition());
            }
        });
    }
}

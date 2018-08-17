package com.example.mrenaud.mymarvelapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.example.mrenaud.mymarvelapp.Cache;
import com.example.mrenaud.mymarvelapp.R;

public class DetailComicsActivity extends AppCompatActivity {

    public static final String ONE_COMIC_KEY = "oneComicKey";

    /*******************/

    public static Intent newInstance(Context context, int position) {
        Intent intent = new Intent(context, DetailComicsActivity.class);
        intent.putExtra(DetailComicsActivity.ONE_COMIC_KEY, position);
        return intent;

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_activity);
        setSupportActionBar(toolbar);


        Intent intent = getIntent();
        if (intent != null) {
            int position = 0;
            if (intent.hasExtra(ONE_COMIC_KEY)) {
                position = intent.getIntExtra(ONE_COMIC_KEY, 0);
            }

            TextView textNameView = findViewById(R.id.Comics_detail_title);
            textNameView.setText(Cache.getInstance().getItemAtPosition(position).getTitle());


            TextView textDiamondCodeView = findViewById(R.id.comics_detail_diamondCode);
            textDiamondCodeView.setText(Cache.getInstance().getItemAtPosition(position).getDiamondCode());

            TextView textDateView = findViewById(R.id.comics_detail_date);
            textDateView.setText(Cache.getInstance().getItemAtPosition(position).getDates());

            ImageView imageDetailView = findViewById(R.id.imageView2);
            imageDetailView.setImageBitmap(Cache.getInstance().getItemAtPosition(position).getThumbnail());

            TextView textAutorRoleView = findViewById(R.id.comics_detail_Autor_Role);
            textAutorRoleView.setText((Cache.getInstance().getItemAtPosition(position).getCreatorList()));
        }
    }

    //seconde activity

}

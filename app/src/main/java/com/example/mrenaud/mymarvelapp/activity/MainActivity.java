package com.example.mrenaud.mymarvelapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ShareActionProvider;

import com.example.mrenaud.mymarvelapp.Cache;
import com.example.mrenaud.mymarvelapp.ComicAdapter;
import com.example.mrenaud.mymarvelapp.interfaces.GetComicListener;
import com.example.mrenaud.mymarvelapp.GetComicTask;
import com.example.mrenaud.mymarvelapp.R;
import com.example.mrenaud.mymarvelapp.interfaces.ItemClickListener;
import com.example.mrenaud.mymarvelapp.model.Comic;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemClickListener {

    private String ws = "http://gateway.marvel.com/v1/public/comics?apikey=1be83d7f4a22fee2c24f55230eb68e2a&ts=1530713443&hash=1291749eb5862bc8207a9c29ae090e8a&format=comic&dateDescriptor=lastWeek"; // <= on mettra l'URL ici
    private ComicAdapter adapter;
    private ShareActionProvider mShareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_activity);
        setSupportActionBar(toolbar);


        adapter = new ComicAdapter(this, this);

        RecyclerView recyclerView = findViewById(R.id.main_activity_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(adapter);

        // mise en place du GetComicTask ( fonction qui gerera l'appel url )

        new GetComicTask(new GetComicListener() {
            @Override
            public void onComicLoaded(List <Comic> comicList) {
                // envoi la list récupérée vers le cache pour garder les données via cache.getInstance().setComicList(comicList)
                Cache.getInstance().setComicList(comicList);

                //update de l'adapter 
                adapter.setItemList(comicList);

            }
        }).execute(ws);



    }

    // inflate du menu dans la toolbar

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

//        MenuItem item = menu.findItem(R.id.share);
//        mShareActionProvider = (ShareActionProvider) item.getActionProvider();

        return true;
    }

    private void setShareIntent(Intent shareIntent) {
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }

    @Override
    public void onIconClick(int position) {

    }

    @Override
    public void onComicsTextClick(int position) {

        adapter.getItemIdAtPosition(position);
        startActivity(DetailComicsActivity.newInstance(this, position));

    }


    // ce qui se passe quand on click sur un comic

    public void onComicItemClick(int position) {

        adapter.getItemIdAtPosition(position);
        startActivity(DetailComicsActivity.newInstance(this, position));
    }


}

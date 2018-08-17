package com.example.mrenaud.mymarvelapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.example.mrenaud.mymarvelapp.interfaces.GetComicListener;
import com.example.mrenaud.mymarvelapp.model.Comic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetComicTask extends AsyncTask <String, Void, List <Comic>> {
    private HttpURLConnection urlConnection;
    private URL url = null;
    private GetComicListener getComicListener;

    public GetComicTask(GetComicListener getComicListener) {
        this.getComicListener = getComicListener;
    }

    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List <Comic> doInBackground(String... strings) {
        JSONObject response;
        String stringURL = strings[0];
        InputStream inputStream = null;
        List <Comic> comicList = new ArrayList <>();

        try {
            url = new URL(stringURL);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            int responseInt = urlConnection.getResponseCode();
            if (responseInt != 200) {
                return null;
            }
            inputStream = urlConnection.getInputStream();
            if (inputStream == null) {
                return null;
            }

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer buffer = new StringBuffer();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                buffer.append(line);
            }

            response = new JSONObject(buffer.toString());


            // But => rentrer dans le JSon via data qui est un Objet
            //        rentrer dans le tableau Results et boucler.
            //
            try {

                JSONObject dataJsonObj = response.optJSONObject("data"); // <= array !
                JSONArray resultJSONArray = dataJsonObj.optJSONArray("results");

                for (int i = 0; i < resultJSONArray.length(); i++) {
                    JSONObject resultsJSONObject = resultJSONArray.optJSONObject(i);

                    Comic resultComic = new Comic();

                    // dates que l'on veut a la position 0 = ok ( éventuellemment mettre un split)
                    JSONArray datesJSONArray = resultsJSONObject.getJSONArray("dates");
                    JSONObject dateJSONObj = datesJSONArray.getJSONObject(0);
                    System.out.print(dateJSONObj);


                    //récuperation des thumbnail = ok
                    JSONObject thumbnailJSONObj = resultsJSONObject.getJSONObject("thumbnail");
                    String thumbnailFormat = String.format("%s.%s", thumbnailJSONObj.optString("path"), thumbnailJSONObj.optString("extension"));
                    URL url = new URL(thumbnailFormat);
                    Bitmap thumbnailImageMain = BitmapFactory.decodeStream(url.openConnection().getInputStream());


                    //récupération des creators = ok
                    JSONObject creatorsJSONObj = resultsJSONObject.getJSONObject("creators");
                    JSONArray itemsJSONArray = creatorsJSONObj.getJSONArray("items");
                    System.out.print(itemsJSONArray);

                    //  devoir passer dans une list de string plutot que récuperer qu'une seule ligne.
                    //  RecyclerView

                    for (int j = 0; j < itemsJSONArray.length(); j++) {
                        JSONObject itemJSONObj = itemsJSONArray.optJSONObject(j);
                        String itemCreator = String.format("Auteur : %s, Rôle : %s", itemJSONObj.optString("name"), itemJSONObj.optString("role"));
                        System.out.print(itemCreator);
                        resultComic.setCreatorList(itemCreator);

                    }


                    resultComic.setThumbnail(thumbnailImageMain);
                    resultComic.setDates(dateJSONObj.optString("date"));
                    resultComic.setTitle(resultsJSONObject.optString("title"));
                    resultComic.setDiamondCode(resultsJSONObject.getString("diamondCode"));

                    comicList.add(resultComic);

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


        } catch (IOException | JSONException e)

        {
            e.printStackTrace();
        } finally

        {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ignored) {

                }
            }
        }

        return comicList;

    }

    protected void onPostExecute(List <Comic> comicList) {
        getComicListener.onComicLoaded(comicList);
    }
}

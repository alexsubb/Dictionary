
package com.example.alex.dictionary;

import android.os.AsyncTask;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class JsonHelper {

    private static String key = "trnsl.1.1.20180821T123608Z.266179947af465ba.641d3675275d3e122fcda62b8e9cfedff81f2c07";
    private static String lang = "en-ru";
    private static MyTask mt;

    public static List<String> getJsonStringYandex(final String text)  {
        final StringBuilder result = new StringBuilder();

        try {
            mt = new MyTask();
            return mt.execute(text).get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    static class MyTask extends AsyncTask<String, Void, List<String>> {

        @Override
        protected List<String> doInBackground(String... params) {
            final StringBuilder result = new StringBuilder();
            String uRl = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=" + key + "&lang=" + lang + "&text=";
            HttpURLConnection urlConnection;
            BufferedReader reader;
            try {
                URL url = new URL(uRl + params[0]);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                result.append(buffer.toString());
            } catch (Exception e) {
                result.append(e.getMessage());
            }
            return (getTranslateFromJSON(result.toString()));
        }

    }

    public static List<String> getTranslateFromJSON(String str) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Translate text = gson.fromJson(str,Translate. class);
        return text.text;
    }
}
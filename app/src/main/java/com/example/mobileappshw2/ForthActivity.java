package com.example.mobileappshw2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

import static com.loopj.android.http.AsyncHttpClient.log;

public class ForthActivity extends AppCompatActivity {

    private TextView textView_title;
    private TextView textView_ABV;
    private TextView textView_firstBrewed;
    private ImageView imageView_info;
    private TextView textView_info;

    private static final String base_url = "https://api.punkapi.com/v2/beers/";
    private static AsyncHttpClient client = new AsyncHttpClient();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forth);

        textView_title = findViewById(R.id.textView_title);
        textView_ABV = findViewById(R.id.textView_ABV);
        textView_firstBrewed = findViewById(R.id.textView_firstBrewed);
        imageView_info = findViewById(R.id.imageView_info);
        textView_info = findViewById(R.id.textView_info);

        String target_url = base_url;

        Intent intent = getIntent();
        String beer_id = intent.getStringExtra("id");
        target_url = target_url + beer_id;

        Log.d("url", target_url);

        client.addHeader("Accept", "application/json");
        client.get(target_url, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    JSONObject beerObject = new JSONArray(new String(responseBody)).getJSONObject(0);
                    String title = beerObject.getString("name");
                    String ABV = "ABV: " + beerObject.getString("abv") + "%";
                    String first_brewed = "First Brewed: " + beerObject.getString("first_brewed");
                    String info = "Description: " + beerObject.getString("description") + "\n\n" +
                            "Food Pairings: " + beerObject.getString("food_pairing") + "\n\n" +
                            "Brewster's Tips: " + beerObject.getString(("brewers_tips")) + "\n\n";
                    String image_url = beerObject.getString("image_url");

                    textView_title.setText(title);
                    Log.d("BeerName", title);
                    textView_ABV.setText(ABV);
                    textView_firstBrewed.setText(first_brewed);
                    Picasso.get().load(image_url).into(imageView_info);
                    textView_info.setText(info);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                log.d("api response", new String(responseBody));
            }
        });

    }
}

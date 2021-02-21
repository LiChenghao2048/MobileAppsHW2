package com.example.mobileappshw2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

import cz.msebera.android.httpclient.Header;

import static com.loopj.android.http.AsyncHttpClient.log;

public class ThirdActivity extends AppCompatActivity {

    private RecyclerView recyclerView_beers;
    private ArrayList<Beer> beers;

    private TextView textView_numResults;

    private static final String api_url = "https://api.punkapi.com/v2/beers?";
    private static AsyncHttpClient client = new AsyncHttpClient();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        String beer_url = api_url;

        recyclerView_beers = findViewById(R.id.recyclerView_beers);
        beers = new ArrayList<>();

        textView_numResults = findViewById(R.id.textView_numResults);

        Intent intent = getIntent();
        String beerName = intent.getStringExtra("name").replace(" ", "_").toLowerCase();
        String dateFrom = intent.getStringExtra("dateFrom").replace("/", "-");
        String dateTo = intent.getStringExtra("dateTo").replace("/", "-");
        Boolean highPoint = intent.getBooleanExtra("highPoint", false);

        if (!beerName.equals("")) {
            beer_url = beer_url + "beer_name=" + beerName + "&";
        }
        if (!dateFrom.equals("")) {
            beer_url = beer_url + "brewed_after=" + dateFrom + "&";
        }
        if (!dateTo.equals("")) {
            beer_url = beer_url + "brewed_before=" + dateTo + "&";
        }
        if (highPoint) {
            beer_url = beer_url + "abv_gt=" + "3.99&";
        } else {
            beer_url = beer_url + "abv_lt=" + "3.99&";
        }
        //Log.d("url", beer_url);

        client.addHeader("Accept", "application/json");
        client.get(beer_url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    JSONArray beersArray = new JSONArray(new String(responseBody));
                    for (int i = 0; i < beersArray.length(); i++) {
                        JSONObject beerObject = beersArray.getJSONObject(i);
                        Beer beer = new Beer(beerObject.getString("name"),
                                beerObject.getString("description"),
                                beerObject.getString("image_url"),
                                beerObject.getString("id"));
                        beers.add(beer);
                    }

                    BeerAdapter adapter = new BeerAdapter(beers);
                    recyclerView_beers.setAdapter(adapter);
                    recyclerView_beers.setLayoutManager(new LinearLayoutManager(getParent()));

                    textView_numResults.setText("We found " + beersArray.length() + " result(s).");


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

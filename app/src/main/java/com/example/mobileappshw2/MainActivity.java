package com.example.mobileappshw2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView_brew;
    private Button button_getStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView_brew = findViewById(R.id.imageView_brew);
        button_getStarted = findViewById(R.id.button_getStarted);
        loadImageFromAsset(imageView_brew, "brew.jpg");

        button_getStarted.setOnClickListener(v -> launchNextActivity(v));
    }

    public void loadImageFromAsset(ImageView imageView, String file_name) {
        // load image
        try {
            InputStream inputStream = getAssets().open(file_name);
            Drawable d = Drawable.createFromStream(inputStream, null);
            imageView.setImageDrawable(d);
        } catch (IOException e) {
            e.printStackTrace();        }
    }

    public void launchNextActivity(View view) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }
}
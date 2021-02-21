package com.example.mobileappshw2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SecondActivity extends AppCompatActivity {

    private EditText editText_beerName;
    private EditText editText_dateFrom;
    private EditText editText_dateTo;
    private Switch switch_highPoint;
    private Button button_showResults;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        editText_beerName = findViewById(R.id.editText_beerName);
        editText_dateFrom = findViewById(R.id.editTextDate_from);
        editText_dateTo = findViewById(R.id.editTextDate_to);
        switch_highPoint = findViewById(R.id.switch_highPoint);
        button_showResults = findViewById(R.id.button_showResults);

        button_showResults.setOnClickListener(v -> launchNextActivity(v));
    }

    public void launchNextActivity(View view) {
        Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);

        String beerName = editText_beerName.getText().toString();
        String dateFrom = editText_dateFrom.getText().toString();
        String dateTo = editText_dateTo.getText().toString();

        intent.putExtra("name", beerName);
        intent.putExtra("dateFrom", dateFrom);
        intent.putExtra("dateTo", dateTo);
        intent.putExtra("highPoint", switch_highPoint.isChecked());

        if (!dateFrom.matches("\\d{2}/\\d{4}") && !dateFrom.equals("")) {
            Toast toast = Toast.makeText(this, R.string.invalid_date, Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        if (!dateTo.matches("\\d{2}/\\d{4}") && !dateTo.equals("")) {
            Toast toast = Toast.makeText(this, R.string.invalid_date, Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        // I think such validation on date would be enough
        // since all invalid date inputs will lead nothing but to 0 results.

        startActivity(intent);
    }
}

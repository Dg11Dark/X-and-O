package com.example.user.xo;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SharedGames extends AppCompatActivity {

    TextView phone , games;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_games);
        SharedPreferences myPref = getSharedPreferences("myPref", MODE_PRIVATE);
        phone = (TextView) findViewById(R.id.phone);
        games = (TextView) findViewById(R.id.games);
        String names=myPref.getString("phone","nothing");
        String numbers=myPref.getString("smsInfo","nothing");

        phone.setText(names);
        games.setText(numbers);
    }



}

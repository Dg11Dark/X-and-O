package com.example.user.xo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SharedGames extends AppCompatActivity implements View.OnClickListener {

    TextView phone , games;
    Button BtnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_games);
        SharedPreferences myPref = getSharedPreferences("myPref", MODE_PRIVATE);
        phone = (TextView) findViewById(R.id.phone);
        games = (TextView) findViewById(R.id.games);
        BtnBack = (Button) findViewById(R.id.BtnBack);

        String names = myPref.getString("phone", "nothing");
        String numbers = myPref.getString("smsInfo", "nothing");

        phone.setText(names);
        games.setText(numbers);

    }
    @Override
    public void onClick (View view){
        if (view == BtnBack) {
            Intent Stati = new Intent(this, Statistic.class);
            startActivity(Stati);
            }
        }
    }


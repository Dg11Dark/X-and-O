package com.example.user.xo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Statistic extends AppCompatActivity implements View.OnClickListener{

    private Button BtnSc,BtnBac;
private TextView TvplayerName,TvLastGame,TvTotalWins,TvTotalWinsX;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        SharedPreferences.Editor editor = getSharedPreferences("myPref", MODE_PRIVATE).edit();
        SharedPreferences myPref = getSharedPreferences("myPref", MODE_PRIVATE);

         TvplayerName = (TextView) findViewById(R.id.TvplayerName);
         TvLastGame = (TextView) findViewById(R.id.TvLastGame);
         TvTotalWins = (TextView) findViewById(R.id.TvTotalWins);
         TvTotalWinsX = (TextView) findViewById(R.id.TvTotalWins);
         BtnSc = (Button) findViewById(R.id.BtnSc);
         BtnBac = (Button) findViewById(R.id.BtnBac);




        String s1 = TvplayerName.getText().toString();
        String s2 = TvLastGame.getText().toString();
        String s3 = TvTotalWins.getText().toString();


        String Name = myPref.getString("UserName", "");
        String LastGame = myPref.getString("LastGame", "");
        String TotalWinX = myPref.getString("TotalWinX", "");
    }
    @Override
    public void onClick(View view) {
        if (view == BtnSc) {

            Intent Score = new Intent(this, SendSms.class);
            startActivity(Score);
            finish();

        }
        if (view == BtnBac) {
            finish();

        }
    }


}

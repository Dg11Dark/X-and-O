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
private TextView TvplayerName,TvLastGame,TvTotalWins,xwon,owon,drawprecent;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        SharedPreferences.Editor editor = getSharedPreferences("myPref", MODE_PRIVATE).edit();
        SharedPreferences myPref = getSharedPreferences("myPref", MODE_PRIVATE);

        TvplayerName = (TextView) findViewById(R.id.TvplayerName);
        TvLastGame = (TextView) findViewById(R.id.TvLastGame);
        TvTotalWins = (TextView) findViewById(R.id.TvTotalWins);
        xwon = (TextView) findViewById(R.id.xwon);
        owon = (TextView) findViewById(R.id.owon);
        drawprecent = (TextView) findViewById(R.id.drawprecent);
        BtnSc = (Button) findViewById(R.id.BtnSc);
        BtnBac = (Button) findViewById(R.id.BtnBac);

        String Name = myPref.getString("userName", "");
        String TotalWinX = myPref.getString("X", "0");
        String TotalWinO = myPref.getString("O", "0");
        String TotalWinDraw = myPref.getString("its a Draw!", "0");
        String formattedDate = myPref.getString("formattedDate", "none");

        TvplayerName.setText(" "+TvplayerName.getText()+" "+Name);
        xwon.setText(" "+xwon.getText()+" "+TotalWinX);
        owon.setText(" "+owon.getText()+" "+TotalWinO);
        TvLastGame.setText(" "+formattedDate+" ");

        int xWins = Integer.parseInt(TotalWinX);
        int oWins = Integer.parseInt(TotalWinO);
        int draw = Integer.parseInt(TotalWinDraw);
        int totalWins= xWins+oWins+draw;
        float drawPrecent = ((float)draw/(float)totalWins)*(float)100;
        drawprecent.setText(" "+drawprecent.getText()+" "+drawPrecent+" %");
        TvTotalWins.setText(" "+totalWins+"");
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

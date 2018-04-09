package com.example.user.xo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity
{
    private GameClass game;
    private Button buttons[];
    private Button BtnStart;
    private Button BtnSta;

    private TextView TvWin;
    boolean winn = false;
    MySmsReciever smsReciever;
    SharedPreferences.Editor editor;
    SharedPreferences myPref;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BtnStart = (Button) findViewById(R.id.BtnStart);

        smsReciever= new MySmsReciever();

        buttons = new Button[]{(Button) findViewById(R.id.Btn1)
        ,(Button) findViewById(R.id.Btn2)
        ,(Button) findViewById(R.id.Btn3)
        ,(Button) findViewById(R.id.Btn4)
        ,(Button) findViewById(R.id.Btn5)
        ,(Button) findViewById(R.id.Btn6)
        ,(Button) findViewById(R.id.Btn7)
        ,(Button) findViewById(R.id.Btn8)
        ,(Button) findViewById(R.id.Btn9)};
        BtnSta = (Button) findViewById(R.id.BtnSta);
        TvWin    = (TextView) findViewById(R.id.TvWin);

        game = new GameClass();

        editor = getSharedPreferences("myPref", MODE_PRIVATE).edit();
        myPref = getSharedPreferences("myPref", MODE_PRIVATE);


        String s1 = myPref.getString("userName","");
        String s2 = myPref.getString("userPassword","");

        if (s1.equals("")&& s2.equals(""))
        {

            Intent register = new Intent(this, SignOn.class);
            startActivity(register);
            finish();
        }
        else
        {
            Intent login = new Intent(this, SignIn.class);
            startActivity(login);
        }
    }
    public void restart(View v)
    {
        this.buttons[0].setText("");
        this.buttons[1].setText("");
        this.buttons[2].setText("");
        this.buttons[3].setText("");
        this.buttons[4].setText("");
        this.buttons[5].setText("");
        this.buttons[6].setText("");
        this.buttons[7].setText("");
        this.buttons[8].setText("");

        TvWin.setText("");
        game = new GameClass();
        winn=false;

    }

    public void doAction(int i,View v){
        game.setMoves(i);
        ((Button) v).setText(game.getTav());
        TvWin.setText("Player turn: " + game.getCurrentPlayer());
        winn = game.checkGameOver();
        if(winn){
            if(game.getWinner().equals("O")) {
                int stat = Integer.parseInt(myPref.getString("O", "0"));
                stat++;
                editor.putString("O", stat + "");
                editor.commit();
            }
            else if(game.getWinner().equals("X")) {
                int stat = Integer.parseInt(myPref.getString("X", "0"));
                stat++;
                editor.putString("X", stat + "");
                editor.commit();
            }
            else if(game.getWinner().equals("its a Draw!")) {
                int stat = Integer.parseInt(myPref.getString("its a Draw!", "0"));
                stat++;
                editor.putString("its a Draw!", stat + "");
                editor.commit();
            }
            Date c = Calendar.getInstance().getTime();

            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
            String formattedDate = df.format(c);
            editor.putString("formattedDate", formattedDate);
            editor.commit();
            TvWin.setText("the winner is... " + game.getWinner());
        }
    }

    public void ClickGame(View v) {
        if (v == BtnSta) {
            Intent Stati = new Intent(this, Statistic.class);
            startActivity(Stati);

        }else {

            if (winn) {
                Toast.makeText(this, "The game has won already", Toast.LENGTH_SHORT).show();
            }else {
                if(!((Button) v).getText().equals("")){
                    Toast.makeText(this, "this block is already filled", Toast.LENGTH_SHORT).show();
                }else {
                    switch (v.getTag().toString()) {
                        case "1": {doAction(0,v);break;}
                        case "2": {doAction(1,v);break;}
                        case "3": {doAction(2,v);break;}
                        case "4": {doAction(3,v);break;}
                        case "5": {doAction(4,v);break;}
                        case "6": {doAction(5,v);break;}
                        case "7": {doAction(6,v);break;}
                        case "8": {doAction(7,v);break;}
                        case "9": {doAction(8,v);break;}
                    }
                }
            }
        }
    }
}

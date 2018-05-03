package com.example.ss12dark.game;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */

    int stageNum;
    Button b1, b2, b3, b4, b5, b6, b7, b8, b9;
    TextView stage, des;
    int Bred, Bgreen, Bblue, Twhite, Tyellow, Tblack;

    private String r,g,b,rg,rb,gb;
    int winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);

        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        b4 = findViewById(R.id.b4);
        b5 = findViewById(R.id.b5);
        b6 = findViewById(R.id.b6);
        b7 = findViewById(R.id.b7);
        b8 = findViewById(R.id.b8);
        b9 = findViewById(R.id.b9);
        stage = findViewById(R.id.stage);
        des = findViewById(R.id.des);
        r="red is the biggest";
        g="green is the biggest";
        b="blue is the biggest";
        rg="red and green is the biggest";
        rb="red and blue is the biggest";
        gb="green and blue is the biggest";
        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);



        // Set up the user interaction to manually show or hide the system UI.
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
            }
        });

        randomAll();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        hide();
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }
    //----------------------------------------------------------------------------------------------------------------------------------------------

    public void reset(View v){
        stageNum = 1;
        stage.setText("Stage "+stageNum);
        randomAll();
    }

    public void resetnums(){
        Tblack=0;
        Tyellow=0;
        Twhite=0;
        Bblue=0;
        Bgreen=0;
        Bred=0;
    }

    public void randomAll() {
        resetnums();
        randomEach(b1);
        randomEach(b2);
        randomEach(b3);
        randomEach(b4);
        randomEach(b5);
        randomEach(b6);
        randomEach(b7);
        randomEach(b8);
        randomEach(b9);
        winner = Integer.parseInt(winButton());
        des.setText(randomPlaces());
    }

    public void randomEach(Button b) {
        randomBackground(b);
        randomText(b);
        setLose(b);
    }

    public void randomText(Button b){
        Random rand = new Random();
        int n = rand.nextInt(3) + 1;
        switch (n) {
            case 1: {
                b.setTextColor(Color.WHITE);
                Twhite++;
                break;
            }
            case 2: {
                b.setTextColor(Color.YELLOW);
                Tyellow++;
                break;
            }
            case 3: {
                b.setTextColor(Color.BLACK);
                Tblack++;
                break;
            }
        }
    }

    public void randomBackground(Button b){
        Random rand = new Random();
        int n = rand.nextInt(3) + 1;
        switch (n) {
            case 1: {
                b.setBackgroundColor(Color.BLUE);
                b.setTag(1);
                Bblue++;
                break;
            }
            case 2: {
                b.setBackgroundColor(Color.RED);
                b.setTag(2);
                Bred++;
                break;
            }
            case 3: {
                b.setBackgroundColor(Color.GREEN);
                b.setTag(3);
                Bgreen++;
                break;
            }
        }
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------

    public String MostApearBackground (){

        String answer1=whoBigger(Bblue,Bgreen);
        if(answer1.equals("first")){
            String answer2=whoBigger(Bblue,Bred);
            if(answer2.equals("first")){
                return b;
            }else if(answer2.equals("draw")){
                return rb;
            }else{
                return r;
            }
        }

        if(answer1.equals("draw")){
            String answer2=whoBigger(Bblue,Bred);
            if(answer2.equals("second")){
                return r;
            }else if(answer2.equals("draw")){
                return "red and blue and green are the same";
            }else{
                return gb;
            }
        }

        if(answer1.equals("second")){
            String answer2=whoBigger(Bred,Bgreen);
            if(answer2.equals("second")){
                return g;
            }else if(answer2.equals("draw")){
                return rg;
            }else{
                return r;
            }
        }


        return "hey";
    }

    public String whoBigger(int a , int b){
        if(a>b){
            return "first";
        }else if(a<b){
            return "second";
        }else{
            return "draw";
        }

    }

    public int falseNum(int a){
        Random rand = new Random();
        int n = rand.nextInt(6) + 1;
        while(n==a){
            n = rand.nextInt(6) + 1;
        }
        return n;
    }


    //----------------------------------------------------------------------------------------------------------------------------------------------
    public String randomQuest1 (){
        int num1=0;

        String rp2 = randomPart2();
        if(rp2.equals("white")){
            num1=falseNum(Twhite);
        }else if(rp2.equals("black")){
            num1=falseNum(Tblack);
        }else{
            num1=falseNum(Tyellow);
        }

        String a = "if the number of background colors "+randomPart1()+" and the number of the text color "+rp2+" is "+num1+" press on"+"B"+randomLostButton();

        return a;
    }
    public String randomQuest2 (){
        int num1=0;

        String rp2 = randomPart2();
        if(rp2.equals("white")){
            num1=Twhite;
        }else if(rp2.equals("black")){
            num1=Tblack;
        }else{
            num1=Tyellow;
        }

        String a = "if the number of background colors "+randomPart1()+" and the number of the text color "+rp2+" is "+num1+" press on"+"B"+randomLostButton();

        return a;
    }
    public String randomQuest3 (){
        int num1=0;

        String rp2 = randomPart2();
        if(rp2.equals("white")){
            num1=falseNum(Twhite);
        }else if(rp2.equals("black")){
            num1=falseNum(Tblack);
        }else{
            num1=falseNum(Tyellow);
        }

        String a = "if the number of background colors "+MostApearBackground()+" and the number of the text color "+rp2+" is "+num1+" press on"+"B"+randomLostButton();

        return a;
    }

    public String randomPart1(){
        Random rand = new Random();
        int n = rand.nextInt(6) + 1;
        String a="hey";
       switch (n){
           case 1:{ a=rg;break; }
           case 2:{ a=rb;break; }
           case 3:{ a=gb;break; }
           case 4:{ a=r;break; }
           case 5:{ a=g;break; }
           case 6:{ a=b;break; }
        }
        return a;
    }

    public String randomPart2(){
        Random rand = new Random();
        int n = rand.nextInt(3) + 1;
        String a="hey";
        switch (n){
            case 1:{ a="white";break; }
            case 2:{ a="black";break; }
            case 3:{ a="yellow";break; }
        }
        return a;
    }

    public String rightAnswer(){
        int num1;
        String rp2 = randomPart2();
        if(rp2.equals("white")){
            num1=Twhite;
        }else if(rp2.equals("black")){
            num1=Tblack;
        }else{
            num1=Tyellow;
        }
        String a = "if the number of background colors "+MostApearBackground()+" and the number of the text color "+rp2+" is "+num1+" press on"+" B"+winner;
        return a;
    }

    public String winButton(){
        Random rand = new Random();
        int n = rand.nextInt(9) + 1;
        String a="hey";
        switch (n){
            case 1:{ b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        win();
                    }
                });a="1"; break;}
            case 2:{ b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    win();
                }
            });a="2"; break;}
            case 3:{ b3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    win();
                }
            });a="3"; break;}
            case 4:{ b4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    win();
                }
            });a="4"; break;}
            case 5:{ b5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    win();
                }
            });a="5"; break;}
            case 6:{ b6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    win();
                }
            });a="6"; break;}
            case 7:{ b7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    win();
                }
            });a="7"; break;}
            case 8:{ b8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    win();
                }
            });a="8"; break;}
            case 9:{ b9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    win();
                }
            });a="9"; break;}

        }


        return a;
    }

    public void setLose(Button b){
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lose();
            }
        });
    }

    public String randomLostButton(){
        Random rand = new Random();
        int n = rand.nextInt(9) + 1;
        String a="hey";
        while(n==winner){
            n = rand.nextInt(9) + 1;
        }
        switch (n){
            case 1:{a="1"; break;}
            case 2:{ b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lose();
                }
            });a="2"; break;}
            case 3:{ b3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lose();
                }
            });a="3"; break;}
            case 4:{ b4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lose();
                }
            });a="4"; break;}
            case 5:{ b5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lose();
                }
            });a="5"; break;}
            case 6:{ b6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lose();
                }
            });a="6"; break;}
            case 7:{ b7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lose();
                }
            });a="7"; break;}
            case 8:{ b8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lose();
                }
            });a="8"; break;}
            case 9:{ b9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lose();
                }
            });a="9"; break;}

        }

        return a;
    }

    public String randomPlaces(){

        String a = rightAnswer();
        String b = randomQuest1();
        String c = randomQuest2();
        String d = randomQuest3();

        String end="hey";

        Random rand = new Random();
        int n = rand.nextInt(12) + 1;

        switch (n){
            case 1:{end=a+"\n"+b+"\n"+c+"\n"+d;break;}
            case 2:{end=b+"\n"+a+"\n"+c+"\n"+d;break;}
            case 3:{end=c+"\n"+b+"\n"+a+"\n"+d;break;}
            case 4:{end=d+"\n"+b+"\n"+c+"\n"+a;break;}
            case 5:{end=a+"\n"+c+"\n"+d+"\n"+b;break;}
            case 6:{end=b+"\n"+c+"\n"+a+"\n"+d;break;}
            case 7:{end=c+"\n"+a+"\n"+b+"\n"+d;break;}
            case 8:{end=d+"\n"+d+"\n"+b+"\n"+a;break;}
            case 9:{end=a+"\n"+c+"\n"+d+"\n"+d;break;}
            case 10:{end=b+"\n"+d+"\n"+c+"\n"+a;break;}
            case 11:{end=c+"\n"+d+"\n"+b+"\n"+a;break;}
            case 12:{end=d+"\n"+b+"\n"+a+"\n"+c;break;}


        }
        return end;
    }
    //----------------------------------------------------------------------------------------------------------------------------------------------
    public void win(){
        stageNum++;
        stage.setText("Stage "+stageNum);
        randomAll();
    }

    public void lose(){
        Toast.makeText(this,"wrong button!",Toast.LENGTH_SHORT).show();
        stageNum = 1;
        stage.setText("Stage "+stageNum);
        randomAll();
    }
}

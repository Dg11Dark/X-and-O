package com.example.user.xo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignIn extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
    }

    public void reg(View v)
    {
        SharedPreferences.Editor editor = getSharedPreferences("myPref", MODE_PRIVATE).edit();
        SharedPreferences myPref = getSharedPreferences("myPref", MODE_PRIVATE);

        EditText EtName  = (EditText) findViewById(R.id.EtName);
        EditText EtPass  = (EditText) findViewById(R.id.EtPass);

        String s1 = EtName.getText().toString();
        String s2 = EtPass.getText().toString();

        String Name = myPref.getString("userName","");
        String Pass = myPref.getString("userPassword","");

        if(s1.equals(Name)&& s2.equals(Pass))
        {
            Toast.makeText(this,"login success",Toast.LENGTH_SHORT).show();
            Intent Main = new Intent(this,MainActivity.class);
            Main.putExtra("check",1);
            startActivity(Main);
            finish();
        }
        else
        {
            Toast.makeText(this,"login failed. wrong username or password",Toast.LENGTH_SHORT).show();

        }
    }
}




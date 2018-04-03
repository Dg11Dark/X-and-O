package com.example.user.xo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignOn extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_on);
    }
    public void reg(View v)
    {
        SharedPreferences.Editor editor = getSharedPreferences("myPref", MODE_PRIVATE).edit();
        SharedPreferences myPref = getSharedPreferences("myPref", MODE_PRIVATE);

        EditText EtEmail  = (EditText) findViewById(R.id.EtEmail);
        EditText EtPass1  = (EditText) findViewById(R.id.EtPass1);
        EditText EtPass2  = (EditText) findViewById(R.id.EtPass2);


        String s1 = EtEmail.getText().toString();
        String s2 = EtPass1.getText().toString();
        String s3 = EtPass2.getText().toString();

        if (s3.equals(s2))
        {
            editor.putString("userName",s1);
            editor.putString("userPassword",s2);

            editor.apply();

            Intent i = new Intent(this,SignIn.class);

            startActivity(i);
            finish();
        }
        else
        {
            Toast.makeText(this, "wrong", Toast.LENGTH_SHORT).show();
        }
    }
}

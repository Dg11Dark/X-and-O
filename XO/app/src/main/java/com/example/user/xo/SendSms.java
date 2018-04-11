package com.example.user.xo;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SendSms extends AppCompatActivity implements View.OnClickListener {

    private TextView EtMass, EtName, EtPho;
    private Button BtnSend, BtnPick,BtnBa;
    private final int PICK_CONTACT_REQUEST = 110;
    final int MY_PERMISSIONS_REQUEST_SEND_SMS = 22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);
        SharedPreferences myPref = getSharedPreferences("myPref", MODE_PRIVATE);
        String Name = myPref.getString("userName", "");
        String totalgames = myPref.getString("totalwins", "");
        EtMass = (TextView) findViewById(R.id.EtMass);
        EtName = (TextView) findViewById(R.id.EtName);
        EtPho = (TextView) findViewById(R.id.EtPho);

        BtnSend = (Button) findViewById(R.id.BtnSend);
        BtnPick = (Button) findViewById(R.id.BtnPick);
        BtnBa = (Button) findViewById(R.id.BtnBa);

        EtName.setText(Name);
        EtMass.setText(totalgames);
    }

    @Override
    public void onClick(View view) {
        if (view == BtnSend) {
            if (EtPho.getText().toString().equals("")) {
                Toast.makeText(this, "please enter a phone number", Toast.LENGTH_SHORT).show();
            } else {

                if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) !=
                        PackageManager.PERMISSION_GRANTED) {

                    if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS))
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},
                                MY_PERMISSIONS_REQUEST_SEND_SMS);
                    else
                        Toast.makeText(getApplicationContext(), "you do not have permission", Toast.LENGTH_LONG).show();
                } else
                    sendSMS();
            }
        }
        if (view == BtnPick)
        {
            Intent i = new Intent();
            i.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
            i.setAction(Intent.ACTION_PICK);
            startActivityForResult(i, PICK_CONTACT_REQUEST);
        }
        if(view==BtnBa)
        {
            Intent Stati = new Intent(this, Statistic.class);
            startActivity(Stati);
        }

    }

    private void sendSMS() {
        SharedPreferences myPref = getSharedPreferences("myPref", MODE_PRIVATE);
        String Name = myPref.getString("userName", "");
        String phoNum = EtPho.getText().toString();
                           /////////////&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
        String messege = (EtMass.getText().toString())+"==="+Name;
        SmsManager smsMngr = SmsManager.getDefault();
        smsMngr.sendTextMessage(phoNum, null,messege,null,null);

        Toast.makeText(getApplicationContext(),"SMS sent...", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions,  int[] grantResults) {

        if (requestCode == MY_PERMISSIONS_REQUEST_SEND_SMS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                sendSMS();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Failed sending SMS", Toast.LENGTH_LONG).show();
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_CONTACT_REQUEST && resultCode == RESULT_OK) {
            Cursor cursor = null;
            try {
                String phone, name;
                Uri uri = data.getData();
                cursor = getContentResolver().query(uri, null, null, null, null);

                cursor.moveToFirst();
                int indexPhone = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
//                int indexName = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);

                phone = cursor.getString(indexPhone);
//                name = cursor.getString(indexName);
                EtPho.setText(phone);
//                EtName.setText(name);

            } catch (Exception s) {
                s.printStackTrace();
            }
        }
    }
}





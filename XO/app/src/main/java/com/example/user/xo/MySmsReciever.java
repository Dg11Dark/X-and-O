package com.example.user.xo;

import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.EditText;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;

public class MySmsReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

       final SharedPreferences myPref = context.getSharedPreferences("myPref", MODE_PRIVATE);

        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED"))
        {
            Toast.makeText(context, "Got SMS...", Toast.LENGTH_LONG).show();
            Bundle bundle = intent.getExtras();
            Object[] smsRes = (Object[]) bundle.get("smsRes");
            String smsPhoneNum="", smsInfo="";
            for (int i=0; i<smsRes.length;i++)
            {
                SmsMessage smsMsg = SmsMessage.createFromPdu((byte[]) smsRes[i]);
                smsPhoneNum = smsMsg.getDisplayOriginatingAddress();
                smsInfo += smsMsg.getDisplayMessageBody()+":";
            }

            Toast.makeText(context, "Phone:"+smsPhoneNum+" Text:"+smsInfo, Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor editor=myPref.edit();
            editor.putString("phone", smsPhoneNum);
            editor.putString("smsInfo", smsInfo);
            editor.apply();


        }

    }

    }


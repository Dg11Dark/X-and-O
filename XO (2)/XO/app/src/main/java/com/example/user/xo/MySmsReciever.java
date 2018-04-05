package com.example.user.xo;

import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class MySmsReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED"))
        {
            Toast.makeText(context, "Got SMS...", Toast.LENGTH_LONG).show();
            Bundle bundle = intent.getExtras(); // receive all the info from the intent
            Object[] pdus = (Object[]) bundle.get("pdus"); // get the list of SMS' details
            // now we'll prepare 2 strings of the sms number and the sms-message-body
            String smsPhoneNum="", smsInfo="";
            for (int i=0; i<pdus.length;i++)
            {
                SmsMessage smsMsg = SmsMessage.createFromPdu((byte[]) pdus[i]);
                smsPhoneNum = smsMsg.getDisplayOriginatingAddress();
                smsInfo += smsMsg.getDisplayMessageBody()+",";
            }
            Toast.makeText(context, "Phone:"+smsPhoneNum+" Text:"+smsInfo, Toast.LENGTH_SHORT).show();
        }

        //throw new UnsupportedOperationException("Not yet implemented");
    }
}

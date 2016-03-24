package com.locationalbum.locationalbum.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

/**
 * @author seogangmin
 * @version 0.0.1 2016. 3. 24. 생성
 */
public class CameraReceiver extends BroadcastReceiver {
    private String TAG = this.getClass().getName();

    private String mImgUri;
    private StringBuilder mSb = new StringBuilder();

    public void onReceive(Context context, Intent intent) {
        Uri uri = intent.getData();
        mImgUri = uri.toString();

        Log.d(TAG, this.toString());
        Toast.makeText(context, "ImageUri : ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public String toString() {
        addMessage("mImgUri >> " + mImgUri);
        return mSb.toString();
    }

    private StringBuilder addMessage(String msg){
        mSb.append(msg);
        mSb.append("\n");
        return mSb;
    }
}

package com.sevenbits11.demosdk;

import android.content.Context;
import android.widget.Toast;

public class DemoSdkToast {

    public static void s(Context c, String message){

        Toast.makeText(c,message, Toast.LENGTH_SHORT).show();

    }
}

package com.example.myapptp2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Login extends AppCompatActivity {

    public static final int CALL_Perm = 1;
    private Intent i;
    String newString;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString("PHONE_NUM");
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("PHONE_NUM");
        }



    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    public void callPhone(View view) {

        i = new Intent(Intent.ACTION_CALL);
        i.setData(Uri.parse("tel:" + newString));
        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale((Manifest.permission.CALL_PHONE))) {
                Log.i("callperm", "you should ");
            } else {
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, CALL_Perm);
            }
        }else{
            startActivity(i);
        }
    }

    public void button1_click(View view){
        onBackPressed();
    }

}
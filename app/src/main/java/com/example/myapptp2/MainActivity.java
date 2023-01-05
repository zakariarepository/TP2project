package com.example.myapptp2;

import androidx.annotation.NonNull;
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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int CALL_Perm = 1;
    private Intent i;

    EditText inputPhone;

    EditText inputC1;

    EditText inputC2;

    String resultString;

    static int number1;

    static int number2;

    int resultInt;




    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState != null) {
            // Restore value of members from saved state
            number1 = Integer.parseInt(savedInstanceState.getString("c1"));
            number2 = Integer.parseInt(savedInstanceState.getString("c2"));

        } else {
            // Probably initialize members with default values for a new instance
            number1 = 0;
        }



        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                resultString = null;

            } else {
                resultString= extras.getString("RESULT_NUM");
            }
        } else {
            resultString= (String) savedInstanceState.getSerializable("RESULT_NUM");

        }

        if(resultString!=null){




            Log.i("sd",resultString);

            Log.i("sd",Integer. toString(number1));
            Log.i("sd",Integer. toString(number2));




            resultInt= Integer.parseInt(resultString);

            if(number1+number2 == resultInt){
                Log.i("sd","horaaaaaaaaaaaaaaaaaaaaaaaay");
                String url = "https://emi.ac.ma";
                Uri webpage = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }

        }





    }



    public void toLogin(View view){
        Intent intent=new Intent(this, Login.class);
        inputPhone = (EditText) findViewById(R.id.edittextphone);
        intent.putExtra("PHONE_NUM", String.valueOf(inputPhone.getText()));
        startActivity (intent);
    }//

    public void toCheck(View view){
        Intent intent=new Intent(this, Check.class);
        inputC1 = (EditText) findViewById(R.id.entier1);
        inputC2 = (EditText) findViewById(R.id.entier2);
        intent.putExtra("N_1", String.valueOf(inputC1.getText()));
        intent.putExtra("N_2", String.valueOf(inputC2.getText()));
        startActivity (intent);
    }//


    public void toPerso(View view){
        Intent intent=new Intent(this, ActivitePerso.class);
        startActivity (intent);
    }//

    //Intent.ACTION_VIEW
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void openWebPage(View view) {
        String url = "https://emi.ac.ma";
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }





    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==CALL_Perm)
            if(grantResults.length>0)
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    //startActivity(i);
                    Toast.makeText(this, "GRANTED CALL", Toast.LENGTH_SHORT).show();
                }else{
                    Log.i("call","perm denied");
                }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("c1",String.valueOf(inputC1.getText()));
        outState.putString("c2",String.valueOf(inputC2.getText()));
    }
}

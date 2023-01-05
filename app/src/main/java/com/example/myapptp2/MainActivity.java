package com.example.myapptp2;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
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

    EditText inputPhone;  // le numero de telephone

    EditText inputC1;   // pour challenge 1
    int number1;

    EditText inputC2;    // challenge 2
    int number2;

    String resultString;    //pour recuperer le resultet de chack
    int resultInt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    }

    public void toLogin(View view){
        //Intent intent=new Intent(this, Login.class);
        Intent intent = new Intent();
        intent.setAction("login.ACTION");
        inputPhone = (EditText) findViewById(R.id.edittextphone);
        intent.putExtra("PHONE_NUM", String.valueOf(inputPhone.getText()));
        startActivity (intent);
    }//

    public void toCheck(View view){
        Intent intent=new Intent(this, Check.class);


        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        inputC1 = findViewById(R.id.entier1);
        inputC2 = findViewById(R.id.entier2);


        intent.putExtra("N_1", String.valueOf(inputC1.getText()));
        intent.putExtra("N_2", String.valueOf(inputC2.getText()));

       if(inputC1.getText().toString().matches("") || inputC2.getText().toString().matches("")){
           Context context = getApplicationContext();
           CharSequence text = "veuiller remplir tous les champs";
           int duration = Toast.LENGTH_SHORT;

           Toast toast = Toast.makeText(context, text, duration);
           toast.show();
       }else{
           Log.i("there",inputC1.getText().toString());
           Log.i("it","this is else");
           someActivityResultLauncher.launch(intent);
           number1= Integer.parseInt(String.valueOf(inputC1.getText()));
           number2= Integer.parseInt(String.valueOf(inputC2.getText()));
       }
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


    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == 78) {
                        // There are no request codes
                        Intent data = result.getData();
                        if( data != null){
                            resultString = data.getStringExtra("RESULT_NUM");

                            Log.i("number 1 on resume sat",Integer. toString(number1));
                            Log.i("number 2 on resume sat",Integer. toString(number2));
                            Log.i("reeslut is",resultString);

                            resultInt= Integer.parseInt(resultString);

                            if(number1+number2 == resultInt){
                                Log.i("sd","horaaaaaaaaaaaaaaaaaaaaay");
                                String url = "https://emi.ac.ma";
                                Uri webpage = Uri.parse(url);
                                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                                if (intent.resolveActivity(getPackageManager()) != null) {
                                    startActivity(intent);
                                }
                            }
                        }

                    }else {

                        Context context = getApplicationContext();
                        CharSequence text = "l’opération a été annulée";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
            });

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==CALL_Perm)
            if(grantResults.length>0)
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "GRANTED CALL", Toast.LENGTH_SHORT).show();
                }else{
                    Log.i("call","perm denied");
                }
    }

}

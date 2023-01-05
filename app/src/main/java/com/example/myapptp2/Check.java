package com.example.myapptp2;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Check extends AppCompatActivity {


    String number1;
    String number2;
    TextView textView1;
    TextView textView2;

    EditText inputResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                number1= null;
                number2= null;
            } else {
                number1= extras.getString("N_1");
                number2= extras.getString("N_2");
            }
        } else {
            number1= (String) savedInstanceState.getSerializable("N_1");
            number2= (String) savedInstanceState.getSerializable("N_2");
        }

        textView1 = (TextView) findViewById(R.id.textView9);
        textView2 = (TextView) findViewById(R.id.textView10);

        textView1.setText(number1);
        textView2.setText(number2);


    }

    public void button1_click(View view){

        Intent intent=new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

        setResult(79,intent);
        Check.super.onBackPressed();

    }

    public void toMain(View view){
        Intent intent=new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        inputResult = (EditText) findViewById(R.id.resultid);
        intent.putExtra("RESULT_NUM", String.valueOf(inputResult.getText()));


        setResult(78,intent);
        Check.super.onBackPressed();

    }//

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
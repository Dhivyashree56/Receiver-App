package com.first.receiverapp;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView text;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.TextBoxID);
        button = findViewById(R.id.ButtonID);

        //Intent receives the TEXT data from other apps with ACTION_VIEW
        Intent receiveIntent = getIntent();
        String sharedText = receiveIntent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null) {
            text.setText(sharedText);
            Toast.makeText(getBaseContext(), "DATA IS RECEIVED BY READ APP", Toast.LENGTH_LONG).show();
        }

        //Return Back to the called app with a greeting message
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String appendText = "Hello, "+sharedText;

                //Intent returns the result back to the called intent
                Intent resultIntent = new Intent();
                resultIntent.putExtra("result",appendText);
                setResult(RESULT_OK,resultIntent);
                Toast.makeText(getBaseContext() , "WELCOME MESSAGE IS SENT BACK", Toast.LENGTH_LONG ).show();
                finish();
            }
        });
    }
}
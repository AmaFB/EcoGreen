package com.example.ecogreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Main extends AppCompatActivity {
    Button Reg0, Log0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Reg0 = findViewById(R.id.reg1);
        Log0 = findViewById(R.id.login1);
        //move to Reg page
        Reg0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main.this,Reg.class);
                startActivity(i);
            }
        });
        //Move to Login page
        Log0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(Main.this, Login.class);
                startActivity(j);

            }
        });
    }
}
package com.example.ecogreen;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Sustainable extends AppCompatActivity {
    TextView txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8,txt9,txt10;
    ImageButton img1,img2,img3,img4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alternatives);
        img1 = findViewById(R.id.imgActs);
        img2 = findViewById(R.id.imgFindS);
        img3 = findViewById(R.id.imgSuss);
        img4 = findViewById(R.id.imgPers);
         img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Sustainable.this, Activities.class);
                startActivity(i);
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Sustainable.this, FindMarket.class);
                startActivity(i);
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Sustainable.this, Sustainable.class);
                startActivity(i);
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Sustainable.this, Personal.class);
                startActivity(i);
            }
        });
        txt1 = findViewById(R.id.txt1_1);
        txt1.setMovementMethod(LinkMovementMethod.getInstance());

        txt2 = findViewById(R.id.txt1_2);
        txt2.setMovementMethod(LinkMovementMethod.getInstance());

        txt3 =findViewById(R.id.txt1_3);
        txt3.setMovementMethod(LinkMovementMethod.getInstance());

        txt4 = findViewById(R.id.txt2_1);
        txt4.setMovementMethod(LinkMovementMethod.getInstance());

        txt5 = findViewById(R.id.txt2_2);
        txt5.setMovementMethod(LinkMovementMethod.getInstance());

        txt6 = findViewById(R.id.txt2_3);
        txt6.setMovementMethod(LinkMovementMethod.getInstance());

        txt7 = findViewById(R.id.txt3_1);
        txt7.setMovementMethod(LinkMovementMethod.getInstance());

        txt8 = findViewById(R.id.txt3_2);
        txt8.setMovementMethod(LinkMovementMethod.getInstance());

        txt9 = findViewById(R.id.txt3_3);
        txt9.setMovementMethod(LinkMovementMethod.getInstance());

        txt10 =findViewById(R.id.txt4);
        txt10.setMovementMethod(LinkMovementMethod.getInstance());
    }
}

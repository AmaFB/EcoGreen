package com.example.ecogreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Activities extends AppCompatActivity {
    ImageButton img1,img2,img3,img4;
    FirebaseDatabase database ;
    DatabaseReference reference;
    EditText EDt1;
    Member member;
    CheckBox chBox1, chBox2, chBox3, chBox4, chBox5, chBox6, chBox7, chBox8, chBox9;
    Button btnSave;
    int i = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities);
        img1 = findViewById(R.id.imgActs);
        img2 = findViewById(R.id.imgFinds);
        img3 = findViewById(R.id.imgSuss);
        img4 = findViewById(R.id.imgPers);
        EDt1 = findViewById(R.id.editdet);
        reference = database.getInstance().getReference().child("Activities");

        member = new Member();
        btnSave = findViewById(R.id.Save_Activity);

        chBox1 = findViewById(R.id.chBox1);
        chBox2 = findViewById(R.id.chBox2);
        chBox3 = findViewById(R.id.chBox3);
        chBox4 = findViewById(R.id.chBox4);
        chBox5 = findViewById(R.id.chBox5);
        chBox6 = findViewById(R.id.chBox6);
        chBox7 = findViewById(R.id.chBox7);
        chBox8 = findViewById(R.id.chBox8);
        chBox9 = findViewById(R.id.chBox9);


        String Activity1 = "Recycling";
        String Activity2 = "CO2Reduced";
        String Activity3 = "PlasticReduced";
        String Activity4 = "WaterSaved";
        String Activity5 = "OrganicUsed";
        String Activity6 = "LandSaved";
        String Activity7 = "Walking";
        String Activity8 = "Biking";
        String Activity9 = "Bus";
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Activities.this, Activities.class);
                startActivity(i);
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Activities.this, FindMarket.class);
                startActivity(i);
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Activities.this, Sustainable.class);
                startActivity(i);
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Activities.this, Personal.class);
                startActivity(i);
            }
        });
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    i = (int)dataSnapshot.getChildrenCount();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (chBox1.isChecked())
                {
                   member.setActivity1(Activity1);
                   reference.child(String.valueOf(i+1)).setValue(member);
                }
               else {}

                if (chBox2.isChecked())
                {
                    member.setActivity2(Activity2);
                    reference.child(String.valueOf(i+1)).setValue(member);
                }  else {}
                if (chBox3.isChecked())
                {
                    member.setActivity3(Activity3);
                    reference.child(String.valueOf(i+1)).setValue(member);
                }  else {}

                if (chBox4.isChecked())
                {
                    member.setActivity4(Activity4);
                    reference.child(String.valueOf(i+1)).setValue(member);
                }  else {}

                if (chBox5.isChecked())
                {
                    member.setActivity5(Activity5);
                    reference.child(String.valueOf(i+1)).setValue(member);
                }  else {}

                if (chBox6.isChecked())
                {
                    member.setActivity6(Activity6);
                    reference.child(String.valueOf(i+1)).setValue(member);

                }  else {}

                if (chBox7.isChecked())
                {
                    member.setActivity7(Activity7);
                    reference.child(String.valueOf(i+1)).setValue(member);
                }  else {}

                if (chBox8.isChecked())
                {
                    member.setActivity8(Activity8);
                    reference.child(String.valueOf(i+1)).setValue(member);
                }  else {}
                if (chBox9.isChecked())
                {
                    member.setActivity9(Activity9);
                    reference.child(String.valueOf(i+1)).setValue(member);
                }  else {}
                member.setActivity10(EDt1.getText().toString());
                reference.child(String.valueOf(i+1)).setValue(member);
                Toast.makeText(Activities.this, "Activities has been Saved", Toast.LENGTH_SHORT).show();
                }
        });
    }
}


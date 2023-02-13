package com.example.ecogreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Achievement extends AppCompatActivity {
    ListView Ls;
    ImageButton img1,img2,img3,img4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.achievement);
        img1 = findViewById(R.id.imgActs3);
        img2 = findViewById(R.id.imgFinds3);
        img3 = findViewById(R.id.imgSuss3);
        img4 = findViewById(R.id.imgPers3);

       final ArrayList<String> List = new ArrayList<>();
       final ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, List);
        Ls = findViewById(R.id.ListView);
        Ls.setAdapter(adapter);
          DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Activities");
          reference.addValueEventListener(new ValueEventListener() {
          @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Member m = snapshot.getValue(Member.class);
                    String t ="Activity : "+ m.getActivity1() +"\n"+ "Activity : "+ m.getActivity2() +"\n"+ "Activity : "+ m.getActivity3()
                    +"\n" + "Activity : "+ m.getActivity4() + "\n" + "Activity : "+ m.getActivity5() + "\n" +"Activity : "+ m.getActivity6() + "\n" +
                            "Activity : "+ m.getActivity7() + "\n" + "Activity : "+ m.getActivity8() + "\n" +"Activity : "+ m.getActivity9()
                            + "\n" +"Details : "+ m.getActivity10();
                    List.add(t);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Achievement.this, Activities.class);
                startActivity(i);
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Achievement.this, FindMarket.class);
                startActivity(i);
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Achievement.this, Sustainable.class);
                startActivity(i);
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Achievement.this, Personal.class);
                startActivity(i);
            }
        });
    }
}

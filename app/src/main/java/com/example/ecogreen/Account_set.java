package com.example.ecogreen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class Account_set extends AppCompatActivity {
    private static final String TAG = "Account_set";
    ImageButton img1,img2,img3,img4;
    EditText displayNameEditText , EditE, EditN ;
    Button updateProfileButton;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FirebaseUser user;
    StorageReference storageReference;
    ProgressBar progressBar;
    String DISPLAY_NAME = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_set);
        img1 = findViewById(R.id.imgActs);
        img2 = findViewById(R.id.imgFindS);
        img3 = findViewById(R.id.imgSuss);
        img4 = findViewById(R.id.imgPers);
        displayNameEditText = findViewById(R.id.ChangeName);
        EditE = findViewById(R.id.ChangeEmail);
        EditN = findViewById(R.id.ChangePhoneNo);
        updateProfileButton = findViewById(R.id.Save_acc_set);
        progressBar = findViewById(R.id.progressBar);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Account_set.this, Activities.class);
                startActivity(i);
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Account_set.this, FindMarket.class);
                startActivity(i);
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Account_set.this, Sustainable.class);
                startActivity(i);
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Account_set.this, Personal.class);
                startActivity(i);
            }
        });

        Intent data = getIntent();
        final String fullName = data.getStringExtra("Name");
        String email = data.getStringExtra("Email");
        String phone = data.getStringExtra("Phone Number");

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        user = fAuth.getCurrentUser();
        storageReference = FirebaseStorage.getInstance().getReference();
        updateProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(displayNameEditText.getText().toString().isEmpty() || EditE.getText().toString().isEmpty() || EditN.getText().toString().isEmpty()){
                    Toast.makeText(Account_set.this, "One or Many fields are empty.", Toast.LENGTH_SHORT).show();
                    return;
                }
                final String email = EditE.getText().toString();
                user.updateEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        DocumentReference docRef = fStore.collection("Users").document(user.getUid());
                        Map<String,Object> edited = new HashMap<>();
                        edited.put("Email",email);
                        edited.put("Name",displayNameEditText.getText().toString());
                        edited.put("Phone Number",EditN.getText().toString());
                        docRef.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Account_set.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),Personal.class));
                                finish();
                            }
                        });
                        Toast.makeText(Account_set.this, "Email is changed.", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Account_set.this,   e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        EditE.setText(email);
        displayNameEditText.setText(fullName);
        EditN.setText(phone);
        Log.d(TAG, "onCreate: " + fullName + " " + email + " " + phone);
    }
        }


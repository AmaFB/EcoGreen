package com.example.ecogreen;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Reg extends AppCompatActivity {
    public static final String TAG = "TAG";
    Button UserSignUpBtn;
    EditText  NameSignup, UserEmailSIgnup ,UserPasswordSignup, UserPhoneSIgnup ,BirthSignup;
    Integer v0=0;
    ProgressDialog loadingBar;
    String userID;
    FirebaseAuth mAuth;
    FirebaseFirestore Fstore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg);
        UserSignUpBtn = findViewById(R.id.createAccount);
        NameSignup = findViewById(R.id.NameOfUser);
        UserEmailSIgnup = findViewById(R.id.Email);
        UserPasswordSignup = findViewById(R.id.Password);
        UserPhoneSIgnup = findViewById(R.id.PhoneNo);
        BirthSignup = findViewById(R.id.Age);

        loadingBar = new ProgressDialog(this);
        //connect to database
        Fstore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        UserSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = NameSignup.getText().toString();
                String UserEmail = UserEmailSIgnup.getText().toString();
                String UserPassword = UserPasswordSignup.getText().toString();
                String UserPhone = UserPhoneSIgnup.getText().toString();
                String BirthDate = BirthSignup.getText().toString();
                //Registration method......
                RegistrationUser(UserEmail,UserPassword,UserPhone,Name,BirthDate);
            }
        });
    }
    private void RegistrationUser( String userEmail, String userPassword,String UserPhone, String Name,String BirthDate)
    {
        if(TextUtils.isEmpty(userEmail)||TextUtils.isEmpty(userPassword))
        {
            Toast.makeText(Reg.this, "Please Fill All of your Information...", Toast.LENGTH_LONG).show();
        }
        if(userPassword.length() <= 5 || userPassword.length() >=  16 ){
            UserPasswordSignup.setError("Password Must be  6-15 Characters");
            return;
        }
        if(TextUtils.isEmpty(Name)||TextUtils.isEmpty(UserPhone))
        {
            Toast.makeText(Reg.this, "Please Fill All of your Information...", Toast.LENGTH_LONG).show();
        }
        if(Name.length() <= 3 || Name.length() >=  10 ){
            NameSignup.setError("Name Must be  3-10 Characters");
            return;
        }
        String t =BirthSignup.getText().toString();
        v0=Integer.parseInt(t);
        if(v0 <= 17 || v0 >=91 ){
            BirthSignup.setError("Age Must be 18-90 years");
            return;
        }
        if(TextUtils.isEmpty(BirthDate))
        {
            Toast.makeText(Reg.this, "Please Fill All of your Information...", Toast.LENGTH_LONG).show();
        }
        else
        {
            loadingBar.show(this, " Registration", "Wait while we Finish...");
            loadingBar.setCancelable(false);
            // register  in the application
            mAuth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(Reg.this,new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    //check if the task done successfully
                    if (task.isSuccessful()) {
                        Toast.makeText(Reg.this, "Registered Successfully...", Toast.LENGTH_LONG).show();
                        //Upload User information to database.
                        userID = mAuth.getCurrentUser().getUid();
                        DocumentReference documentReference = Fstore.collection("Users").document(userID);
                        Map<String,Object> User = new HashMap<>();
                        User.put("Name",Name);
                        User.put("Email",userEmail);
                        User.put("Password",userPassword);
                        User.put("Phone Number",UserPhone);
                        User.put("Age",BirthDate);
                        documentReference.set(User).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override  //after the upload done successfully
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "onSuccess: User Profile Created Successfully."+ userID);
                            }
                        });
                        //to make the loading bar disappear
                        finish();
                    } else {
                        Toast.makeText(Reg.this, "Registration Failed...", Toast.LENGTH_LONG).show();
                        loadingBar.dismiss();
                    }
                }
            });
        }
    }
}


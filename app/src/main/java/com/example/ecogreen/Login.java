package com.example.ecogreen;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class Login extends AppCompatActivity {
    Button UserLoginButton;
    EditText UserNameLogin, UserPasswordLogin;
    FirebaseAuth LAuth;
    ProgressDialog loadingBar;
    TextView mCreateBtn,forgotTextLink;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        UserLoginButton = findViewById(R.id.Login0);
        UserNameLogin = findViewById(R.id.Username0);
        UserPasswordLogin = findViewById(R.id.Password0);
        forgotTextLink = findViewById(R.id.forgotPassword0);
        mCreateBtn = findViewById(R.id.createText);
        progressBar = findViewById(R.id.progressBar);

        //loading bar and database
        loadingBar = new ProgressDialog(this);
        LAuth = FirebaseAuth.getInstance();
        UserLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = UserNameLogin.getText().toString().trim();
                String password = UserPasswordLogin.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    UserNameLogin.setError("Email is Required.");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    UserPasswordLogin.setError("Password is Required.");
                    return;
                }

                if(password.length() < 6 || password.length() >=  15){
                    UserPasswordLogin.setError("Password Must be 6 - 15 Characters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                // authenticate the user

                LAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Personal.class));
                        }else {
                            Toast.makeText(Login.this, "User Sing in Failed..." + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }

                    }
                });

            }
        });
        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Reg.class));
            }
        });
            forgotTextLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final EditText resetMail = new EditText(v.getContext());
                    final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                    passwordResetDialog.setTitle("Reset Password ?");
                    passwordResetDialog.setMessage("Enter Your Email To Received Reset Link.");
                    passwordResetDialog.setView(resetMail);

                    passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // extract the email and send reset link
                            String mail = resetMail.getText().toString();
                            LAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(Login.this, "Reset Link Sent To Your Email.", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Login.this, "Error ! Reset Link is Not Sent" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    });
                    passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // close the dialog
                        }
                    });
                    passwordResetDialog.create().show();
                }
            });

        }
    }
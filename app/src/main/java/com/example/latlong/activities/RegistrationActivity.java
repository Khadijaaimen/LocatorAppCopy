package com.example.latlong.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.latlong.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RegistrationActivity extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText nName, nEmail, nPassword;
    Button nRegisterBtn;
    TextView nClickLogin;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;
    ProgressBar progressBar;
    String userId;
    DatabaseReference mRoofRef = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        nName = findViewById(R.id.editName);
        nEmail = findViewById(R.id.editEmailAddress);
        nPassword = findViewById(R.id.editPassword);
        nRegisterBtn = findViewById(R.id.signUpBtn);
        nClickLogin = findViewById(R.id.alreadyCreatedAccount);

        progressBar = findViewById(R.id.progressBarRegister);
        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        nRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                String name = nName.getText().toString().toLowerCase().trim();
                String email = nEmail.getText().toString().trim();
                String password = nPassword.getText().toString().trim();

                if (TextUtils.isEmpty(name)) {
                    nName.setError("Required Field!");
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    nEmail.setError("Required Field!");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    nPassword.setError("Required Field!");
                    return;
                } else if (password.length() < 8) {
                    nPassword.setError("Password must have more than 6 characters!");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegistrationActivity.this, "User Added", Toast.LENGTH_SHORT).show();
                            userId = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();
                            DocumentReference documentReference = firestore.collection("users").document(userId);
                            Map<String, Object> user = new HashMap<>();
                            user.put("name", name);
                            user.put("email", email);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG, "onSuccess: Profile is created for " + userId);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: " + e.toString());
                                }
                            });
                            startActivity(new Intent(getApplicationContext(), LatLongActivity.class));
                        } else {
                            Toast.makeText(RegistrationActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
        nClickLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });
    }
}
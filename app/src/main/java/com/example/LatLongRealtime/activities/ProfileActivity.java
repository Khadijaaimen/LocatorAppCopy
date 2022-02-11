package com.example.LatLongRealtime.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.LatLongRealtime.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import java.util.Objects;

public class ProfileActivity extends AppCompatActivity  {

    TextView mName, mEmail, mPhone;
    //    ImageView addImage;
    FirebaseAuth firebaseAuth;
    String userID, personName, personEmail, personPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile);

        mName = findViewById(R.id.nameProfile);
        mEmail = findViewById(R.id.emailProfile);
        mPhone = findViewById(R.id.phoneProfile);
//        addImage = findViewById(R.id.imageAddImage);
        firebaseAuth = FirebaseAuth.getInstance();

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if (acct != null) {
            personName = acct.getDisplayName();
            personEmail = acct.getEmail();
//            personPhone = acct.get;
        }


        userID = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();

    }
}
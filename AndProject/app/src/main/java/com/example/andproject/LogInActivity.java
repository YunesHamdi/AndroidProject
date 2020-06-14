package com.example.andproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.andproject.R;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class LogInActivity extends AppCompatActivity {


    private static final int RC_SIGN_IN = 1;
    private static final int RC_SIGN_OUT = 2;

    private FirebaseAuth mAuth;
    SharedPreferences appIntro = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // run the intro activity if it is first time running the app
        appIntro = getSharedPreferences("hasRunBefore_appIntro", 0);
        Boolean hasRun = appIntro.getBoolean("hasRun_appIntro", false);
        if (!hasRun) {
            Intent intent = new Intent(this, MyAppIntro.class);
            startActivity(intent);
        }

        SharedPreferences settings = getSharedPreferences("hasRunBefore_appIntro", 0);
        SharedPreferences.Editor edit = settings.edit();
        edit.putBoolean("hasRun_appIntro", true);
        edit.commit();
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_login);
    }


    public void createSignInIntent(View v) {
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.GoogleBuilder().build(),
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.PhoneBuilder().build()
        );

        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setLogo(R.drawable.logo)
                        .build(),
                RC_SIGN_IN);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                openMainActivity();
            } else {
                Toast.makeText(this, "You have not finished signing up", Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == RC_SIGN_OUT) {
            signOut();
        }
    }

    public void signOut() {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
    }

    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
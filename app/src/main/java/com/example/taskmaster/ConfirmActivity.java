package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.amplifyframework.core.Amplify;

public class ConfirmActivity extends AppCompatActivity {

    private static final String TAG = "VerificationActivity";
    private String username;
    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        EditText editText = findViewById(R.id.confirmCode);
        Button confirm = findViewById(R.id.confirm);

        Intent intent = getIntent();
        username = intent.getExtras().getString("username", "");
        email = intent.getExtras().getString("email", "");
        password = intent.getExtras().getString("password", "");

        confirm.setOnClickListener(view -> verification(email, editText.getText().toString()));
    }

    void verification(String email, String confirmationNumber) {
        Amplify.Auth.confirmSignUp(
                email,
                confirmationNumber,
                success -> {
                    Log.i(TAG, "verification: succeeded" + success.toString());
                    Intent goToSignIn = new Intent(ConfirmActivity.this, LoginActivity.class);
                    goToSignIn.putExtra("email", email);
                    startActivity(goToSignIn);

//                    silentSignIn(username, password);
                },
                error -> {
                    Log.e(TAG, "verification: failed" + error.toString());
                });
    }

    void silentSignIn(String email, String password) {
        Amplify.Auth.signIn(
                email,
                password,
                success -> {
                    Log.i(TAG, "signIn: worked " + success.toString());
                },
                error -> Log.e(TAG, "signIn: failed" + error.toString()));
    }
}
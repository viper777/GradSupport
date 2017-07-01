package com.example.a21701125.myapplication1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private EditText email,password;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ProgressDialog loginDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        mAuth=FirebaseAuth.getInstance();
        loginDialog =new ProgressDialog(this);
        loginDialog.setMessage("Login..");
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                    finish();
                }
            }
        };
    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    public void login(View view) {
        loginDialog.show();
        mAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    loginDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }
                loginDialog.dismiss();
            }
        });
    }
    public void register(View view) {
        startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
        finish();
    }
}

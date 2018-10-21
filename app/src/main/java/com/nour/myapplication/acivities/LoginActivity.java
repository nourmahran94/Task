package com.nour.myapplication.acivities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.nour.myapplication.R;
import com.nour.myapplication.util.Util;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextEmail , editTextPassword ;
    private FirebaseAuth mAuth ;
    private ProgressBar progressBar ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        intView ();

    }

    private void intView() {
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword =findViewById(R.id.editTextPassword);
        mAuth =FirebaseAuth.getInstance();

        findViewById(R.id.buttonLogin).setOnClickListener(this);
        findViewById(R.id.buttonSignUp).setOnClickListener(this);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
    }


    private void UserLogin() {
        if (Util.isNetworkAvailable(LoginActivity.this)) {

            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();

            if (email.isEmpty()) {
                editTextEmail.setError(getString(R.string.email_is_empty));
                editTextEmail.requestFocus();
                return;

            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                editTextEmail.setError(" Please enter a valid email");
                editTextEmail.requestFocus();
                return;
            }

            if (password.isEmpty()) {
                editTextPassword.setError(getString(R.string.password_is_empty));
                editTextPassword.requestFocus();
                return;
            }
            if (password.length() > 6) {
                editTextPassword.setError("Minimum length of password should be 6 ");
                editTextPassword.requestFocus();
                return;
            }

            progressBar.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressBar.setVisibility(View.GONE);
                    if (task.isSuccessful()) {
                        Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                        i.addFlags((Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        startActivity(i);

                    } else {
                        Util.showDialog(LoginActivity.this, "Error", "Do not have acount ? Please Sign Up");

                    }
                }

            });
        } else { Util.showDialog(LoginActivity.this , " Error " , " Network error has occurred");

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonLogin:
                UserLogin();
                break;
            case R.id.buttonSignUp:
                startActivity(new Intent(this, ResgisterActivity.class));
                break;

    }
} }
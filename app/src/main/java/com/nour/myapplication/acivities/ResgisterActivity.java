package com.nour.myapplication.acivities;

import android.content.Context;
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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.nour.myapplication.R;
import com.nour.myapplication.util.Util;

public class ResgisterActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextEmail , editTextPassword , editTextConfirmedPassword ;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resgister);

        intView ();

    }

    private void intView() {
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);
        editTextConfirmedPassword = findViewById(R.id.editTextConfirmPassword);
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.buttonSignUp).setOnClickListener(this);
        findViewById(R.id.buttonLogin).setOnClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);
    }

    private void registerUser () {


        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()){
            editTextEmail.setError(getString(R.string.email_is_empty));
            editTextEmail.requestFocus();
            return;

        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError(" Please enter a valid email");
            editTextEmail.requestFocus();
            return;
        }


        if (password.isEmpty()){
            editTextPassword.setError(getString(R.string.password_is_empty));
            editTextPassword.requestFocus();
            return;
        }
        if (password.length() > 6 ) {
            editTextPassword.setError("Minimum length of password should be 6 ");
            editTextPassword.requestFocus();
            return;
        } if  (editTextPassword.getText().toString().equals(editTextConfirmedPassword.getText().toString()))

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email , password ).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    finish();

                    Intent i = new Intent(ResgisterActivity.this , HomeActivity.class);
                    i.addFlags((Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    startActivity(i);

                } else  {
                    Util.showDialog(ResgisterActivity.this , " Error" , " You are already registered");

                   }
                }
            });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.buttonSignUp :
                registerUser () ;
                break;

            case R.id.buttonLogin:
                startActivity(new Intent(this,LoginActivity.class));
                break;
        }


    }
}




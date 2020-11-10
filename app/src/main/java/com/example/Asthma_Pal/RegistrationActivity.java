package com.example.Asthma_Pal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    private EditText Email, Password, ConPassword;
    private Button Register;
    private TextView Return;
    private FirebaseAuth UserAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        UserAuth = FirebaseAuth.getInstance();

        Password = findViewById(R.id.etPassword);
        ConPassword = findViewById(R.id.etConfirmPassword);
        Email = findViewById(R.id.etEmail);
        Register = findViewById(R.id.btnRegister);
        Return = findViewById(R.id.tvReturntoMenu);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                   String userPass = Password.getText().toString().trim();
                   String userEmail = Email.getText().toString().trim();

                   UserAuth.createUserWithEmailAndPassword(userEmail, userPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if(task.isSuccessful()) {
                               Toast.makeText(RegistrationActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                               Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                               startActivity(intent);
                               RegistrationActivity.this.finish();
                           }
                           else
                               Toast.makeText(RegistrationActivity.this, "Registration Unsuccessful", Toast.LENGTH_SHORT).show();
                       }
                   });
                }
            }
        });

        Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                LoginActivity.RA.finish();
                startActivity(intent);
                RegistrationActivity.this.finish();
            }
        });
    }

    private boolean validate(){
        boolean valid = false;

        String pass = Password.getText().toString();
        String conPass = ConPassword.getText().toString();
        String email = Email.getText().toString();

        if(email.isEmpty() || pass.isEmpty()){
            Toast.makeText(this, "Please enter an Email and Password", Toast.LENGTH_SHORT).show();
        }
        else if(conPass.isEmpty()){
            Toast.makeText(this, "Please enter Confirmed Password", Toast.LENGTH_SHORT).show();
        }
        else if(!pass.equals(conPass)){
            Toast.makeText(this, "Password and Confirmed Password do not match", Toast.LENGTH_SHORT).show();
        }
        else
            valid = true;

        return valid;
    }
}
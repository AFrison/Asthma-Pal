package com.AP.Asthma_Pal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    //Declare key variable
    private EditText Username, Password;
    private Button Login;
    private TextView Register;
    public static Activity RA;
    private FirebaseAuth LoginDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Connect to database
        LoginDatabase = FirebaseAuth.getInstance();

        //See if a User has already signed into the app
        FirebaseUser user = LoginDatabase.getCurrentUser();

        //If a user is already signed in then auto sign in
        if (user != null) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        // Reference to  this activity so that we can close inside other activities
        RA = this;

        //Initialize Variables to the components displayed on the activity
        Username = (EditText)findViewById(R.id.etUsername);
        Password = (EditText)findViewById(R.id.etPassword);
        Login = (Button)findViewById(R.id.btnLogin);
        Register = findViewById(R.id.tvRegister);

        //Listen for click on button
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Username.getText().toString(), Password.getText().toString());
            }
        });

        //Listen for click on text to take to registration
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(register);
            }
        });
    }

    private void validate(String enterUser, String enterPass){

        //Pull data from firebase and confirm that the credentials are correct then login or cancel
        LoginDatabase.signInWithEmailAndPassword(enterUser, enterPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                    Toast.makeText(LoginActivity.this, "Unrecognized user, Please Try again", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
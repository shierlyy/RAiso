package com.example.projectuxlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    ArrayList<GlobalVariable> userEmail = new ArrayList<GlobalVariable>();
    Button loginButton;
    EditText loginEmail, loginPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEmail = findViewById(R.id.emailTF);
        loginPassword = findViewById(R.id.passwordTF);
        loginButton = findViewById(R.id.loginbutton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = loginEmail.getText().toString();
                String password = loginPassword.getText().toString();

                if(TextUtils.isEmpty(loginEmail.getText().toString())){
                    Toast.makeText(LoginActivity.this, "Email must be filled", Toast.LENGTH_SHORT).show();
                }

                if (!loginEmail.getText().toString().trim().contains(".")) {
                    Toast.makeText(getApplicationContext(),"Email must contain '.'",Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(loginPassword.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "Password must be filled", Toast.LENGTH_SHORT).show();
                }
                else{
                    Integer check = 0;
                    for (GlobalVariable user : userEmail) {
                        if(loginEmail.equals(user.getEmail())){
                            check = 1;
                            break;
                        }
                    }
                    if (check == 1) {
                        Toast.makeText(LoginActivity.this, "Wrong credentials", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(LoginActivity.this, "You have signup successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("userLogin", loginEmail.getText().toString());
                        startActivity(intent);
                    }

                }


            }
        });

    }
}
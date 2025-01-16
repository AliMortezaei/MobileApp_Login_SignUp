package com.codingtutorials.loginsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username, password;
    TextView signupText;
    Button loginButton;
    DatabaseHelper databaseHelper ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.userNameText);
        password = findViewById(R.id.passwordText);
        loginButton = findViewById(R.id.loginButton);
        signupText = findViewById(R.id.textSignup);
        databaseHelper = new DatabaseHelper(this);

        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString().trim();
                String pass = password.getText().toString().trim();
                if(user.isEmpty() || pass.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
                }
                else {
                    String loggedInUserName = databaseHelper.checkLogin(user,pass);
                    if(loggedInUserName!=null)
                    {
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        intent.putExtra("USERNAME", loggedInUserName);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Invalid Username", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }
}
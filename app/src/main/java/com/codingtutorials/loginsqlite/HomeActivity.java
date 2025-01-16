package com.codingtutorials.loginsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    TextView userName;
    DatabaseHelper databaseHelper;
    Button logOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        userName = findViewById(R.id.textUserName);
        databaseHelper = new DatabaseHelper(this);
        logOut = findViewById(R.id.logOutButton);
        String user = getIntent().getStringExtra("USERNAME");

        userName.setText("Welcome "+ user);

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogOut();
            }
        });


    }

    @Override
    public void onBackPressed() {
        LogOut();
        super.onBackPressed();
    }

    private void LogOut() {

        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}
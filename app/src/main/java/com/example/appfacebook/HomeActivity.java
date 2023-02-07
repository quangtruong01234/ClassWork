package com.example.appfacebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent home = getIntent();
        String userName = home.getStringExtra("userName");
        ((TextView) findViewById(R.id.usernameHome)).setText("Welcome : " + userName);
    }
}
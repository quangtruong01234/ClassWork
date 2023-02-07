package com.example.appfacebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnLogin;
    private EditText userName, passwordLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textfile);

        btnLogin =(Button) findViewById(R.id.button);
        userName = findViewById(R.id.userName);
        passwordLogin = findViewById(R.id.password);
    }
    public void onLogin(View view){
        String userLogin = userName.getText().toString();
        String password = passwordLogin.getText().toString();

        if(userLogin.equals("admin") &&
                password.equals("123456")) {
            Intent home = new Intent(this, HomeActivity.class);
            home.putExtra("userName", userLogin);
            Toast.makeText(getApplicationContext(), "Logging...", Toast.LENGTH_LONG).show();
            startActivity(home);

        }else{
            Toast.makeText(this, "Wrong username or password!", Toast.LENGTH_SHORT).show();
            ((EditText) findViewById(R.id.password)).setText("");
            Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show();
        }
    }


}
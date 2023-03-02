package com.example.appfacebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private static final int REQUEST_PERMISSION_CODE = 1000;
    private RecyclerView rcvUser;
    private UserAdapter mUserAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//        Intent home = getIntent();
//        String userName = home.getStringExtra("userName");
//        ((TextView) findViewById(R.id.usernameHome)).setText("Welcome : " + userName);

        rcvUser = findViewById(R.id.rcv_user);
        mUserAdapter = new UserAdapter(this, getListUser());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        rcvUser.setLayoutManager(gridLayoutManager);

        rcvUser.setAdapter(mUserAdapter);
        getCallPhonePermission();

    }
    public void onProduct(View view){
        Intent product = new Intent(this,ProductActivity.class);
        startActivity(product);
    }

    private List<User> getListUser(){
        List<User> list =new ArrayList<>();
        list.add(new User(R.drawable.pharmacy, "pharmacy"));
        list.add(new User(R.drawable.gift, "Registry"));
        list.add(new User(R.drawable.cart_trolley, "cartwheel"));
        list.add(new User(R.drawable.clothing, "clothing"));

        list.add(new User(R.drawable.shoe, "shoes"));
        list.add(new User(R.drawable.bag, "accessories"));
        list.add(new User(R.drawable.baby_clothes, "baby"));
        list.add(new User(R.drawable.home, "home"));

        list.add(new User(R.drawable.grill, "patio & garden"));

        return list;
    }
    public void  getCallPhonePermission(){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            return;
        }

        if(checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(HomeActivity.this, "Granted", Toast.LENGTH_LONG).show();
        }else{
            if(shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)){

            }

            String[] permissions = {Manifest.permission.CALL_PHONE};
            requestPermissions(permissions, REQUEST_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_PERMISSION_CODE: {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                }else{

                }
                return;
            }
        }
    }
}
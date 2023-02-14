package com.example.appfacebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
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
}
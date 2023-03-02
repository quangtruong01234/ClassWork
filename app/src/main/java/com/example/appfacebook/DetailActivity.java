package com.example.appfacebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appfacebook.database.ProductDatabase;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private TextView tvPhoneNumber;
    private Button callStoreBtn;
    private Button callProduct;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Bundle bundle = getIntent().getExtras();
        if(bundle==null){
            return;
        }
        User user = (User) bundle.get("object_user");
        TextView tvNameItem = findViewById(R.id.tv_name_item);
        tvPhoneNumber = findViewById(R.id.tv_phone_number);
        callStoreBtn = findViewById(R.id.btn_call);
        tvNameItem.setText(user.getName());


        callStoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callStore();
            }
        });




    }




    private void callStore(){
        Uri uri = Uri.parse("tel:" + tvPhoneNumber.getText().toString());
        Intent intent = new Intent(Intent.ACTION_CALL, uri);
        startActivity(intent);
    }


}
package com.example.appfacebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    private TextView tvPhoneNumber;
    private Button callStoreBtn;
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
        ImageView tvImageItem = findViewById(R.id.img_name_item);
        tvPhoneNumber = findViewById(R.id.tv_phone_number);
        callStoreBtn = findViewById(R.id.btn_call);
        tvNameItem.setText(user.getName());
        tvImageItem.setImageResource(user.getResourceImage());

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
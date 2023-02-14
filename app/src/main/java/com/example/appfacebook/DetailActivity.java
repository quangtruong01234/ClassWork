package com.example.appfacebook;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

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
        tvNameItem.setText(user.getName());
        tvImageItem.setImageResource(user.getResourceImage());


    }
}
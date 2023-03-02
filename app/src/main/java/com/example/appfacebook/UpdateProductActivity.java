package com.example.appfacebook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appfacebook.database.ProductDatabase;

public class UpdateProductActivity extends AppCompatActivity {
    private EditText editName;
    private  EditText editDes;
    private  EditText editPrice;
    private Button btnUpdateProduct;

    private Product mProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        editName = findViewById(R.id.edit_name);
        editDes = findViewById(R.id.edit_des);
        editPrice = findViewById(R.id.edit_price);
        btnUpdateProduct = findViewById(R.id.btn_update_product);
        mProduct = (Product) getIntent().getExtras().get("object_product");
        if(mProduct!=null){
            editName.setText(mProduct.getProductName());
            editDes.setText(mProduct.getProductDes());
            editPrice.setText(String.valueOf(mProduct.getPrice()));
        }
        btnUpdateProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProduct();
            }
        });
    }

    private void updateProduct() {
        String strProName = editName.getText().toString().trim();
        String strDes = editDes.getText().toString().trim();
        int  strPrice = Integer.parseInt(editPrice.getText().toString());
        if(TextUtils.isEmpty(strProName)|| TextUtils.isEmpty(strDes)|| strPrice<=0){
            return;
        }
        //Update Product
        mProduct.setProductName(strProName);
        mProduct.setProductDes(strDes);
        mProduct.setPrice(strPrice);

        ProductDatabase.getInstance(this).productDAO().updateProduct(mProduct);
        Toast.makeText(this,"Update product successfully",Toast.LENGTH_SHORT).show();
        Intent intentResult = new Intent();
        setResult(Activity.RESULT_OK,intentResult);
        finish();
    }
}
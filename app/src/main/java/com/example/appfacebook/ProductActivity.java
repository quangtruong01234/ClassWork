package com.example.appfacebook;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appfacebook.database.ProductDatabase;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode()==RESULT_OK){
                        loadData();
                    }
                }
            });

    private EditText editName;
    private  EditText editDes;
    private  EditText editPrice;
    private Button btnAddProduct;
    private RecyclerView rcvProduct;

    private ProductAdapter productAdapter;
    private List<Product> mListProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        //editText
        initUi();

        productAdapter = new ProductAdapter(new ProductAdapter.ClickItemProduct() {
            @Override
            public void updateProduct(Product product) {
                clickUpdateProduct(product);
            }

            @Override
            public void deleteProduct(Product product) {
                clickDeleteProduct(product);
            }
        });
        mListProduct = new ArrayList<>();
        productAdapter.setData(mListProduct);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvProduct.setLayoutManager(linearLayoutManager);

        rcvProduct.setAdapter(productAdapter);

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProduct();
            }
        });
        loadData();
    }



    private void initUi(){
        editName = findViewById(R.id.edit_name);
        editDes = findViewById(R.id.edit_des);
        editPrice = findViewById(R.id.edit_price);
        btnAddProduct = findViewById(R.id.btn_add_product);
        rcvProduct = findViewById(R.id.rcv_product);

    }


    private void addProduct() {
        String strProName = editName.getText().toString().trim();
        String strDes = editDes.getText().toString().trim();
        int  strPrice = Integer.parseInt(editPrice.getText().toString());
        if(TextUtils.isEmpty(strProName)|| TextUtils.isEmpty(strDes)|| strPrice<=0){
            return;
        }
        Product product = new Product(strProName,strDes,strPrice);

        if(isProductExist(product)){
            Toast.makeText(this,"Product exist",Toast.LENGTH_SHORT).show();
            return;
        }
        ProductDatabase.getInstance(this).productDAO().insertProduct(product);
        Toast.makeText(this,"Add user successfully",Toast.LENGTH_SHORT).show();
        editName.setText("");
        editDes.setText("");
        editPrice.setText("");
        hideSoftKeyboard();
        loadData();


    }
    public void hideSoftKeyboard(){
        try{
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }catch (NullPointerException ex){
            ex.printStackTrace();
        }
    }
    private void loadData(){
        mListProduct  = ProductDatabase.getInstance(this).productDAO().getListProduct();
        productAdapter.setData(mListProduct);
    }

    private boolean isProductExist(Product product){
        List<Product> list = ProductDatabase.getInstance(this).productDAO().checkProduct(product.getProductName());
        return list!=null && !list.isEmpty();
    }

    private void clickUpdateProduct(Product product){
        Intent intent = new Intent(ProductActivity.this,UpdateProductActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_product",product);
        intent.putExtras(bundle);
        mActivityResultLauncher.launch(intent);

    }
    private void clickDeleteProduct(Product product) {
        new AlertDialog.Builder(this)
                .setTitle("Confirm delete product")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Delete product
                        ProductDatabase.getInstance(ProductActivity.this).productDAO().deleteProduct(product);
                        Toast.makeText(ProductActivity.this,"Delete product Successfully",Toast.LENGTH_SHORT).show();
                        loadData();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}
package com.example.appfacebook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> mListProduct;
    private ClickItemProduct ClickItemProduct;

    public interface ClickItemProduct{
        void updateProduct(Product product);

        void deleteProduct(Product product);
    }

    public ProductAdapter(ProductAdapter.ClickItemProduct clickItemProduct) {
        ClickItemProduct = clickItemProduct;
    }

    public void setData(List<Product> list){
        this.mListProduct = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail,parent,false);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = mListProduct.get(position);
        if(product==null){
            return;
        }
        holder.tvProductName.setText(product.getProductName());
        holder.tvProductDes.setText(product.getProductDes());
        holder.tvPrice.setText(String.valueOf(product.getPrice()));

        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickItemProduct.updateProduct(product);
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickItemProduct.deleteProduct(product);
            }
        });

    }

    @Override
    public int getItemCount() {
        if(mListProduct!=null){
            return mListProduct.size();
        }
        return 0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{
        private TextView tvProductName;
        private TextView tvProductDes;
        private TextView tvPrice;
        private Button btnUpdate;
        private Button btnDelete;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            tvProductName = itemView.findViewById(R.id.tv_product_name);
            tvProductDes = itemView.findViewById(R.id.tv_product_des);
            tvPrice = itemView.findViewById(R.id.tv_product_price);
            btnUpdate = itemView.findViewById(R.id.btn_update);
            btnDelete = itemView.findViewById(R.id.btn_delete);

        }
    }
}

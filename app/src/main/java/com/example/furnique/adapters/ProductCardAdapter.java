package com.example.furnique.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.furnique.R;
import com.example.furnique.schemas.Product;
import com.example.furnique.utils.CurrencyFormatUtil;

import java.util.ArrayList;
import java.util.List;

public class ProductCardAdapter extends RecyclerView.Adapter<ProductCardAdapter.ViewHolder> {

    Context context;
    ArrayList<Product> productList;
    OnClickListener onClickListener;

    public ProductCardAdapter(Context context, ArrayList<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductCardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductCardAdapter.ViewHolder holder, int position) {
        RequestOptions requestOptions = new RequestOptions()
                .transform(new RoundedCorners(16));
        Glide.with(context)
                .load(productList.get(position).getFirstImage()).apply(requestOptions).into(holder.imageView);

        Product product = productList.get(position);
        holder.txtCategory.setText(product.getCategoryString());
        holder.txtTitle.setText(product.getName());
        holder.txtPrice.setText(CurrencyFormatUtil.formatAsVietnamDong(product.getFirstVariantPrice()));
        holder.itemView.setOnClickListener(v -> {
            this.onClickListener.onClick(v, product);
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void addProducts(List<Product> products) {
        products.forEach(product -> productList.add(product));
        notifyDataSetChanged();
    }

    public void setOnClickListener(OnClickListener l) {
        this.onClickListener = l;
    }


    public interface OnClickListener {
        void onClick(View v, Product product);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txtCategory;
        TextView txtTitle;
        TextView txtPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.productCardImage);
            txtCategory = itemView.findViewById(R.id.txtCategory);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtPrice = itemView.findViewById(R.id.txtPrice);
        }
    }
}

package com.example.furnique.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.furnique.R;

import java.util.ArrayList;

public class ProductCarouselAdapter extends RecyclerView.Adapter<ProductCarouselAdapter.ViewHolder>{
    Context context;
    ArrayList<String> productList;
    onItemClickListener onItemClickListener;

    public ProductCarouselAdapter(Context context, ArrayList<String> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.carousel_product_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(productList.get(position)).into(holder.imageView);
        holder.itemView.setOnClickListener(v -> onItemClickListener.onClick(holder.imageView, productList.get(position)));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    public void setOnItemClickListener(ProductCarouselAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface onItemClickListener {
        void onClick(ImageView imageView, String path);
    }
}

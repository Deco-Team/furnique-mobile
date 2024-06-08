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
import com.example.furnique.R;
import com.example.furnique.schemas.Category;
import com.example.furnique.schemas.Product;

import java.util.List;

public class ProductCarouselAdapter extends RecyclerView.Adapter<ProductCarouselAdapter.ViewHolder>{
    Context context;
    List<Category> productList;
    onItemClickListener onItemClickListener;

    public ProductCarouselAdapter(Context context, List<Category> productList) {
        this.context = context;
        this.productList = productList;
    }

    public void addProducts(List<Category> products)
    {
        products.forEach(product -> productList.add(product));
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.carousel_product_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(productList.get(position).getImage()).into(holder.imageView);
        holder.itemView.setOnClickListener(v -> onItemClickListener.onClick(holder.imageView, productList.get(position)));

        Category category = productList.get(position);
        holder.categoryTitle.setText(category.getName());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView categoryTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            categoryTitle = itemView.findViewById(R.id.categoryTitle);
        }
    }

    public void setOnItemClickListener(ProductCarouselAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface onItemClickListener {
        void onClick(ImageView imageView, Category category);
    }
}

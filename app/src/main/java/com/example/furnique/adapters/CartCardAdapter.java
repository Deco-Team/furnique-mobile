package com.example.furnique.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.furnique.R;

import java.util.ArrayList;

public class CartCardAdapter extends RecyclerView.Adapter<CartCardAdapter.ViewHolder> {

    Context context;

    ArrayList<String> cartItemList;

    public CartCardAdapter(Context context, ArrayList<String> cartItemList) {
        this.context = context;
        this.cartItemList = cartItemList;
    }

    @NonNull
    @Override
    public CartCardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_card, parent, false);
        return new CartCardAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartCardAdapter.ViewHolder holder, int position) {
        RequestOptions requestOptions = new RequestOptions()
                .transform(new RoundedCorners(16));
        Glide.with(context)
                .load(cartItemList.get(position)).apply(requestOptions).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgProductItemCart);
        }
    }
}

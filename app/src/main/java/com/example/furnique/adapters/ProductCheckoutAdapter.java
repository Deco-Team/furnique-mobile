package com.example.furnique.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.furnique.R;
import com.example.furnique.dto.response.CartResponseDTO;
import com.example.furnique.utils.CurrencyFormatUtil;

import java.util.ArrayList;
import java.util.List;

public class ProductCheckoutAdapter extends RecyclerView.Adapter<ProductCheckoutAdapter.ViewHolder> {
    Context context;

    ArrayList<CartResponseDTO.ItemDto> cartItemList;

    public ProductCheckoutAdapter(Context context) {
        this.context = context;
        this.cartItemList = new ArrayList<>();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txtProductNameCart;
        TextView txtSkuCart;
        TextView txtProductPriceCart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgProductItemCart);
            txtProductNameCart = itemView.findViewById(R.id.txtProductNameCart);
            txtSkuCart = itemView.findViewById(R.id.txtSkuCart);
            txtProductPriceCart = itemView.findViewById(R.id.txtProductPriceCart);
        }
    }

    @NonNull
    @Override
    public ProductCheckoutAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.checkout_item_card, parent, false);
        return new ProductCheckoutAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductCheckoutAdapter.ViewHolder holder, int position) {
        RequestOptions requestOptions = new RequestOptions()
                .transform(new RoundedCorners(16));
        Glide.with(context)
                .load(cartItemList.get(position).getProduct().getFirstImage()).apply(requestOptions).into(holder.imageView);

        CartResponseDTO.ItemDto itemDto = cartItemList.get(position);
        holder.txtProductNameCart.setText(itemDto.getProduct().getName());
        holder.txtSkuCart.setText(itemDto.getSku());
        holder.txtProductPriceCart.setText(CurrencyFormatUtil.formatAsVietnamDong(itemDto.getProduct().getFirstVariantPrice()));
    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    public void addCartItems(List<CartResponseDTO.ItemDto> items) {
        items.forEach(item -> cartItemList.add(item));
        notifyDataSetChanged();
    }
}

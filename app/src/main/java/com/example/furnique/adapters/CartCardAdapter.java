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

public class CartCardAdapter extends RecyclerView.Adapter<CartCardAdapter.ViewHolder> {

    Context context;

    ArrayList<CartResponseDTO.ItemDto> cartItemList;
    OnActionCartItemListener onActionCartItemListener;

    public CartCardAdapter(Context context) {
        this.context = context;
        this.cartItemList = new ArrayList<>();
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
                .load(cartItemList.get(position).getProduct().getFirstImage()).apply(requestOptions).into(holder.imageView);

        CartResponseDTO.ItemDto itemDto = cartItemList.get(position);
        holder.txtProductNameCart.setText(itemDto.getProduct().getName());
        holder.txtSkuCart.setText(itemDto.getSku());
        holder.edtQuantityCart.setText(String.valueOf(itemDto.getQuantity()));
        holder.txtProductPriceCart.setText(CurrencyFormatUtil.formatAsVietnamDong(itemDto.getProduct().getFirstVariantPrice()));

        holder.btnDesCart.setOnClickListener(view -> {
            Log.d("CartCardAdapter.onBindViewHolder", itemDto.getProduct().getName() + " :: btnDesCart");
            int quantity = Integer.parseInt(holder.edtQuantityCart.getText().toString());
            if (quantity > 1) {
                quantity -= 1;
                holder.edtQuantityCart.setText(String.valueOf(quantity));
                this.onActionCartItemListener.onUpdateProductQuantityInCart(itemDto, quantity);
            }
        });

        holder.btnIncCart.setOnClickListener(view -> {
            Log.d("CartCardAdapter.onBindViewHolder", itemDto.getProduct().getName() + " :: " + itemDto.getProduct().getFirstVariantQuantity() + " :: btnIncCart");
            int quantity = Integer.parseInt(holder.edtQuantityCart.getText().toString());
            if (quantity < itemDto.getProduct().getFirstVariantQuantity()) {
                quantity += 1;
                holder.edtQuantityCart.setText(String.valueOf(quantity));
                this.onActionCartItemListener.onUpdateProductQuantityInCart(itemDto, quantity);
            }
        });
        holder.edtQuantityCart.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                Log.d("CartCardAdapter.onBindViewHolder", itemDto.getProduct().getName()+ " :: " + itemDto.getProduct().getFirstVariantQuantity() + " :: edtQuantityCart");
                int quantity = Integer.parseInt(holder.edtQuantityCart.getText().toString());
                if (quantity < 1) {
                    quantity = 1;
                } else if (quantity > itemDto.getProduct().getFirstVariantQuantity()) {
                    quantity = itemDto.getProduct().getFirstVariantQuantity();
                }
                holder.edtQuantityCart.setText(String.valueOf(itemDto.getProduct().getFirstVariantQuantity()));
                this.onActionCartItemListener.onUpdateProductQuantityInCart(itemDto, quantity);
            }
        });

        holder.btnDeleteItem.setOnClickListener(view -> {
            Log.d("CartCardAdapter.onBindViewHolder", itemDto.getProduct().getName() + " :: btnDeleteItem");
            this.onActionCartItemListener.onRemoveItemInCart(itemDto);
        });
    }

    public void setOnActionCartItemListener(OnActionCartItemListener listener) {
        this.onActionCartItemListener = listener;
    }


    public interface OnActionCartItemListener {
        void onUpdateProductQuantityInCart(CartResponseDTO.ItemDto itemDto, int quantity);
        void onRemoveItemInCart(CartResponseDTO.ItemDto itemDto);
    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    public void addCartItems(List<CartResponseDTO.ItemDto> items) {
        items.forEach(item -> cartItemList.add(item));
        notifyDataSetChanged();
    }

    public void resetCartItems() {
        cartItemList = new ArrayList<>();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txtProductNameCart;
        TextView txtSkuCart;
        TextView edtQuantityCart;
        TextView txtProductPriceCart;
        Button btnDesCart;
        Button btnIncCart;
        Button btnDeleteItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgProductItemCart);
            txtProductNameCart = itemView.findViewById(R.id.txtProductNameCart);
            txtSkuCart = itemView.findViewById(R.id.txtSkuCart);
            edtQuantityCart = itemView.findViewById(R.id.edtQuantityCart);
            txtProductPriceCart = itemView.findViewById(R.id.txtProductPriceCart);
            btnDesCart = itemView.findViewById(R.id.btnDesCart);
            btnIncCart = itemView.findViewById(R.id.btnIncCart);
            btnDeleteItem= itemView.findViewById(R.id.btnDeleteItem);
        }
    }
}

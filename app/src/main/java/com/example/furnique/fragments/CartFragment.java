package com.example.furnique.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.furnique.R;
import com.example.furnique.activities.CheckoutActivity;
import com.example.furnique.adapters.CartCardAdapter;
import com.example.furnique.contracts.Constants;
import com.example.furnique.dto.request.CartRequestDTO;
import com.example.furnique.dto.response.CartResponseDTO;
import com.example.furnique.models.CartModel;
import com.example.furnique.utils.CurrencyFormatUtil;

public class CartFragment extends Fragment {

    RecyclerView cartRecycler;
    CartCardAdapter cartCardAdapter;
    LinearLayout layout_empty_cart;
    public CartModel cartModel;
    TextView txtProductsPrice;
    TextView txtSumPrice;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

//        View customBar = view.findViewById(R.id.custom_bar);
//        TextView txtTitle = customBar.findViewById(R.id.txtTitle);
//        txtTitle.setText("Giỏ hàng");

        cartRecycler = view.findViewById(R.id.rvCartItems);
        layout_empty_cart = view.findViewById(R.id.layout_empty_cart);
        TextView txtEmptyCart = view.findViewById(R.id.txtEmptyCart);
        TextView txtCartEmpty = view.findViewById(R.id.txtCartEmpty);
        LinearLayout txtOrderSummary = view.findViewById(R.id.txtOrderSummary);
        this.txtProductsPrice = view.findViewById(R.id.txtProductsPrice);
        this.txtSumPrice = view.findViewById(R.id.txtSumPrice);

        SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences(Constants.FURNIQUE_PREF, Context.MODE_PRIVATE);
        String accessToken = pref.getString("accessToken", null);
        Log.d("CartFragment.onCreate", "accessToken: " + accessToken);

        if(accessToken == null) {
            txtEmptyCart.setText("Bạn chưa đăng nhập");
            txtCartEmpty.setText("Hãy đăng nhập để xem giỏ hàng.");
            txtOrderSummary.setVisibility(View.INVISIBLE);
        } else {
            this.cartModel = new CartModel();
            this.cartModel.getCart(accessToken);
            this.cartCardAdapter = new CartCardAdapter(getContext());
            this.cartRecycler.setAdapter(cartCardAdapter);
            this.cartRecycler.setLayoutManager(new GridLayoutManager(getContext(), 1));
            this.cartCardAdapter.setOnActionCartItemListener(new CartCardAdapter.OnActionCartItemListener() {
                @Override
                public void onUpdateProductQuantityInCart(CartResponseDTO.ItemDto itemDto, int quantity) {
                    SharedPreferences pref = getContext().getSharedPreferences(Constants.FURNIQUE_PREF, Context.MODE_PRIVATE);
                    String accessToken = pref.getString("accessToken", null);
                    if(accessToken != null) {
                        CartRequestDTO.UpdateCartDto updateCartDto = new CartRequestDTO.UpdateCartDto(itemDto.getProductId(), itemDto.getSku(), quantity);
                        cartModel.updateProductQuantityInCart(accessToken, updateCartDto);
                    }
                }
                @Override
                public void onRemoveItemInCart(CartResponseDTO.ItemDto itemDto) {
                    SharedPreferences pref = getContext().getSharedPreferences(Constants.FURNIQUE_PREF, Context.MODE_PRIVATE);
                    String accessToken = pref.getString("accessToken", null);
                    if(accessToken != null) {
                        CartRequestDTO.DeleteItemInCartDto deleteItemInCartDto = new CartRequestDTO.DeleteItemInCartDto(itemDto.getProductId(), itemDto.getSku());
                        cartModel.removeItemInCart(accessToken, deleteItemInCartDto);
                    }
                }
            });
            this.cartModel.setOnCartResponseListener(new CartModel.OnCartResponseListener() {
                @Override
                public void onGetCartSuccess(CartResponseDTO.CartDTO cartDTO) {
                    Log.d("CartFragment.onGetCartSuccess: ", String.valueOf(cartDTO.getTotalAmount()));
                    txtProductsPrice.setText(CurrencyFormatUtil.formatAsVietnamDong(cartDTO.getTotalAmount()));
                    txtSumPrice.setText(CurrencyFormatUtil.formatAsVietnamDong(cartDTO.getTotalAmount()));
                    cartCardAdapter.resetCartItems();
                    if (!cartDTO.getItems().isEmpty()) {
                        layout_empty_cart.setVisibility(View.INVISIBLE);
                        cartCardAdapter.addCartItems(cartDTO.getItems());
                        view.findViewById(R.id.btnCheckOut).setEnabled(true);

                    } else {
                        layout_empty_cart.setVisibility(View.VISIBLE);
                        view.findViewById(R.id.btnCheckOut).setEnabled(false);
                    }
                }

                @Override
                public void reUpdateView(String accessToken) {
                    Log.d("CartFragment.reUpdateView:... ", "");
                    cartModel.getCart(accessToken);
                }
            });

            view.findViewById(R.id.btnCheckOut).setOnClickListener(v -> {
                Intent intent = new Intent(getContext(), CheckoutActivity.class);
                startActivity(intent);
            });
        }
        return view;
    }
}
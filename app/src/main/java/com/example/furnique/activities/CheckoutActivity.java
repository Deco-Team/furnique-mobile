package com.example.furnique.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.furnique.R;
import com.example.furnique.adapters.ProductCheckoutAdapter;
import com.example.furnique.contracts.Constants;
import com.example.furnique.databinding.ActivityCheckoutBinding;
import com.example.furnique.dto.request.OrderRequestDTO;
import com.example.furnique.dto.response.CartResponseDTO;
import com.example.furnique.dto.response.DataResponseDTO;
import com.example.furnique.dto.response.OrderResponseDTO;
import com.example.furnique.models.CartModel;
import com.example.furnique.models.CheckoutModel;
import com.example.furnique.utils.CurrencyFormatUtil;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class CheckoutActivity extends AppCompatActivity {

    private ActivityCheckoutBinding binding;
    private ProductCheckoutAdapter productCheckoutAdapter;
    private CartModel cartModel;
    private List<CartResponseDTO.ItemDto> cartItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_checkout);

        binding = ActivityCheckoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        RecyclerView checkoutItemRecycler = binding.rvCartItems;

        SharedPreferences pref = getApplicationContext().getSharedPreferences(Constants.FURNIQUE_PREF, Context.MODE_PRIVATE);
        String accessToken = pref.getString("accessToken", null);

        this.cartModel = new CartModel();
        this.cartModel.setOnCartResponseListener(new CartModel.OnCartResponseListener() {
            @Override
            public void onGetCartSuccess(CartResponseDTO.CartDTO cartDTO) {
                cartItems = cartDTO.getItems();
                productCheckoutAdapter.addCartItems(cartItems);
                binding.priceHolder.setText(CurrencyFormatUtil.formatAsVietnamDong(cartDTO.getTotalAmount()));
            }

            @Override
            public void reUpdateView(String accessToken) {
                cartModel.getCart(accessToken);
            }
        });

        this.cartModel.getCart(accessToken);
        this.productCheckoutAdapter = new ProductCheckoutAdapter(this);
        checkoutItemRecycler.setAdapter(productCheckoutAdapter);
        checkoutItemRecycler.setLayoutManager(new GridLayoutManager(this, 1));

        binding.lastNameInputCheckout.requestFocus();

        binding.checkoutButton.setOnClickListener(v -> {
            String lastName = binding.lastNameInputCheckout.getText().toString();
            String firstName = binding.firstNameInputCheckout.getText().toString();
            String email = binding.emailIntputCheckout.getText().toString();
            String phone = binding.phoneInputCheckout.getText().toString();
            String address = binding.addressInputCheckout.getText().toString();

            if (lastName.isEmpty()) {
                binding.lastNameInputCheckout.setError("Vui lòng nhập họ");
            }
            if (lastName.length() > 30) {
                binding.lastNameInputCheckout.setError("Họ phải ít hơn hoặc bằng 30 chữ cái");
            }
            if (firstName.isEmpty()) {
                binding.firstNameInputCheckout.setError("Vui lòng nhập tên");
            }
            if (firstName.length() > 30) {
                binding.firstNameInputCheckout.setError("Tên phải ít hơn hoặc bằng 30 chữ cái");
            }
            if (email.isEmpty()) {
                binding.emailIntputCheckout.setError("Vui lòng nhập email");
            }
            if (!email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                binding.emailIntputCheckout.setError("Email không hợp lệ");
            }
            if (phone.isEmpty()) {
                binding.phoneInputCheckout.setError("Vui lòng nhập số điện thoại");
            }
            if (!phone.matches("^(\\d{3}[- .]?){2}\\d{4}$")) {
                binding.phoneInputCheckout.setError("Số điện thoại không hợp lệ");
            }
            if (address.isEmpty()) {
                binding.addressInputCheckout.setError("Vui lòng nhập địa chỉ");
            }
            if (address.length() > 500) {
                binding.addressInputCheckout.setError("Địa chỉ phải ít hơn hoặc bằng 500 chữ cái");
            }

            CheckoutModel checkoutModel = new CheckoutModel();
            OrderRequestDTO.CreateOrderDTO.OrderCustomer orderCustomer = new OrderRequestDTO.CreateOrderDTO.OrderCustomer(firstName, lastName, email, phone, address);
            List<OrderRequestDTO.CreateOrderDTO.OrderItem> orderItems = new ArrayList<>();
            cartItems.forEach(item -> orderItems.add(new OrderRequestDTO.CreateOrderDTO.OrderItem(item.getProductId(), item.getSku())));
            checkoutModel.createOrder(accessToken,
                    new OrderRequestDTO.CreateOrderDTO(
                            orderCustomer,
                            orderItems, "MOMO", ""),
                    new CheckoutModel.CreateOrderCallback() {
                        @Override
                        public void onSuccess(DataResponseDTO<OrderResponseDTO.CreateOrderDTO> responseDTO) {
                            Log.d("CheckoutActivity", new Gson().toJson(responseDTO));
                            try {
                                String deeplink = java.net.URLDecoder.decode(responseDTO.getData().getDeeplink(), "UTF-8");
                                Uri uri = Uri.parse(deeplink);
                                Log.d("CheckoutActivity", uri.toString());
                                Intent paymentIntent = new Intent(Intent.ACTION_VIEW, uri);

                                PackageManager packageManager = getPackageManager();
                                List<ResolveInfo> activities =
                                        packageManager.queryIntentActivities(paymentIntent, 0);
                                boolean isIntentSafe = activities.size() > 0;
                                Log.d("CheckoutActivity", "isIntentSafe: " + isIntentSafe);

                                startActivity(paymentIntent);
                            } catch (UnsupportedEncodingException e) {
                                throw new RuntimeException(e);
                            }
                        }

                        @Override
                        public void onFail() {
                            Log.e(CheckoutActivity.class.getName(), "Fail to create order");
                        }
                    });
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
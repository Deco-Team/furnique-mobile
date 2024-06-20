package com.example.furnique.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.furnique.R;
import com.example.furnique.models.ProductDetailsModel;
import com.example.furnique.schemas.Product;
import com.example.furnique.utils.CurrencyFormatUtil;

public class ProductDetailActivity extends AppCompatActivity {
    private Product product;
    private ImageView imgProduct;
    private TextView txtCategories;
    private TextView txtProductName;
    private TextView txtPrice;
    private TextView txtAvailable;
    private TextView txtDescriptionDetail;
    private TextView txtDimensionDetail;
    private Button btnBack;
    private EditText edtQuantity;
    private Button btnDes;
    private Button btnInc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_detail);

        getViews();
        String productId = getIntent().getStringExtra("productId");

        new ProductDetailsModel(productId, this);

        btnBack.setOnClickListener(view -> {
            Intent intent = new Intent(ProductDetailActivity.this, MainActivity.class);
            startActivity(intent);
        });

        btnDes.setOnClickListener(view -> {
            int quantity = Integer.parseInt(edtQuantity.getText().toString());
            if (quantity > 1) {
                quantity -= 1;
                edtQuantity.setText(String.valueOf(quantity));
            }
        });

        btnInc.setOnClickListener(view -> {
            int quantity = Integer.parseInt(edtQuantity.getText().toString());
            if (quantity < product.getFirstVariantQuantity()) {
                quantity += 1;
                edtQuantity.setText(String.valueOf(quantity));
            }
        });

        edtQuantity.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    int quantity = Integer.parseInt(edtQuantity.getText().toString());
                    if (quantity < 1) {
                        edtQuantity.setText("1");
                    }

                    if (quantity > ProductDetailActivity.this.product.getFirstVariantQuantity()) {
                        edtQuantity.setText(String.valueOf(ProductDetailActivity.this.product.getFirstVariantQuantity()));
                    }
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void setData(Product product) {
        Glide.with(this).load(product.getFirstImage()).into(imgProduct);
        txtCategories.setText(product.getCategoryString());
        txtProductName.setText(product.getName());
        txtPrice.setText(CurrencyFormatUtil.formatAsVietnamDong(product.getFirstVariantPrice()));
        txtAvailable.setText(product.getFirstVariantQuantity() + " mặt hàng có sẵn");
        txtDescriptionDetail.setText(product.getDescription());
        txtDimensionDetail.setText(product.getFirstVariantDimensionsString());
        this.product = product;
    }

    private void getViews() {
        imgProduct = findViewById(R.id.imgProduct);
        txtCategories = findViewById(R.id.txtCategories);
        txtProductName = findViewById(R.id.txtProductName);
        txtPrice = findViewById(R.id.txtPrice);
        txtAvailable = findViewById(R.id.txtAvailable);
        txtDescriptionDetail = findViewById(R.id.txtDescriptionDetail);
        txtDimensionDetail = findViewById(R.id.txtDimensionDetail);
        btnBack = findViewById(R.id.btnBack);
        edtQuantity = findViewById(R.id.edtQuantity);
        btnDes = findViewById(R.id.btnDes);
        btnInc = findViewById(R.id.btnInc);
    }
}
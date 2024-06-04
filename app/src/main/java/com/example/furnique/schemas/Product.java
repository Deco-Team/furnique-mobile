package com.example.furnique.schemas;

import java.util.List;

class ProductCategory {
    String _id;
    String name;
    String description;
    String image;
}

class ProductDimension {
    int height;
    int width;
    int length;
}

class ProductVariantKeyValue {
    String color;
    String material;
}

class ProductVariant {
    String sku;
    int price;
    int quantity;
    ProductDimension dimensions;
    ProductVariantKeyValue keyValue;
}

public class Product {
    String _id;
    String name;
    String slug;
    String description;
    List<String> images;
    String brand;
    List<ProductCategory> categories;
    List<ProductVariant> variants;
}

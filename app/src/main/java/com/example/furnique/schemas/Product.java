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

public class Product {
    String _id;
    String name;
    String slug;
    String description;
    List<String> images;
    String brand;
    List<ProductCategory> categories;
    List<ProductVariant> variants;

    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getImages() {
        return images;
    }

    public String getFirstImage() {
        return images.get(0);
    }

    public String getBrand() {
        return brand;
    }

    public List<ProductCategory> getCategories() {
        return categories;
    }

    public List<ProductVariant> getVariants() {
        return variants;
    }
    public String getFirstVariantSku() {
        return variants.get(0).getSku();
    }

    public int getFirstVariantPrice() {
        return variants.get(0).getPrice();
    }

    public String getFirstVariantDimensionsString() {
        ProductDimension dimensions = variants.get(0).dimensions;
        return dimensions.width + " x " + dimensions.length + " x " + dimensions.height;
    }

    public int getFirstVariantQuantity() {
        return variants.get(0).getQuantity();
    }

    public String getCategoryString() {
        StringBuilder categoryBuilder = new StringBuilder();
        for (int i = 0; i < categories.size(); i++) {
            categoryBuilder.append(categories.get(i).name);
            if (i < categories.size() - 1) {
                categoryBuilder.append(", ");
            }
        }
        return categoryBuilder.toString();
    }
    public class ProductVariant {
        String sku;
        int price;
        int quantity;
        ProductDimension dimensions;
        ProductVariantKeyValue keyValue;

        public String getSku() {
            return sku;
        }

        public int getPrice() {
            return price;
        }

        public int getQuantity() {
            return quantity;
        }
    }
}

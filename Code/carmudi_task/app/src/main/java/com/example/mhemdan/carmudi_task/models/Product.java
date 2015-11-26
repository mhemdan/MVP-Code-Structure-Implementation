package com.example.mhemdan.carmudi_task.models;

/**
 * Created by mhemdan on 11/20/15.
 */
public class Product {
    private String name,brand,price,imageUrl;
//    public Product(String name,String brand,String price,String imageUrl){
//        this.name = name;
//        this.brand = brand;
//        this.price = price;
//        this.imageUrl = imageUrl;
//    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

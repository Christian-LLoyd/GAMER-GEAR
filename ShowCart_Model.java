package com.example.gamegearhaven;

public class ShowCart_Model {
    private String title;
    private String price;

    public ShowCart_Model(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public ShowCart_Model(String title, String price) {
        this.title = title;
        this.price = price;
    }
}

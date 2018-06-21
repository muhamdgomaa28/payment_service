package com.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * Created by muhamd gomaa on 2/13/2018.
 */
public class Nonce {
    public Nonce(String nonce, Double chargeAmount) {
        this.nonce = nonce;
        this.chargeAmount = chargeAmount;
    }
    public Nonce(String nonce, Double chargeAmount,Cart c) {
        this.nonce = nonce;
        this.chargeAmount = chargeAmount;

     //   this.product=p;
    this.cart=c;
    }
    public Nonce(){}

    private String nonce  ;
    private Double chargeAmount;
   // private int product;

    private Cart cart;
@JsonIgnore
    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {

        this.cart = cart;
    }

//    public int getProduct() {
//        return product;
//    }
//
//    public void setProduct(int product) {
//        this.product = product;
//    }

    public Double getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(Double chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }
}

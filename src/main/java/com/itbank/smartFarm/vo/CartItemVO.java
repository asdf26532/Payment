package com.itbank.smartFarm.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemVO {

    private int id;
    private int cartId;
    private int itemId;
    private String itemName;
    private int quantity;
    private int price;

    public CartItemVO(int i, int i1, int i2, String itemA, int i3, int i4) {
    }
}
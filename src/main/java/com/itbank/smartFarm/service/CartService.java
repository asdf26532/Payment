package com.itbank.smartFarm.service;

import com.itbank.smartFarm.model.CartDAO;
import com.itbank.smartFarm.vo.CartItemVO;
import com.itbank.smartFarm.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    private CartDAO cd;

    public CartVO getCartByMemberId(int member_id) {
        CartVO cart = cd.findByMemberId(member_id);
        int totalPrice = cart.getTotal_price();
        cart.setTotal_price(totalPrice);

        return cart;
    }

    public void addItemToCart(CartItemVO cartItem) {
        cd.addItem(cartItem);
    }

    public void removeItemFromCart(int cartItemId) {
        cd.removeItem(cartItemId);
    }

    public void updateItemQuantity(int cartItemId, int quantity) {
        cd.updateItemQuantity(cartItemId, quantity);
    }

}

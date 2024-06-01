package com.itbank.smartFarm.controller;

import com.itbank.smartFarm.service.CartService;
import com.itbank.smartFarm.vo.CartItemVO;
import com.itbank.smartFarm.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order/cart")
public class CartController {
    @Autowired
    private CartService cs;

    @GetMapping("/{member_id}")
    public ResponseEntity<CartVO> getCart(@PathVariable int member_id) {
        CartVO cart = cs.getCartByMemberId(member_id);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addItemToCart(@RequestBody CartItemVO cartItem) {
        cs.addItemToCart(cartItem);
        return ResponseEntity.ok("장바구니에 추가 성공");
    }

    @DeleteMapping("/remove/{item_id}")
    public ResponseEntity<?> removeItemFromCart(@PathVariable int item_id) {
        cs.removeItemFromCart(item_id);
        return ResponseEntity.ok("장바구니에서 삭제되었습니다");
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateItemQuantity(@RequestBody CartItemVO cartItem) {
        cs.updateItemQuantity(cartItem.getId(), cartItem.getQuantity());
        return ResponseEntity.ok("Item quantity updated");
    }

}

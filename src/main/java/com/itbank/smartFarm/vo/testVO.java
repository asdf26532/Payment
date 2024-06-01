package com.itbank.smartFarm.vo;

import lombok.Data;

import java.util.List;

public class testVO {
    @Data
    public class CartVO {
        private Long id;
        private Long memberId;
        private List<CartItemVO> cartItems;
        private double totalPrice;
    }

    @Data
    public class CartItemVO {
        private Long id;
        private Long cartId;
        private Long itemId;
        private int quantity;
        private double price;
        private String itemName;
    }

    @Data
    public class OrderVO {
        private Long id;
        private Long memberId;
        private List<OrderItemVO> orderItems;
        private String status;
        private double totalPrice;
    }

    @Data
    public class OrderItemVO {
        private Long id;
        private Long orderId;
        private Long itemId;
        private int quantity;
        private double price;
        private String itemName;
    }

    @Data
    public class MemberVO {
        private Long id;
        private String username;
        private String password;
        private String email;
        private String address;
        private String phoneNumber;
    }

    @Data
    public class ItemVO {
        private Long id;
        private String name;
        private String description;
        private double price;
    }

}

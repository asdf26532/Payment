package com.itbank.smartFarm.model;

import com.itbank.smartFarm.vo.CartItemVO;
import com.itbank.smartFarm.vo.CartVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CartDAO {

    CartVO findByMemberId(int memberId);

    void addItem(CartItemVO cartItem);

    void removeItem(int cartItemId);

    void updateItemQuantity(int cartItemId, int quantity);

}

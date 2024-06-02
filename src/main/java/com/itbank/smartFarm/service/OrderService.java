package com.itbank.smartFarm.service;

import java.util.Arrays;
import java.util.List;

import com.itbank.smartFarm.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.itbank.smartFarm.model.OrderDAO;

@Service
public class OrderService {

	// 간단한 테스트 데이터를 위한 임시 저장소
	private static final MemberVO testMember = new MemberVO();
	private static final CartVO testCart = new CartVO();
	private static final List<CartItemVO> testCartItems = Arrays.asList(
			new CartItemVO(1, 1, 1, "Item A", 10000, 2),
			new CartItemVO(2, 1, 2, "Item B", 20000, 1)
	);

	static {
		testMember.setId(1);
		testMember.setUsername("test");
		testMember.setPassword("password");
		testMember.setAddress("123 Main St, Cityville");
		testMember.setPhoneNumber("010-1234-5678");
		testMember.setEmail("test@example.com");

		testCart.setId(1);
		testCart.setMemberId(1);
		testCart.setCartItems(testCartItems);
		int totalPrice = testCartItems.stream()
				.mapToInt(item -> item.getPrice() * item.getQuantity())
				.sum();
		testCart.setTotalPrice(totalPrice);
	}

	public CartVO getCartByMemberId(int memberId) {
		return testCart;
	}

	public MemberVO getMemberById(int memberId) {
		return testMember;
	}
}
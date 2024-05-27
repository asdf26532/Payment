package com.itbank.smartFarm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.itbank.smartFarm.model.OrderDAO;
import com.itbank.smartFarm.vo.CartVO;
import com.itbank.smartFarm.vo.OrderItemVO;
import com.itbank.smartFarm.vo.OrdersVO;

@Service
public class OrderService {

	@Autowired
	private OrderDAO od;

	// 상품 목록
	public List<OrderItemVO> selectAll() {
		return od.selectAll();
	}

	// 상세 페이지
	public OrderItemVO selectOne(int id) {
		return od.selectOne(id);
	}

	// 상세페이지 -> 주문 페이지 넘기는 정보
	public int makedelivery(String address) {
		return od.makedelivery(address);
	}

	public int getdeliveryid() {
		return od.getdeliveryid();
	}

	public int getorderitem_id() {
		return od.getorderitem_id();
	}

	public int makeorder(OrdersVO ov) {
		return od.makerorder(ov);
	}

	public int getorderid() {
		return od.getorderid();
	}

	public int count(CartVO cv) {
		return od.count(cv);
	}

	// 정보 수정
	public CartVO getorder(int id) {
		return od.getOrder(id);
	}

	public int modify(CartVO input) {
		return od.order(input);
	}

	public int modifyaddress(CartVO input) {
		return od.modifyaddress(input);
	}

	// 정보 삭제
	public int deleteOrder(int id) {
		return od.deleteOrder(id);
	}

	public int getdelid(int id) {
		return od.getdelid(id);
	}

	public Object deletedelivery(int deliverid) {
		return od.deliverid(deliverid);
	}

	public int deliveryid(int delivery_id) {
		return od.deliveryid(delivery_id);

	}
	
	// 주문 정보
	
	public List<CartVO> getOrders(int id) {
		return od.getOrders(id);
	}

	public List<CartVO> afterPay(int memberid) {
		return od.afterPay(memberid);
	}

	public int getExistingOrderId(int memberId, int orderItemId) {
		Integer existingOrderId = od.getExistingOrderId(memberId, orderItemId);
		return existingOrderId != null ? existingOrderId.intValue() : -1;
	}

	public int countUp(CartVO cartVO) {

		return od.countUp(cartVO);
	}
}
package com.itbank.smartFarm.payservice;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itbank.smartFarm.payModel.OrderDAO;
import com.itbank.smartFarm.vo.CartVO;
import com.itbank.smartFarm.vo.OrderItemVo;
import com.itbank.smartFarm.vo.OrdersVO;
import com.itbank.smartFarm.vo.ShipmentsVO;

@Service
public class OrderService {
	@Autowired
	private OrderDAO od;

	public List<CartVO> getOrders(int member_id) {
		return od.getOrders(member_id);
	}

	public CartVO getorder(int id) {
		return od.getOrder(id);
	}


	public int modify(OrderItemVo input) {
		return od.order(input);
	}

	public void deleteOrder(int orderId) {
        od.deleteOrderItems(orderId);
        od.deleteShipmentByOrder(orderId);
        od.deleteOrder(orderId);
	}
}
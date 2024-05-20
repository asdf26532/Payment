package com.itbank.smartFarm.payservice;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itbank.smartFarm.payModel.OrderDAO;
import com.itbank.smartFarm.vo.CartVO;
import com.itbank.smartFarm.vo.MemberVO;
import com.itbank.smartFarm.vo.OrderItemVo;
import com.itbank.smartFarm.vo.OrdersVO;
import com.itbank.smartFarm.vo.ShipmentsVO;

@Service
public class OrderService {
	@Autowired
	private OrderDAO od;

	public List<CartVO> getOrders(int i) {
		return od.getOrders(i);
	}

	public CartVO getorder(int id) {
		return od.getOrder(id);
	}


	public int modify(CartVO input) {
		return od.order(input);
	}

	public int modifyaddress(CartVO input) {
		return od.modifyaddress(input);
	}


	public int deleteOrder(int orderId) {
        od.deleteOrderItems(orderId);
        od.deleteShipmentByOrder(orderId);
        return od.deleteOrder(orderId);
	}
	
	public List<OrderItemVo> selectAll() {
		
		return od.selectAll();
	}
	
	public OrderItemVo selectOne(int id) {
		
		return od.selectOne(id);
	}
}
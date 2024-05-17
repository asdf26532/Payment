package com.itbank.smartFarm.payservice;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.itbank.smartFarm.payDAO.OrderDAO;
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

	public int deleteorder(int id) {
		return od.deleteOrder(id);
	}

	public int addorders(OrdersVO ov) {
		return od.addorders(ov);
		
	}

	public int addorderitems(OrderItemVo oiv) {
		return od.addorderitems(oiv);
		
	}

	public int addshipments(ShipmentsVO sv) {
		return od.addshipments(sv);
		
	}


}

package com.itbank.smartFarm.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itbank.smartFarm.Model.OrderDAO;
import com.itbank.smartFarm.vo.CartVO;

@SpringBootTest
public class OrderDaoTest {
	
	@Autowired
	private OrderDAO od;
	
	@Test
	public void connect() {
		
//		assertNotNull(od.selectAll());
//		
//		assertNotNull(od.selectOne(100));
//		
//		assertNotNull(od.getOrders(456));
//		
//		assertNotNull(od.getOrder(65));
//		
//		assertNotNull(od.deleteOrder(65));
//		
//		assertNotNull(od.deleteShipmentByOrder(65));
//		
//		assertNotNull(od.order(null));
//		
//		assertNotNull(od.modifyaddress(null));
//		
//		assertNotNull(od.makerorder(null));
//		
//		assertNotNull(od.makedelivery(null));
//		
//		assertNotNull(od.getdeliveryid());
//		
//		assertNotNull(od.getorderitem_id());
//		
//		assertNotNull(od.getdelid(0));
//		
//		assertNotNull(od.deliverid(0));
//		
//		assertNotNull(od.count(null));
//		
//		assertNotNull(od.getorderid());
	}
}

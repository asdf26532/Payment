package com.itbank.smartFarm.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itbank.smartFarm.Model.OrderDAO;

@SpringBootTest
public class DaoTest {
	
	@Autowired
	private OrderDAO od;
	
	@Test
	public void connect() {
		
		assertNotNull(od.selectAll());
		
		assertNotNull(od.selectOne(2776));
		
		assertNotNull(od.getOrders(456));
//		
//		assertNotNull(od.getOrder(0));
//		
//		assertNotNull(od.deleteOrder(256));
//		
		assertNotNull(od.deleteShipmentByOrder(2112));
//		
//		assertNotNull(od.order(null));
//		
//		assertNotNull(od.modifyaddress(null));
//		
//		assertNotNull(od.makerorder(null));
//		
//		assertNotNull(od.makedelivery(null));
//		
		assertNotNull(od.getdeliveryid());
//		
		assertNotNull(od.getorderitem_id());
//		
//		assertNotNull(od.getdelid(0));
//		
//		assertNotNull(od.deliverid(0));
//		
//		assertNotNull(od.count(null));
//		
		assertNotNull(od.getorderid());
	}
}

package com.itbank.smartFarm;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.itbank.smartFarm.controller.OrderController;
import com.itbank.smartFarm.payservice.OrderService;

@SpringBootTest
@WebMvcTest(OrderController.class)
public class CartTest {
	@Autowired
	private OrderService os;
	
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    public void testListOrders() throws Exception {
        when(orderService.getOrders(2)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/pay/order"))
               .andExpect(status().isOk())
               .andExpect(model().attributeExists("orderlist"))
               .andExpect(model().attribute("orderlist", Collections.emptyList()))
               .andExpect(view().name("pay/order"));
    }

    @Test
    public void testDeleteOrder() throws Exception {
        int orderId = 1;

        mockMvc.perform(get("/pay/delete/" + orderId))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/pay/order"));

        verify(orderService, times(1)).deleteOrder(orderId);
    }
    
}

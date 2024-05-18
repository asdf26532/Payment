package com.itbank.smartFarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.itbank.smartFarm.payservice.OrderService;
import com.itbank.smartFarm.vo.OrderItemVo;

@Controller
@RequestMapping("/pay")
public class OrderController {
	@Autowired
	private OrderService os;
	@GetMapping("/order")
	public String list(Model model) {
		model.addAttribute("orderlist", os.getOrders(456));

		return "pay/order";
	}


//	@GetMapping("/order")
//	public ModelAndView list(HttpSession session) {
//		ModelAndView mav=new ModelAndView();
//		
//		int memberid=(int) session.getAttribute("member_id");
//		
//		mav.addObject("orderlist", os.getOrders(memberid));
//		mav.setViewName("pay/order");
//		
//		return mav;
//	}

	@GetMapping("/update/{orderitems_id}")
	public ModelAndView update(@PathVariable("orderitems_id") int id) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("orderlist", os.getorder(id));
		mav.setViewName("pay/update");

		return mav;
	}

	@PostMapping("/update/{order_id}")
	public ModelAndView postupdate(OrderItemVo input) {
		ModelAndView mav = new ModelAndView();

		os.modify(input);
		mav.addObject("orderlist", os.getorder(input.getOrder_id()));

		mav.setViewName("pay/order");

		return mav;
	}

	@GetMapping("/delete/{order_id}")
	public String delete(@PathVariable("order_id") int id) {
	    os.deleteOrder(id);
	    return "pay/order";
	}
}
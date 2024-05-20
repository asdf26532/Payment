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
import com.itbank.smartFarm.vo.CartVO;
import com.itbank.smartFarm.vo.MemberVO;
import com.itbank.smartFarm.vo.OrderItemVo;
import com.itbank.smartFarm.vo.ShipmentsVO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/pay")
public class OrderController {
	@Autowired
	private OrderService os;

	@GetMapping("/order")
	public ModelAndView list(HttpSession session) {
		ModelAndView mav = new ModelAndView();

		MemberVO user = (MemberVO) session.getAttribute("user");
		int memberid=user.getId();
		mav.addObject("orderlist", os.getOrders(memberid));

		mav.setViewName("/pay/order");

		return mav;
	}

	@GetMapping("/update/{order_id}")
	public ModelAndView update(@PathVariable("order_id") int orderId) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("orderlist", os.getorder(orderId));

		mav.setViewName("pay/update");

		return mav;
	}

	@PostMapping("/update/{order_id}")
	public ModelAndView postupdate(CartVO input) {
		ModelAndView mav = new ModelAndView();

		os.modify(input);
		os.modifyaddress(input);

		int modifyResult = os.modify(input);
		int modifyAddressResult = os.modifyaddress(input);

		String msg = "수정 되었습니다.";
		if (modifyResult == 0 || modifyAddressResult == 0) {
			msg = "수정 실패하였습니다.";
		}

		mav.addObject("row", (modifyResult != 0 && modifyAddressResult != 0) ? 1 : 0);
		mav.addObject("path", "/pay/order");
		mav.addObject("msg", msg);

		mav.setViewName("/pay/Message");

		return mav;
	}

	@GetMapping("/delete/{order_id}")
	public ModelAndView delete(@PathVariable("order_id") int id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("row", os.deleteOrder(id));

		int row = os.deleteOrder(id);

		String msg = "삭제 되었습니다. ";
		if (row != 0)
			msg = "삭제 실패하였습니다.";

		mav.addObject("path", "/pay/order");
		mav.addObject("msg", msg);

		mav.setViewName("/pay/Message");

		return mav;
	}

	@GetMapping("/market")
	public ModelAndView market() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("list", os.selectAll());
		mav.setViewName("/pay/market");

		return mav;
	}

	@GetMapping("/detailPage/{id}")
	public ModelAndView detailPage(@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("product", os.selectOne(id));
		mav.setViewName("pay/detailPage");

		return mav;
	}

//	@GetMapping("/add/{id}")
//	public ModelAndView addCart(@PathVariable("id") int id) {
//		ModelAndView mav=new ModelAndView();
//		
//		os.add
//	}
}
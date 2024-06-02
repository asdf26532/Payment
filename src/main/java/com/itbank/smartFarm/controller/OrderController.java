package com.itbank.smartFarm.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.itbank.smartFarm.service.OrderService;
import com.itbank.smartFarm.vo.CartVO;
import com.itbank.smartFarm.vo.MemberVO;
import com.itbank.smartFarm.vo.OrdersVO;
import jakarta.servlet.http.HttpSession;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;

	@GetMapping("/cart")
	public String showCart(Model model) {
		int memberId = 1; // testMember ID
		CartVO cart = orderService.getCartByMemberId(memberId);
		model.addAttribute("cart", cart);
		return "cart";
	}

	@GetMapping("/pay")
	public String showPayPage(Model model) {
		int memberId = 1; // testMember ID
		MemberVO member = orderService.getMemberById(memberId);
		CartVO cart = orderService.getCartByMemberId(memberId);
		model.addAttribute("member", member);
		model.addAttribute("cart", cart);
		return "pay";
	}

}
package com.itbank.smartFarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.itbank.smartFarm.Service.OrderService;
import com.itbank.smartFarm.vo.CartVO;
import com.itbank.smartFarm.vo.MemberVO;
import com.itbank.smartFarm.vo.OrdersVO;
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
		int memberid = user.getId();
		mav.addObject("orderlist", os.getOrders(memberid));

		mav.setViewName("/pay/order");

		return mav;
	}

	@GetMapping("/update/{order_id}")
	public ModelAndView update(@PathVariable("order_id") int orderId) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("orderlist", os.getorder(orderId));

		mav.setViewName("/pay/update");

		return mav;
	}

	@PostMapping("/update/{order_id}")
	public ModelAndView postupdate(CartVO input) {
		ModelAndView mav = new ModelAndView();

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
		int deliverid = os.getdelid(id);
		mav.addObject("row", os.deleteOrder(id));
		mav.addObject(os.deletedelivery(deliverid));

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

	@PostMapping("/detailPage/{id}")
	public ModelAndView addCart(@PathVariable("id") int productId, @RequestParam("quantity") int quantity,
			HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		//로그인 한 멤버 정보 + 아이디 + 주소 가져오기
		MemberVO user = (MemberVO) session.getAttribute("user");
		int memberid = user.getId();
		String address = user.getAddress();
		
		//가져온 정보 기반으로 운송정보 생성
		os.makedelivery(address);

		//생성한 배송정보의 id 및 제품의 order_id 가져오기
		int delivery_id = os.getdeliveryid();
		int orderitem_id = os.getorderitem_id();

		//memberid, orderitem_id, delivery_id 기반으로 주문 정보 생성
		OrdersVO ov = new OrdersVO(memberid, orderitem_id, delivery_id);
		mav.addObject(os.makeorder(ov));
		
		//생성한 주문 정보의 id값 가져오기
		int orderid=os.getorderid();
	    // 상세 페이지에서 바꾼 개수 기반으로 개수 변경
	    CartVO cv = new CartVO();
	    cv.setOrder_id(orderid);
	    cv.setCount(quantity);
	    mav.addObject(os.count(cv));
		
		//생성 + 변경한 정보 기반으로 CartView 출력
		mav.addObject("orderlist", os.getOrders(memberid));
		mav.setViewName("/pay/order");

		return mav;
	}
}
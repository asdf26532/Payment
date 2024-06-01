package com.itbank.smartFarm.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/pay")
public class OrderController {

	@Autowired
	private OrderService os;

	//상품 디테일 페이지
	@GetMapping("/details")
	public void details() {}

	// 상세페이지 정보를 받아 장바구니로 이동
	@PostMapping("/details")
	public ModelAndView Order(@RequestParam("quantity") int quantity,HttpSession session) {
		ModelAndView mav = new ModelAndView();

		if (session.getAttribute("user") == null) {
			mav.setViewName("redirect:/member/login");
			return mav;
		} else {
			// 로그인 한 멤버 정보 + 아이디 + 주소 가져오기
			MemberVO user = (MemberVO) session.getAttribute("user");

			int memberId = user.getId();
			String address = user.getAddress();

			int orderItemId = os.getorderitem_id();

			// 주문이 이미 존재하는지 확인 - order 페이지에만 있는지 확인
			int existingOrderId = os.getExistingOrderId(memberId, orderItemId);

			if (existingOrderId != -1) {
				// 주문이 이미 존재하면 수량을 업데이트
				CartVO cartVO = new CartVO();
				cartVO.setOrder_id(existingOrderId);
				cartVO.setCount(quantity);
				os.countUp(cartVO);
			} else {
				// 생성한 배송정보의 ID 및 제품의 order_id 가져오기
				// 가져온 정보를 기반으로 운송정보 생성
				os.makedelivery(address);
				int deliveryId = os.getdeliveryid();
				// memberid, orderitem_id, delivery_id를 기반으로 주문 정보 생성
				OrdersVO orderVO = new OrdersVO(memberId, orderItemId, deliveryId);
				// 주문이 존재하지 않으면 새로운 주문 추가
				os.makeorder(orderVO); // 주문 추가
				int orderid = os.getorderid();
				CartVO cartVO = new CartVO();
				cartVO.setOrder_id(orderid);
				cartVO.setCount(quantity);

				os.count(cartVO); // 주문 수량 설정

			}

			mav.setViewName("redirect:/pay/cart");

			return mav;
		}

	}

	// 장바구니에서 결제
	@GetMapping("/cart")
	public ModelAndView order(HttpSession session) {
		ModelAndView mav = new ModelAndView();

		MemberVO user = (MemberVO) session.getAttribute("user");
		int memberid = user.getId();
		mav.addObject("orderlist", os.getOrders(memberid));

		mav.setViewName("pay/cart");

		return mav;
	}

	// 정보 수정
	@GetMapping("/newUpdate/{order_id}")
	public ModelAndView update(@PathVariable("order_id") int orderId) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("orderlist", os.getorder(orderId));

		mav.setViewName("pay/newUpdate");

		return mav;
	}

	@PostMapping("/newUpdate/{order_id}")
	public ModelAndView postupdate(CartVO input) {
		ModelAndView mav = new ModelAndView();

		int modifyResult = os.modify(input);
		int modifyAddressResult = os.modifyaddress(input);

		String msg = "수정 실패.";
		if (modifyResult == 0 || modifyAddressResult == 0) {
			msg = "수정 성공.";
		}

		mav.addObject("row", (modifyResult != 0 && modifyAddressResult != 0) ? 1 : 0);
		mav.addObject("path", "/pay/newUpdate");
		mav.addObject("msg", msg);
		
		mav.setViewName("pay/Message");

		return mav;
	}

	// 정보 삭제
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

		mav.addObject("path", "/");
		mav.addObject("msg", msg);

		mav.setViewName("pay/Message");

		return mav;
	}

	// 정보 삭제 - 결제 후
	@GetMapping("/deleteafter/{order_id}")
	public ModelAndView deleteafter(@PathVariable("order_id") int id) {
		ModelAndView mav = new ModelAndView();
		int deliverid = os.getdelid(id);
		mav.addObject("row", os.deleteOrder(id));
		mav.addObject(os.deletedelivery(deliverid));

		int row = os.deleteOrder(id);
		String msg = "삭제 되었습니다. ";
		if (row != 0)
			msg = "삭제 실패하였습니다.";

		mav.addObject("path", "/pay/orderStatus");
		mav.addObject("msg", msg);

		mav.setViewName("pay/Message");

		return mav;
	}

	@PostMapping("/updateDeliveryInfo")
	public ModelAndView updateDeliveryInfo(@RequestBody CartVO deliveryInfo, HttpSession session) {
		ModelAndView mav = new ModelAndView();

		// 세션에서 회원 ID 가져오기
		MemberVO user = (MemberVO) session.getAttribute("user");
		int memberId = user.getId();
		System.out.println(deliveryInfo);
		// 배송 상태를 "결제완료"로 업데이트
		os.deliveryid(deliveryInfo.getDelivery_id());

		// 회원의 최신 주문 목록을 가져오기
		List<CartVO> order = os.getOrders(memberId);

		// 업데이트된 주문 목록을 ModelAndView에 추가
		mav.addObject("orderlist", order);

		// 뷰 이름을 orderStatus로 설정
		mav.setViewName("pay/orderStatus");

		return mav;
	}

	// 주문 현황
	@GetMapping("/orderStatus")
	public ModelAndView afterPay(HttpSession session) {
		ModelAndView mav = new ModelAndView();

		// 세션에서 현재 사용자의 ID를 가져옵니다.
		MemberVO user = (MemberVO) session.getAttribute("user");
		int memberId = user.getId();

		// 사용자의 최신 주문 목록을 가져와서 orderlist에 추가합니다.
		List<CartVO> orderList = os.afterPay(memberId);
		mav.addObject("orderlist", orderList);

		// 뷰 이름을 orderStatus로 설정합니다.
		mav.setViewName("pay/orderStatus");

		return mav;
	}
}
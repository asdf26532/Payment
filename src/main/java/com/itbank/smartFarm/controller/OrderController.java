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

@Controller
@RequestMapping("/pay")
public class OrderController {

	@Autowired
	private OrderService os;

	// 상품 리스트 불러오기
	@GetMapping("/market")
	public ModelAndView market() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("list", os.selectAll());
		mav.setViewName("/pay/market");

		return mav;
	}

	// 상세페이지 불러오기
	@GetMapping("/detailPage/{id}")
	public ModelAndView detailPage(@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("product", os.selectOne(id));
		mav.setViewName("pay/detailPage");

		return mav;
	}


	// 상세페이지 정보를 받아 장바구니로 이동
	@PostMapping("/detailPage/{id}")
	public ModelAndView Order(@PathVariable("id") int productId, @RequestParam("quantity") int quantity,
			HttpSession session) {
		ModelAndView mav = new ModelAndView();

		if (session.getAttribute("user") == null) {
			// 로그인 페이지로 리다이렉트
			mav.setViewName("redirect:/member/login");
			return mav;
		}

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
		
	    mav.setViewName("redirect:/pay/order");
	    
		return mav;
	}

	// 장바구니에서 결제
	@GetMapping("/order")
	public ModelAndView order(HttpSession session) {
		ModelAndView mav = new ModelAndView();

		MemberVO user = (MemberVO) session.getAttribute("user");
		int memberid = user.getId();
		mav.addObject("orderlist", os.getOrders(memberid));

		mav.setViewName("/pay/order");

		return mav;
	}

	// 정보 수정
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

		mav.addObject("path", "/pay/order");
		mav.addObject("msg", msg);

		mav.setViewName("/pay/Message");

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

		mav.addObject("path", "/pay/Orderprepare");
		mav.addObject("msg", msg);

		mav.setViewName("/pay/Message");

		return mav;
	}
	@PostMapping("/updateDeliveryInfo")
	public ModelAndView updateDeliveryInfo(@RequestBody CartVO deliveryInfo, HttpSession session) {

		ModelAndView mav = new ModelAndView();
		MemberVO user = (MemberVO) session.getAttribute("user");
		int memberid = user.getId();
		os.deliveryid(deliveryInfo.getDelivery_id());
		mav.addObject("orderlist", os.getOrders(memberid));
		mav.setViewName("/pay/Orderprepare");
		return mav;
	}
	
	// 주문 현황
	@GetMapping("/orderPrepare")
	public ModelAndView afterPay(HttpSession session) {
		ModelAndView mav = new ModelAndView();

		MemberVO user = (MemberVO) session.getAttribute("user");
		int memberid = user.getId();
		mav.addObject("orderlist", os.afterPay(memberid));

		mav.setViewName("/pay/orderPrepare");

		return mav;
	}
}
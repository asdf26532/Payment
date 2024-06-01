package com.itbank.smartFarm.controller;

import com.itbank.smartFarm.service.PayService;
import com.itbank.smartFarm.vo.MemberVO;
import com.itbank.smartFarm.vo.testVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order/pay")
public class PayController {
    @Autowired
    private PayService ps;

    @PostMapping("/{memberId}")
    public ResponseEntity<?> checkout(@PathVariable Long memberId) {
        testVO.OrderVO order = ps.checkout(memberId);
        return ResponseEntity.ok(order);
    }

    @PostMapping
    public ResponseEntity<?> pay(@RequestBody OrderVO order) {
        String paymentId = ps.processPayment(order);
        ps.updateOrderStatus(order.getId(), "COMPLETED");
        return ResponseEntity.ok("Payment successful, payment ID: " + paymentId);
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<MemberVO> getMemberDetails(@PathVariable Long memberId) {
        MemberVO member = ps.getMemberDetails(memberId);

        return ResponseEntity.ok(member);
    }
}

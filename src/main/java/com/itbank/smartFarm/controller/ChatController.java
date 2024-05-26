package com.itbank.smartFarm.controller;

import com.itbank.smartFarm.service.ChatService;
import com.itbank.smartFarm.vo.MemberVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping()
    public String chatScreen(Model model, HttpSession session) {
        MemberVO user = (MemberVO) session.getAttribute("user");
        List<MemberVO> senders = chatService.getSendersByReceiverId(user.getId());
        model.addAttribute("senders", senders);
        model.addAttribute("sender_id", user.getId());
        return "chat/chat";
    }
}

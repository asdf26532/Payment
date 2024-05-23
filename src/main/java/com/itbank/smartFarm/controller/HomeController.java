package com.itbank.smartFarm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
    	model.addAttribute("msg", "Project Start");
    	
        return "home";
    }

    @GetMapping("/company")
    public void company(Model model) {}

    @GetMapping("/download")
    public void download(Model model) {}

    @GetMapping("/support")
    public void support(Model model) {}

    @GetMapping("/chat/chatScreen/{senderId}/{receiverId}")
    public String chat(Model model, @PathVariable String senderId, @PathVariable String receiverId) {
        model.addAttribute("sender_id", senderId);
        model.addAttribute("receiver_id", receiverId);
        return "/chat/chatScreen";
    }
}

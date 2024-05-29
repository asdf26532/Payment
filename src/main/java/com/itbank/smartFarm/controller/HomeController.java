package com.itbank.smartFarm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
    public String support(Model model) {

        return "redirect:/board/QnA";
    }


}

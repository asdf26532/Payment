package com.itbank.smartFarm.controller;

import com.itbank.smartFarm.service.BoardService;
import com.itbank.smartFarm.vo.BoardVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService bs;


    @GetMapping("/freeBoard")
    public ModelAndView freeBoard(@RequestParam Map<String, Object> param) {

        ModelAndView mav = new ModelAndView();

        mav.addObject("map", bs.getfreeBds(param));
        mav.setViewName("/board/freeBoard");

        return mav;
    }


    @GetMapping("/fBadd")
    public String add() {
        return "/board/fBadd";
    }

    @PostMapping("/fBadd")
    public ModelAndView add(BoardVO input) {

        ModelAndView mav = new ModelAndView();

        mav.addObject("row", bs.addFB(input));
        mav.setViewName("/board/freeBoard");

        return mav;
    }


    @GetMapping("/fB_view/{idx}")
    public ModelAndView fB_view(@PathVariable int idx) {

        ModelAndView mav = new ModelAndView();

        mav.addObject("row", bs.getfB(idx));
        mav.setViewName("board/fB_view");

        return mav;
    }

    @PostMapping("/fB_view")
    public ModelAndView fB_view(BoardVO input, HttpSession session) {

        ModelAndView mav = new ModelAndView();

        mav.addObject("row", bs);

////        if (session.user != null) {
//            mav.addObject("comment", bs.addCom(input));
//        }

        mav.setViewName("/board/fB_view");

        return mav;
    }


    @GetMapping("/delete/{idx}")
    public String delete(@PathVariable int idx) {
        bs.delBoard(idx);

        return "redirect:/goBack";
    }


    @GetMapping("/updatefB/{idx}")
    public ModelAndView updateFB(@PathVariable int idx) {

        ModelAndView mav = new ModelAndView();

        mav.addObject("row", bs.getfB(idx));
        mav.setViewName("board/fBadd");

        return mav;
    }

    @GetMapping("/QnA")
    public ModelAndView qna(@RequestParam Map<String, Object> param) {

        ModelAndView mav = new ModelAndView();

        mav.addObject("map",bs.getQna(param));
        mav.setViewName("/board/QnA");

        return mav;
    }

    @GetMapping("/QnA_view/{idx}")
    public ModelAndView QnA_view(@PathVariable int idx) {

        ModelAndView mav = new ModelAndView();

        mav.addObject("row", bs.getSelectQna(idx));
        mav.setViewName("board/QnA_view");

        return mav;
    }

    @PostMapping("/QnA_view")
    public ModelAndView QnA_view(BoardVO input, HttpSession session) {

        ModelAndView mav = new ModelAndView();

        mav.addObject("row", bs);

////        if (session.user != null) {
//            mav.addObject("comment", bs.addCom(input));
//        }

        mav.setViewName("/board/QnA_view");

        return mav;
    }


    @GetMapping("/QnAadd")
    public String addQna() {
        return "/board/QnAadd";
    }

    @PostMapping("/QnAadd")
    public ModelAndView addQna(BoardVO input) {

        ModelAndView mav = new ModelAndView();

        mav.addObject("row", bs.addQnA(input));
        mav.setViewName("/board/QnA");

        return mav;
    }


    @GetMapping("/updateQnA/{idx}")
    public ModelAndView update(@PathVariable int idx) {

        ModelAndView mav = new ModelAndView();

        mav.addObject("row", bs.getSelectQna(idx));
        mav.setViewName("board/QnAadd");

        return mav;
    }
}



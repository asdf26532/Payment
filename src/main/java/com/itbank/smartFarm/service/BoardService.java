package com.itbank.smartFarm.service;

import java.util.List;

import com.itbank.smartFarm.components.Paging;
import com.itbank.smartFarm.model.BoardDAO;
import com.itbank.smartFarm.vo.BoardVO;
import com.itbank.smartFarm.vo.ReplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class BoardService {

    @Autowired
    private BoardDAO bd;


    public Map<String, Object> getNotices(Map<String, Object> param) {

        String sint = (String) param.get("page");
        sint = (sint == null) ? "1" : sint;

        int reqPage = Integer.parseInt(sint);
        int boardnum = 101;

        int totalcount;
        if (param.containsKey("group") || param.containsKey("search")) {
            param.put("num", boardnum); // 검색 조건이 있는 경우에만 param에 num 추가
            totalcount = bd.searchboard(param);
        } else {
            totalcount = bd.totalboard(boardnum);
        }

        Paging page = new Paging(reqPage, totalcount);

        param.put("offset", page.getOffset());
        param.put("boardCount", page.getBoardCount());


        Map<String, Object> result = new HashMap<>();

        result.put("pg", page);
        result.put("list", bd.getAllNotices(param));

        return result;
    }


    public Map<String, Object> getfreeBds(Map<String, Object> param) {

        String sint = (String) param.get("page");
        sint = (sint == null) ? "1" : sint;

        int reqPage = Integer.parseInt(sint);
        int boardnum = 102;

        int totalcount;
        if (param.containsKey("group") || param.containsKey("search")) {
            param.put("num", boardnum); // 검색 조건이 있는 경우에만 param에 num 추가
            totalcount = bd.searchboard(param);
        } else {
            totalcount = bd.totalboard(boardnum);
        }

        Paging page = new Paging(reqPage, totalcount);

        param.put("offset", page.getOffset());
        param.put("boardCount", page.getBoardCount());


        Map<String, Object> result = new HashMap<>();

        result.put("pg", page);
        result.put("list", bd.selectFreeAll(param));

        return result;
    }

    public BoardVO getNotice(int id) {
        return bd.getOneNotice(id);
    }

    public int updateViewCount(int id) {
        return bd.updateViewCount(id);
    }

    public BoardVO getfB(int id) {
        return bd.selectFreeOne(id);
    }

    @Transactional
    public int addNotice(BoardVO input) {
        return bd.addNotice(input);

    }

    @Transactional
    public int deleteBoard(int id) {
        return bd.deleteBoard(id);
    }

    public int addFB(BoardVO input) {
        return bd.insertFb(input);
    }

    public int updateFB(BoardVO input) {
        return bd.updateBoard(input);
    }

    @Transactional
    public void updateNotice(BoardVO input) {
        bd.updateBoard(input);
    }


    public Map<String, Object> getMarkets(Map<String, Object> param) {
        String sint = (String) param.get("page");
        sint = (sint == null) ? "1" : sint;

        int reqPage = Integer.parseInt(sint);
        int boardnum = 104;

        if (param.containsKey("category") && "".equals(param.get("category"))) {
            param.put("category", null);
        }
        if (param.containsKey("soldout") && "".equals(param.get("soldout"))) {
            param.put("soldout", null);
        }

        int totalcount;
        if (param.containsKey("category") || param.containsKey("soldout") || param.containsKey("group") || param.containsKey("search")) {
            param.put("num", boardnum); // 검색 조건이 있는 경우에만 param에 num 추가
            totalcount = bd.searchboard(param);
        } else {
            totalcount = bd.totalboard(boardnum);
        }

        Paging page = new Paging(reqPage, totalcount);

        param.put("offset", page.getOffset());
        param.put("boardCount", page.getBoardCount());

        Map<String, Object> result = new HashMap<>();
        result.put("pg", page);
        result.put("list", bd.getAllFreemarkets(param));

        return result;
    }


    public BoardVO getMarket(int id) {
        return bd.getOneFreeMarket(id);
    }


    @Transactional
    public int addMarket(BoardVO input) {
        return bd.addFreeMarket(input);


    }

    public Map<String, Object> getQna(Map<String, Object> param) {

        String sint = (String) param.get("page");
        sint = (sint == null) ? "1" : sint;

        int reqPage = Integer.parseInt(sint);
        int boardnum = 105;

        int totalcount;
        if (param.containsKey("group") || param.containsKey("search")) {
            param.put("num", boardnum); // 검색 조건이 있는 경우에만 param에 num 추가
            totalcount = bd.searchboard(param);
        } else {
            totalcount = bd.totalboard(boardnum);
        }

        Paging page = new Paging(reqPage, totalcount);

        param.put("offset", page.getOffset());
        param.put("boardCount", page.getBoardCount());


        Map<String, Object> result = new HashMap<>();

        result.put("pg", page);
        result.put("list", bd.selectQnaAll(param));

        return result;
    }

    @Transactional
    public void updateMarket(BoardVO input) {
        bd.updateFreeMarket(input);
    }

    public BoardVO getSelectQna(int id) {
        return bd.selectQnaOne(id);
    }

    public int addQnA(BoardVO input) {
        return bd.insertQna(input);

    }


//    public List<ReplyVO> getReplys() {
//        return bd.selectReplyAll();
//    }
//
//    public List<ReplyVO> getReplys(int board_id) {
//        return bd.selectReplys(board_id);
//    }
//
//    public int addReply(ReplyVO input) {
//        return bd.insertReply(input);
//    }

}
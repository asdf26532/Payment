package com.itbank.smartFarm.service;

import java.util.List;

import com.itbank.smartFarm.components.Paging;
import com.itbank.smartFarm.model.BoardDAO;
import com.itbank.smartFarm.vo.ReplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itbank.smartFarm.model.BoardDAO;
import com.itbank.smartFarm.model.vo.BoardVO;
import com.itbank.smartFarm.model.vo.MemberVO;

import java.util.HashMap;
import java.util.Map;

@Service
public class BoardService {

    @Autowired
    private BoardDAO bd;

    public List<BoardVO> getNotices() {
        return bd.getAllNotices();
    }

    public Map<String, Object> getfreeBds(Map<String, Object> param) {

        String sint = (String) param.get("page");
        sint = (sint == null) ? "1" : sint;

        int reqPage = Integer.parseInt(sint);

        Paging page = new Paging(reqPage, bd.totalBoard());

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

    public BoardVO getfB(int idx) {
        return bd.selectFreeOne(idx);
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


    public List<BoardVO> getMarkets(String category, Integer soldout) {
        return bd.getAllFreemarkets(category, soldout);
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

        Paging page = new Paging(reqPage, bd.totalBoard());

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

    public BoardVO getSelectQna(int idx) {
        return bd.selectQnaOne(idx);
    }

    public int addQnA(BoardVO input) {
        return bd.insertQna(input);

    }


    public List<ReplyVO> getReplys() {
        return bd.selectReplyAll();
    }

    public List<ReplyVO> getReplys(int board_id) {
        return bd.selectReplys(board_id);
    }

    public int addReply(ReplyVO input) {
        return bd.insertReply(input);
    }


}

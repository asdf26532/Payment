package com.itbank.smartFarm.service;

import com.itbank.smartFarm.components.Paging;
import com.itbank.smartFarm.model.BoardDAO;
import com.itbank.smartFarm.vo.BoardVO;
import com.itbank.smartFarm.vo.ReplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    public BoardVO getSelectQna(int id) {
        return bd.selectQnaOne(id);
    }

    public int addQnA(BoardVO input) {
        return bd.insertQna(input);

    }

    public int updateQnA(BoardVO input) {
        return bd.updateBoard(input);
    }

    // 댓글 조회
    public List<ReplyVO> getReplies(int board_id) {
        List<ReplyVO> replies = bd.getReplies(board_id);
        if (replies == null) {
            return new ArrayList<>();
        }
        return replies;
    }

    // 댓글 추가
    @Transactional
    public int addReply(ReplyVO reply) {
        return bd.addReply(reply);
    }

    // 댓글 삭제
    @Transactional
    public int deleteReply(int id) {
        return bd.deleteReply(id);
    }
}

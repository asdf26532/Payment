package com.itbank.smartFarm.service;

import com.itbank.smartFarm.components.Paging;
import com.itbank.smartFarm.model.BoardDAO;
import com.itbank.smartFarm.vo.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardService {

    @Autowired
    private BoardDAO bd;

    public Map<String, Object> getfreeBds(Map<String, Object> param) {

        String sint = (String)param.get("page");
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

    public BoardVO getfB(int idx) {

        return bd.selectFreeOne(idx);
    }

    public BoardVO addCom(BoardVO input) {

        bd.inComment(input);

        return null;
    }

    public int addFB(BoardVO input) {

        return bd.insertFb(input);

    }

    public int delBoard(int idx) {

        return bd.delete(idx);
    }
}

package com.itbank.smartFarm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itbank.smartFarm.model.BoardDAO;
import com.itbank.smartFarm.model.vo.BoardVO;
import com.itbank.smartFarm.model.vo.MemberVO;

@Service
public class BoardService {

	@Autowired
	private BoardDAO bd;

	public List<BoardVO> getNotices() {
		return bd.getAllNotices();
	}

	public BoardVO getBoard(int id) {
		return bd.getOneNotice(id);
	}

	public int updateViewCount(int id) {
		return bd.updateViewCount(id);

	}

	public int addBoard(BoardVO input) {
		return bd.addNotice(input);

	}

	public int deleteBoard(int id) {
		return bd.deleteNotice(id);
	}

	public void updateBoard(BoardVO input) {
		bd.updateNotice(input);
	}

	public List<BoardVO> getFreemarkets() {
		return bd.getAllFreemarkets();
	}
	
	public BoardVO getFreemarket(int id) {
		return bd.getOneFreeMarket(id);
	}

}

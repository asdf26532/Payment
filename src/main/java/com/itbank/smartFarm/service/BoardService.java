package com.itbank.smartFarm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	public BoardVO getNotice(int id) {
		return bd.getOneNotice(id);
	}

	public int updateViewCount(int id) {
		return bd.updateViewCount(id);

	}
	@Transactional
	public int addNotice(BoardVO input) {
		return bd.addNotice(input);

	}

	@Transactional
	public int deleteBoard(int id) {
		return bd.deleteBoard(id);
	}
	
	@Transactional
	public void updateNotice(BoardVO input) {
		bd.updateNotice(input);
	}

	public List<BoardVO> getMarkets() {
		return bd.getAllFreemarkets();
	}
	
	public BoardVO getMarket(int id) {
		return bd.getOneFreeMarket(id);
	}

	@Transactional
	public int addMarket(BoardVO input) {
		return bd.addFreeMarket(input);
		
	}
	
	@Transactional
	public void updateMarket(BoardVO input) {
		bd.updateFreeMarket(input);
	}

}

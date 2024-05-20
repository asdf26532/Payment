package com.itbank.smartFarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itbank.smartFarm.service.BoardService;
import com.itbank.smartFarm.model.vo.BoardVO;
import com.itbank.smartFarm.model.vo.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService bs;

	// 현재 세션에 있는 유저 정보 (중복 제거를 위한 별도 메소드 할당)
	private MemberVO getUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return (MemberVO) session.getAttribute("user");
	}

	// 전체 공지 게시글 리스트화
	@GetMapping("/notice")
	public String notices(Model model) {
		model.addAttribute("notices", bs.getNotices());
		return "board/notice";
	}

	// 지정된 글 번호(id)의 상세 글 내용 조회
	@GetMapping("/notice_view/{id}")
	public String notice(@PathVariable("id") int id, Model model, HttpServletRequest request) {
		MemberVO user = getUser(request);
		// 상세 글 조회 시, 현재 접속 중인 계정의 id를 검색해 수정/삭제 버튼을 보이게 하기 위함
		int memberId = (user != null) ? user.getId() : -1;

		bs.updateViewCount(id);
		model.addAttribute("notice", bs.getNotice(id));
		model.addAttribute("memberid", memberId);
		return "board/notice_view";
	}

	// 공지 사항 작성 폼으로 전송 (인터셉트 = member_id != 1001시 로그인으로 리다이렉트)
	@GetMapping("/notice_write")
	public String noticewrite() {
		return "board/notice_write";
	}

	// 공지 사항 작성 처리
	@PostMapping("/notice_write")
	public String noticewrite(BoardVO input) {
		bs.addNotice(input);
		return "redirect:/board/notice";
	}

	// 공지사항 삭제
	@PostMapping("/notice_delete/{id}")
	public String noticedelete(@PathVariable("id") int id) {
		bs.deleteBoard(id);
		return "redirect:/board/notice";
	}

	// 현재 글 번호(id) 정보 획득 후 공지사항 업데이트(notice_write form 재활용) 폼으로 전송
	@GetMapping("/notice_update/{id}")
	public String noticeupdate(@PathVariable("id") int id, Model model) {
		model.addAttribute("notice", bs.getNotice(id));
		return "board/notice_write";
	}

	// 공지사항 업데이트
	@PostMapping("/notice_update/{id}")
	public String noticeupdate(BoardVO input) {
		bs.updateNotice(input);
		return "redirect:/board/notice";
	}
	
	

	// 전체 장터 게시글 리스트화
	// 장터에서 카테고리, 판매 상태로 필터링하도록 추가하는 기능.
	@GetMapping("/freemarket")
	public String freemarkets(
			@RequestParam(required = false) String category,
			@RequestParam(required = false) Integer soldout,
			Model model) {
		model.addAttribute("freemarkets", bs.getMarkets(category, soldout));
		return "board/freemarket";
	}

	// 지정된 글 번호(id)의 상세 글 내용 조회
	@GetMapping("/freemarket_view/{id}")
	public String freemarket(@PathVariable("id") int id, Model model, HttpServletRequest request) {
		MemberVO user = getUser(request);
		// 상세 글 조회 시, 현재 접속 중인 계정의 id를 검색해 수정/삭제 버튼을 보이게 하기 위함
		int memberid = (user != null) ? user.getId() : -1;

		bs.updateViewCount(id);
		model.addAttribute("freemarket", bs.getMarket(id));
		model.addAttribute("memberid", memberid);
		return "board/freemarket_view";
	}

	// 장터 작성 폼으로 전송 (비 로그인 시 로그인으로 리다이렉트)
	@GetMapping("/freemarket_write")
	public String freemarketwrite() {
		return "board/freemarket_write";
	}

	// 장터 글 작성 처리
	@PostMapping("/freemarket_write")
	public String freemarketwrite(BoardVO input, HttpServletRequest request) {
	    // 현재 세션에 저장된 유저 정보 획득
	    MemberVO user = getUser(request);
	    input.setMember_id(user.getId());
	    bs.addMarket(input);
	    return "redirect:/board/freemarket";
	}

	// 장터 글 삭제
	@PostMapping("/freemarket_delete/{id}")
	public String freemarketdelete(@PathVariable("id") int id) {
		bs.deleteBoard(id);
		return "redirect:/board/freemarket";
	}

	// 현재 글 번호(id) 정보 획득 후 장터 글 업데이트(freemarket_write form 재활용) 폼으로 전송
	@GetMapping("/freemarket_update/{id}")
	public String freemarketupdate(@PathVariable("id") int id, Model model) {
		model.addAttribute("freemarket", bs.getMarket(id));
		return "board/freemarket_write";
	}

	// 장터 글 업데이트
	@PostMapping("/freemarket_update/{id}")
	public String freemarketupdate(BoardVO input) {
		bs.updateMarket(input);
		return "redirect:/board/freemarket";
	}

}

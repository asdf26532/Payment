package com.itbank.smartFarm.interceptor;

import com.itbank.smartFarm.service.BoardService;
import com.itbank.smartFarm.vo.BoardVO;
import com.itbank.smartFarm.vo.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class SecretInterceptor implements HandlerInterceptor {

    @Autowired
    private BoardService bs;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();
        MemberVO user = (MemberVO) session.getAttribute("user");
        String boardId = request.getRequestURI().split("/")[request.getRequestURI().split("/").length - 1];

        // 비밀글인 경우에만 인터셉터를 적용합니다.
        if (isSecretBoard(boardId) && !canAccessBoard(user, boardId)) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().println("<script>alert('작성자 또는 관리자만 내용을 확인할 수 있습니다.'); history.go(-1);</script>");
            return false;
        }
        return true;
    }

    private boolean isSecretBoard(String boardId) {
        BoardVO board = bs.getSelectQna(Integer.parseInt(boardId));
        return board != null && board.isSecret();
    }

    private boolean canAccessBoard(MemberVO user, String boardId) {
        return user != null && (user.getId() == 1001 || user.getId() == bs.getSelectQna(Integer.parseInt(boardId)).getMember_id());
    }
}

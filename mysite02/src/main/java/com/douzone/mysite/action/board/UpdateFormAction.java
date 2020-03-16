package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class UpdateFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		Long no = Long.parseLong(request.getParameter("no"));
		BoardVo vo = new BoardVo();
		vo.setNo(no);
		
		BoardVo list = new BoardRepository().findbyNo(vo);
		request.setAttribute("list", list);
		
		WebUtil.forward("/WEB-INF/views/board/modify.jsp", request, response);
	}

}

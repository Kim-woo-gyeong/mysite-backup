package com.douzone.mysite.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		int page;
		
		if(request.getParameter("p") == null) {
			page = 0;
		} else {
			page = Integer.parseInt(request.getParameter("p"));
		}
		
		List<BoardVo> list = new BoardRepository().findAll(page);
		
		int total = new BoardRepository().TotlaPage();
		request.setAttribute("list", list);
		request.setAttribute("total", total);
		
		WebUtil.forward("/WEB-INF/views/board/list.jsp", request, response);
	}

}

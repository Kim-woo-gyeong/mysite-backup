package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class ReplyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		Long no = Long.parseLong(request.getParameter("no"));
		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		Long userNo = Long.parseLong(request.getParameter("user"));
		
		BoardVo vo = new BoardVo();
		vo.setNo(no);
		
		
		BoardVo boardVo = new BoardRepository().findbyNo(vo);
		BoardVo newVo = new BoardVo();
		
		newVo.setTitle(title);
		newVo.setContents(contents);
		newVo.setUserNo(userNo);
		newVo.setONo(boardVo.getONo());
		newVo.setGNo(boardVo.getGNo());
		newVo.setDepth(boardVo.getDepth());
		
		new BoardRepository().orderUpdate(newVo);
		new BoardRepository().replyInsert(newVo);
		
		WebUtil.redirect(request.getContextPath() + "/board", request, response);
	}

}

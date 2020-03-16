package com.douzone.mysite.action.guestbook;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.GuestBookRepository;
import com.douzone.mysite.vo.GuestBookVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class InsertAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		
		String name = request.getParameter("name");
		String password = request.getParameter("pass");
		String contents = request.getParameter("content");
		
		if("".equals(name)) {
			
			pw.println("<script>alert('이름을 입력하세요.');location.href='"+request.getContextPath()+"/guestbook';</script>");
			pw.flush();
			
		} else if("".equals(password)) {
			
			pw.println("<script>alert('비밀번호를 입력하세요.');location.href='"+request.getContextPath()+"/guestbook';</script>");
			pw.flush();
			
		} else if("".equals(contents)) {
			pw.println("<script>alert('내용을 입력하세요.');location.href='"+request.getContextPath()+"/guestbook';</script>");
			pw.flush();
		} else {
			GuestBookVo vo = new GuestBookVo();
			vo.setName(name);
			vo.setPassword(password);
			vo.setContents(contents);
			
			new GuestBookRepository().insert(vo);
			WebUtil.redirect(request.getContextPath()+"/guestbook", request, response);
		} 
	}

}

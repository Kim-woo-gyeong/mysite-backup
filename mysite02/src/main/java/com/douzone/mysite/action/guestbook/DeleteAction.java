package com.douzone.mysite.action.guestbook;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.GuestBookRepository;
import com.douzone.mysite.vo.GuestBookVo;
import com.douzone.web.action.Action;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		Long no = Long.parseLong(request.getParameter("no"));
		String password = request.getParameter("password");
		
		GuestBookVo vo = new GuestBookVo();
		vo.setNo(no);
		vo.setPassword(password);
		
		Boolean result = new GuestBookRepository().delete(vo);
		
		if(result) {
			response.sendRedirect(request.getContextPath()+"/guestbook");
		} else {
			pw.println("<script>alert('비밀번호가 틀렸습니다');location.href='"+request.getContextPath()+"/guestbook?a=deleteform&&no="+vo.getNo()+"';</script>");
			pw.flush();
		}
	}

}

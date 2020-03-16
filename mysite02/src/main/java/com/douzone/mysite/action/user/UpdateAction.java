package com.douzone.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.repository.UserRepository;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		Long no = Long.parseLong(request.getParameter("no"));
		
		UserVo vo = new UserVo();
		vo.setName(name);
		vo.setPassword(password);
		vo.setNo(no);
		
		UserVo authUser = new UserRepository().update(vo);
		
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", authUser);
		
		WebUtil.redirect(request.getContextPath()+"/user?a=joinsuccess&&bool=false", request, response);
	}

}

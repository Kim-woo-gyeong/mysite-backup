package com.douzone.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.mysite.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler) throws Exception {
		
		//1. handler 종류 확인. -annotation이 찍혀 있나
		if(handler instanceof HandlerMethod == false) {
			// (보통 assets의 정적 자원 접근 )DefaultServletHandler가 처리 하는 경우.
			return true;
		}
		
		//2. casting - HandlerMethod + DefaultHandler 둘다 오기 떄문에 함부로 casting x
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		//3. Method의 @Auth 받아오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		
		//4. Method에 Auth가 없으면 - @auth x Type에 붙어 있는지 확인(과제)
		if(auth == null) {
			auth = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Auth.class);
		}
		
		//5. 타입이나 메소드 둘아 @Auth가 적용이 안되어 잇는경우.
		if(auth == null) {
			return true;
		}
		//5. 인증 여부 확인(@Auth가 붙어 있기 때문)
		
		HttpSession session = request.getSession();
		if(session == null) {
			//로그인 해야함.
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser==null) {
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		//6.권한 체크를 위해서 role 가져오기("USER","ADMIN")
		String role = auth.value();
		
		
		//7. @Auth의 role이 "USER"인 경우에는  
		//   authUser의 role이 "USER"나 "ADMIN"이든 상관이 없음. 무조건 true?
		if("USER".equals(role)) {
			return true;
		}
		
		//8. @Auth의 role이 ADMIN인 경우에는
		//   반드시 authUser role = "ADMIN" --> 과제
		if("ADMIN".equals(authUser.getrole()) == false) {
			response.sendRedirect(request.getContextPath());
			return false;
		}
		
		//@Auth role = "ADMIN"
		//authUser role = "ADMIN"
		//인증이 된 경우(핸들러 메소드 실행)
		return true;
	}

}

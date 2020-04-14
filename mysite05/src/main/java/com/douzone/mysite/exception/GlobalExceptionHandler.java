package com.douzone.mysite.exception;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.douzone.mysite.dto.JsonResult;
import com.fasterxml.jackson.databind.ObjectMapper;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Log LOG = LogFactory.getLog( GlobalExceptionHandler.class );
	
	@ExceptionHandler(Exception.class)
	public void handleException(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Exception e) throws Exception{
		
		//1. 로깅(logging 파일/DB에 남기기)
//		e.printStackTrace();
		StringWriter errors = new StringWriter(); //버퍼
		e.printStackTrace(new PrintWriter(errors));
		
		//LOGGER.error(errors.toString());
		LOG.error(errors.toString());
		//2. 요청 구분
		// 만약, Json 요청인 경우에는 request header의 Accept에 application/json
		// 만약, HTML요청인 경우에는 request header의 Accept에 text/html
		// 만약, jpeg요청인 경우에는 request header의 Accept에 image/jpeg
		String accept = request.getHeader("accept");
		if(accept.matches(".*application/json.*")) {
			//3. Json 응답
			//header
			response.setStatus(HttpServletResponse.SC_OK);
			
			JsonResult jsonResult = JsonResult.fail(errors.toString());
			String jsonString = new ObjectMapper().writeValueAsString(jsonResult);
			
			OutputStream os = response.getOutputStream();
			os.write(jsonString.getBytes("utf-8"));
			os.close();
		} else {
			//4. 안내페이지 가기(정상종료)
			request.setAttribute("exception", errors.toString());
			request
				.getRequestDispatcher("/WEB-INF/views/error/exception.jsp")
				.forward(request, response);
		}
	}
}

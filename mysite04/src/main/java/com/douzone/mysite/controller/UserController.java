package com.douzone.mysite.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;
import com.douzone.security.Auth;
import com.douzone.security.AuthUser;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/join",method=RequestMethod.GET)
	public String join(@ModelAttribute UserVo vo) {
		return "user/join";
	}
	
	@RequestMapping(value="/join",method=RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo vo, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
//			List<ObjectError> list = result.getAllErrors();
//			for(ObjectError error:list) {
//				System.out.println(error);
//			}
			model.addAllAttributes(result.getModel());
			//어떤 필드에서 에러가 났는지, 메시지 출력. 모든 에러 내용을 jsp로 돌림
			return "user/join";
		}
		userService.join(vo);
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping("/joinsuccess")
	public String joinSuccess() {
		return "user/joinsuccess";
	}
	
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	/*
	 * @RequestMapping(value="/login",method = RequestMethod.POST) public String
	 * login(HttpSession session, @ModelAttribute UserVo vo) { UserVo authUser =
	 * userService.getUser(vo); if(authUser==null) { return "user/login"; }
	 * 
	 * session.setAttribute("authUser",authUser); return "redirect:/"; }
	 */
	
	/*
	 * @RequestMapping(value="/logout") public String logout(HttpSession session) {
	 * /////////////////////////접근제어코드//////////////////////// UserVo authUser =
	 * (UserVo)session.getAttribute("authUser"); if(authUser==null) { return
	 * "redirect:/"; } ////////////////////////////////////////////////////////////
	 * session.removeAttribute("authUser"); session.invalidate(); return
	 * "redirect:/"; }
	 */
	
	@Auth
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(
			@AuthUser UserVo authUser, Model model) {

//		UserVo authUser = (UserVo)session.getAttribute("authUser");
//		if(authUser==null) {
//			return "redirect:/";
//		}
		
		Long no = authUser.getNo();
		
		UserVo vo = userService.findUser(no);
		model.addAttribute("vo", vo);
		return "user/update";
	}
	
	@Auth
	@RequestMapping(value="/update/{bool}", method=RequestMethod.POST)
	public String userUpdate(
			@PathVariable("bool") boolean bool, 
			Model model, 
			@AuthUser UserVo authUser, UserVo vo) {

		vo.setNo(authUser.getNo());
		model.addAttribute("bool", bool);
		
		userService.update(vo);
		authUser.setName(vo.getName());
		return "user/joinsuccess";
	}
	
	
	/*
	 * @ExceptionHandler(Exception.class) public String handleException() { return
	 * "error/exception"; }
	 */
}

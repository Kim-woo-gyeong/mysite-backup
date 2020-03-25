package com.douzone.mysite.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.service.FileUploadService;
import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;
import com.douzone.security.Auth;

@Auth("ADMIN")
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private SiteService siteService;
	
	@Autowired 
	private FileUploadService fileUploadService;
	
	@RequestMapping("")
	public String main(Model model) {
		
		SiteVo siteVo = siteService.getSite();
		model.addAttribute("siteVo", siteVo);
		return "admin/main";
	}
	
	@RequestMapping(value = "/main/update", method = RequestMethod.POST)
	public String update(
			@RequestParam(value = "title", required = true, defaultValue = "") String title,
			@RequestParam(value = "welcomeMessage", required = true, defaultValue = "") String welcomeMessage,
			@RequestParam(value = "description", required = true, defaultValue = "") String description,
			@RequestParam(value = "file") MultipartFile multipartFile) {
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("title",title);
		map.put("welcomeMessage", welcomeMessage);
		map.put("description", description);
		
		String url = fileUploadService.restore(multipartFile);
		map.put("profileURL", url);
		
		
		siteService.updateSite(map);
		return "redirect:/main";
	}
	
	@RequestMapping("/guestbook")
	public String guestbook() {
		return "admin/guestbook";
	}
	
	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}
	
	@RequestMapping("/user")
	public String user() {
		return "admin/user";
	}
}

package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.vo.GuestBookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {
	
	@Autowired
	private GuestbookService bookService;
	
	@RequestMapping("")
	public String index(Model model) {
		List<GuestBookVo> list = bookService.getList();
		model.addAttribute("list", list);
		return "guestbook/index";
	}
	
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public String add(GuestBookVo vo) {
		bookService.insert(vo);
		return "redirect:/guestbook";
	}
	
	@RequestMapping(value="/delete/{no}",method = RequestMethod.GET)
	public String delete(@PathVariable("no") Long no, Model model) {
		model.addAttribute("no", no);
		return "guestbook/delete";
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public String delete(GuestBookVo vo) {
		bookService.delete(vo);
		return "redirect:/guestbook";
	}
	
	@RequestMapping("/spa")
	public String indexSpa() {
		return "guestbook/index-spa";
	}
}

package com.douzone.mysite.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.security.Auth;
import com.douzone.security.AuthUser;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("")
	public String index(
			@RequestParam(value="p", required=true, defaultValue="0") int page,
			@RequestParam(value="kwd", required=true, defaultValue="") String keyword,
			BoardVo vo, Model model) {
		
		List<BoardVo> list = boardService.getList(page, keyword);
		int total = boardService.getTotal(keyword);
		Map<String, Object> map = boardService.paging(page, total);
		
		map.put("kwd", keyword);
		
		model.addAttribute("list", list);
		model.addAllAttributes(map);
		
		return "board/index";
	}
	
	@RequestMapping(value="/view/{no}",method=RequestMethod.GET)
	public String view(@PathVariable("no") Long no, Model model) {
		
		BoardVo vo = new BoardVo();
		vo.setNo(no);
		
		BoardVo boardVo = boardService.viewContents(vo);
		model.addAttribute("list", boardVo);
		return "board/view";
	}
	
	@Auth
	@RequestMapping(value="/delete/{no}",method=RequestMethod.GET)
	public String delete(
			@AuthUser UserVo authUser,
			@PathVariable("no") Long no) {
		
		BoardVo vo = new BoardVo();
		vo.setNo(no);
		
		boardService.deleteContents(vo);
		return "redirect:/board";
	}
	
	@Auth
	@RequestMapping(value="/write",method=RequestMethod.GET)
	public String write() {
		return "board/write";
	}
	
	@Auth
	@RequestMapping(value="/write",method=RequestMethod.POST)
	public String write(@AuthUser UserVo authUser,BoardVo vo) {
		if(vo.getgNo() != null) {
			boardService.orderUp(vo);
		}
		boardService.writeContents(vo);
		
		return "redirect:/board";
	}
	
	@Auth
	@RequestMapping(value="/modify/{no}",method=RequestMethod.GET)
	public String modify(@AuthUser UserVo authUser,@PathVariable("no") Long no, Model model) {
		
		BoardVo vo = new BoardVo();
		vo.setNo(no);
		BoardVo list = boardService.viewContents(vo);
		model.addAttribute("list", list);
		return "board/modify";
	}
	
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	public String modify(BoardVo vo) {
		boardService.modifyContents(vo);
		return "redirect:/board";
	}

	@Auth
	@RequestMapping(value="/reply/{no}",method=RequestMethod.GET)
	public String reply(@AuthUser UserVo authUser,
			@PathVariable("no") Long no, Model model) {
		
		BoardVo vo = new BoardVo();
		vo.setNo(no);
		
		BoardVo boardVo = boardService.viewContents(vo);
		model.addAttribute("vo", boardVo);
		
		return "board/write";
	}
}


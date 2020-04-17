package com.douzone.mysite.controller.api;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.mysite.dto.JsonResult;
import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.vo.GuestBookVo;

@RestController("GuestbookApiController")
//@ResponseBody를 안 붙야도 됨
@RequestMapping("api/guestbook")
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@GetMapping("/list/{no}")
	public JsonResult list(@PathVariable("no") Long startNo) {
		List<GuestBookVo> list = guestbookService.getList(startNo);
		return JsonResult.success(list);
	}
	
	@PostMapping("/add")
	public JsonResult add(@RequestBody GuestBookVo vo) {
		guestbookService.writeMessage(vo);
		vo.setPassword(""); //보안 때문에 password를 지움..?
		return JsonResult.success(vo);
	}
	
	@DeleteMapping("/delete/{no}")
	public JsonResult delete(
				@PathVariable("no") Long no, 
				@RequestParam(value = "password", required=true, defaultValue = "") String password) {
		boolean result = guestbookService.delete(no, password);
		
		return JsonResult.success(result ? no : -1);
	}
}

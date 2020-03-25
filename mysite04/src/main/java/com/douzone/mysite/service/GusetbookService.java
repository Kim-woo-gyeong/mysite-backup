package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GuestBookRepository;
import com.douzone.mysite.vo.GuestBookVo;

@Service
public class GusetbookService {

	@Autowired
	private GuestBookRepository bookRepository;
	
	public List<GuestBookVo> getList() {
		return bookRepository.findAll();
	}

	public int insert(GuestBookVo vo) {
		return bookRepository.insert(vo);
	}

	public Boolean delete(GuestBookVo vo) {
		int result = bookRepository.delete(vo);
		return result == 1;
	}
}

package com.douzone.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	static final int LIST_SIZE = 5;
	
	public List<BoardVo> getList(int page, String kekword){
		
		int pageIndex = page * 5;
		return boardRepository.findAll(pageIndex,kekword,LIST_SIZE);
	}
	
	public int getTotal(String keyword){
		return boardRepository.TotalPage(keyword);
	}
	
	public BoardVo viewContents(BoardVo vo) {
		boardRepository.hitUpdate(vo);
		return boardRepository.findbyNo(vo);
	}

	public Boolean deleteContents(BoardVo vo) {
		int count = boardRepository.delete(vo);
		return count == 1;
	}

	public Boolean writeContents(BoardVo vo) {
		
		int count = boardRepository.insert(vo);
		return count == 1;
	}

	public Boolean modifyContents(BoardVo vo) {
		int count = boardRepository.update(vo);
		return count == 1;
	}

	public Boolean orderUp(BoardVo vo) {
		int count = boardRepository.orderUpdate(vo);
		return count == 1;
	}
	
	public Map<String, Object> paging(int page, int total) {
		Map<String, Object> map = new HashMap<>();
		int firstPage = 0;
		int currentPage = page;
		int endPage = 0;
		int nextPage = 0;
		int prePage = 0;
		int max = 0;
		
		firstPage = (currentPage/LIST_SIZE)*5;
		endPage = firstPage + 4;
		nextPage = currentPage + 1;
		prePage = currentPage - 1;
		if( total % LIST_SIZE == 0) {
			max = total / LIST_SIZE - 1;
		} else {
			max = total / LIST_SIZE;
		}
		
		map.put("max", max);
		map.put("total", total);
		map.put("currentPage", currentPage);
		map.put("firstPage", firstPage);
		map.put("endPage", endPage);
		map.put("nextPage", nextPage);
		map.put("prePage", prePage);
		
		return map;
	}
}

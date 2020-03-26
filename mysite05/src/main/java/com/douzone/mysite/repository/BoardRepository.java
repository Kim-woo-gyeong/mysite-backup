package com.douzone.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVo;

@Repository
public class BoardRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<BoardVo> findAll(int page, String keyword,int size) {
		Map<String, Object> map = new HashMap<>();
		map.put("pageIndex", page);
		map.put("kwd",keyword);
		map.put("size",size);
		
		return sqlSession.selectList("board.findAll", map);
	}

	public BoardVo findbyNo(BoardVo vo) {
		return sqlSession.selectOne("board.findbyNo",vo);
	}

	public int insert(BoardVo vo) {
		return sqlSession.insert("board.insert",vo);
	}

	public int delete(BoardVo vo) {
		return sqlSession.delete("board.delete",vo);
	}

	public int update(BoardVo vo) {
		return sqlSession.update("board.update", vo);
	}

	public void hitUpdate(BoardVo vo) {
		sqlSession.update("board.hitupdate", vo);
	}

	public int orderUpdate(BoardVo vo) {
		return sqlSession.update("board.orderup", vo);
	}
	
	public int TotalPage(String kwd) {
		Map<String, Object> map = new HashMap<>();
		map.put("kwd", kwd);
		return sqlSession.selectOne("board.TotalPage",map);
	}
}

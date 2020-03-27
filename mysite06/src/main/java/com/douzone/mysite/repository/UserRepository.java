package com.douzone.mysite.repository;

import java.util.HashMap;
import java.util.Map;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.UserVo;

@Repository
public class UserRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public Boolean insert(UserVo vo) {
		
		int count = sqlSession.insert("user.insert",vo);
		return count==1;
	}

	public UserVo findByEmailAndPassword(UserVo vo) {
		return sqlSession.selectOne("user.findByEmailAndPassword",vo);
	}

	public UserVo findByEmailAndPassword(String email, String password) {
		Map<String, Object> map = new HashMap<>();
		map.put("email", email);
		map.put("password", password);
		
		return sqlSession.selectOne("user.findByEmailAndPassword2",map);
	}
	
	public UserVo findByNo(Long no) {
		return sqlSession.selectOne("user.findByNo",no);
	}

	public int update(UserVo vo) {
		return sqlSession.update("user.update",vo);
	}
}

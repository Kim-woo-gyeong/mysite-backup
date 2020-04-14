package com.douzone.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.UserRepository;
import com.douzone.mysite.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public Boolean join(UserVo vo) {
		Boolean count = userRepository.insert(vo);
		return count;
	}
	
	public UserVo getUser(UserVo vo) {
		return userRepository.findByEmailAndPassword(vo);
	}
	
	public UserVo findUser(Long no) {
		return userRepository.findByNo(no);
	}

	public boolean update(UserVo vo) {
		int count =  userRepository.update(vo);
		return count == 1;
	}
	
	public boolean existUser(String email) {
		return userRepository.findByEmail(email) != null;
	}
}

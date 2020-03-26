package com.douzone.mysite.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.SiteRepository;
import com.douzone.mysite.vo.SiteVo;

@Service
public class SiteService {

	@Autowired
	private SiteRepository siteRepository;
	
	public SiteVo getSite() {
		return siteRepository.findAll();
	}

	public Boolean updateSite(Map<String, Object> map) {
		int count = siteRepository.update(map);
		return count == 1;
	}

}

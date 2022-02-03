package com.poscoict.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.mysite.repository.SiteRepository;
import com.poscoict.mysite.vo.SiteVo;

@Service
public class SiteService {
	@Autowired
	private SiteRepository siteRepository;

	// 메인 페이지 
	public boolean updateSite(SiteVo siteVo) {
		System.out.println("updatesite- : " + siteVo.toString());
		return siteRepository.updateSite(siteVo)==1;
	}

	public SiteVo getContents() {
		return siteRepository.find();
	}
}

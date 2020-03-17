package com.douzone.mysite.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.vo.SiteVo;

@Service
public class FileUploadService {
	private static final String SAVE_PATH="/mysite-uploads";
	private static final String URL="/images";
	
	@Autowired
	private SiteService siteService;
	
	public String restore(MultipartFile multipartFile) {
		SiteVo vo = siteService.getSite();
		String url = "";
		try {
			if(multipartFile.isEmpty()) {
				url = vo.getProfileURL();
				return url;
			}
			
			String originFileName = multipartFile.getOriginalFilename();
			String extName = originFileName.substring(originFileName.lastIndexOf('.')+1); // 확장자 출력 jpg
			
			String saveFilename = generateSaveFilename(extName);
			long fileSize = multipartFile.getSize();
			
			System.out.println("### " + originFileName );
			System.out.println("### " + fileSize);
			System.out.println("### " + saveFilename);
			
			byte[] fileData = multipartFile.getBytes();
			OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFilename);
			os.write(fileData);
			os.close();
			
			url = URL + "/"+saveFilename; 
			
		} catch(IOException e) {
			throw new RuntimeException("file upload error:"+e);
		}
		
		return url;
	}
	
	private String generateSaveFilename(String extName) {
		String fileName="";
		
		Calendar calendar = Calendar.getInstance();
		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += ("."+extName);
		
		return fileName;
	}

}

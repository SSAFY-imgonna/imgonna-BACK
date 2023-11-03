package com.ssafy.trip.util;

import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import org.springframework.web.multipart.MultipartRequest;

import java.io.File;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
  
public class FileUtil {
	public static final String ENCODING_TYPE = "UTF-8"; // 인코딩 타입
	public static final int MAX_SIZE = 20*1024*1024;	// 최대 업로드 사이즈(20MB)
	public static final String UPLOAD_PATH = "/upload"; // 업로드 상대 경로
	
	// 파일 업로드
	public static MultipartRequest createFile(HttpServletRequest request)throws IOException{
		// 업로드 절대 경로
		String upload = request.getServletContext().getRealPath(UPLOAD_PATH);
		
		// 위 경로의 디렉토리가 존재하지 않으면 새로 생성
		File dir = new File(upload);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
		// new DefaultFileRenamePolicy() : 동일한 이름이 존재하면 새로운 이름이 부여됨
//		return new MultipartRequest(request, upload, MAX_SIZE, ENCODING_TYPE, new DefaultFileRenamePolicy());
		return null;
	}
	
	// 파일 삭제
	public static void removeFile(HttpServletRequest request, String filename) {
		if(filename != null) {
			// 업로드 절대 경로
			String upload = request.getServletContext().getRealPath(UPLOAD_PATH);
			
			File file = new File(upload + "/" + filename);
			if(file.exists()) {
				file.delete();
			}
		}
	}
}
package com.ssafy.imgonna.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Component
public class FileUtil {
	@Value("${file.path}")
	private String UPLOAD_PATH;

	@Autowired
	private ServletContext servletContext;

	// 파일 업로드
	public String uploadFile(MultipartFile file) throws IOException {
		if (!file.isEmpty()) {
			String today = new SimpleDateFormat("yyMMdd").format(new Date());
			String saveFolder = UPLOAD_PATH + File.separator + today;
			String saveFileName = null;
			File folder = new File(saveFolder);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			String originalFileName = file.getOriginalFilename(); // 원본 파일 이름
			if (Optional.ofNullable(originalFileName).isPresent()) {
				saveFileName = UUID.randomUUID() + originalFileName.substring(originalFileName.lastIndexOf('.'));
				file.transferTo(new File(folder, saveFileName));
			}

			return String.format("%s%s%s", today, File.separator,saveFileName);
		}
		// TODO 파일 업로드 관련 예외 터뜨리기
		return null;
	}

	// 파일 삭제
	public void removeFile(String filename) {
		if (filename != null) {
			File file = new File(UPLOAD_PATH + File.separator + filename);
			if (file.exists()) {
				file.delete();
			}
		}
	}
}
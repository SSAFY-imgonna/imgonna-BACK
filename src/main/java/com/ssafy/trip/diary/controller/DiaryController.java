package com.ssafy.trip.diary.controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.trip.accompany.model.dto.AccompanyRequestDto;
import com.ssafy.trip.diary.model.dto.AttractionResponseDto;
import com.ssafy.trip.diary.model.dto.DiaryListResponseDto;
import com.ssafy.trip.diary.model.dto.DiaryRequestDto;
import com.ssafy.trip.diary.service.DiaryService;
import com.ssafy.trip.file.model.dto.FileInfoDto;

@RequestMapping("/diary")
@RestController
public class DiaryController {
	private static final Logger logger = LoggerFactory.getLogger(DiaryController.class);
	
    @Value("${file.path}")
    private String uploadPath;
    
    @Autowired
    private ServletContext servletContext;
    
    @Autowired
	private DiaryService diaryService;

	public DiaryController(DiaryService diaryService) {
		super();
		this.diaryService = diaryService;
	}
	
	@GetMapping("/{title}")
	public ResponseEntity<?> getAttractionListByTitle(@PathVariable("title") String title) {
		logger.info("getAttractionListByTitle title : {}", title);
		
		try {
			List<AttractionResponseDto> attractionList = diaryService.getAttractionListByTitle(title);
			HttpHeaders header = new HttpHeaders();
			header.setContentType(MediaType.APPLICATION_JSON);
			return ResponseEntity
					.status(HttpStatus.OK)
					.headers(header)
					.body(attractionList);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}	

    @PostMapping
    public ResponseEntity<?> createAccompany(DiaryRequestDto diaryRequestDto,
    		@RequestPart(required=false) MultipartFile[] upfile, HttpSession session) throws Exception {
    	logger.debug("AccompanyRequestDto : {}", diaryRequestDto);
        
        try {
        	if(upfile != null) {
	        	// 이미지 업로드
	        	uploadFiles(diaryRequestDto, upfile);
        	}
        	// 동행 글 추가
        	diaryService.createDiary(diaryRequestDto);
        	return ResponseEntity
        			.status(HttpStatus.OK)
        			.build();
        } catch (Exception e) {
        	return exceptionHandling(e);
        }
    }
    
    @GetMapping
    public ResponseEntity<?> getDiaryList(@RequestParam Map<String, String> map) {
        try {
            DiaryListResponseDto diaryList = diaryService.getDiaryList(map);
            
            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.APPLICATION_JSON);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .headers(header)
                    .body(diaryList);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    
    private void uploadFiles(DiaryRequestDto diaryRequestDto, MultipartFile[] files) throws IOException {
        // FileUpload 관련 설정
//        logger.debug("uploadPath : {}, uploadImagePath : {}, uploadFilePath : {}", uploadPath, uploadImagePath, uploadFilePath);
        logger.debug("MultipartFile.isEmpty : {}", files[0].isEmpty());
        if (!files[0].isEmpty()) { // 파일 1개라도 업로드했다면
            String realPath = servletContext.getRealPath("/upload");
            String today = new SimpleDateFormat("yyMMdd").format(new Date());
            String saveFolder = uploadPath + File.separator + today;
            logger.debug("저장 폴더 : {}", saveFolder);
            File folder = new File(saveFolder);
            if (!folder.exists()) // 해당 폴더(upload 및 날짜별 폴더)가 존재하지 않을경우, 생성해줌
                folder.mkdirs();
            List<FileInfoDto> fileInfos = new ArrayList<FileInfoDto>();
            for (MultipartFile mfile : files) {
                FileInfoDto fileInfoDto = new FileInfoDto();
                String originalFileName = mfile.getOriginalFilename(); // 원본 파일 이름
                if (!originalFileName.isEmpty()) {
                    // java.util.UUID 클래스를 사용하여 UUID(Universally Unique Identifier)를 생성하고 문자열로 반환
                    String saveFileName = UUID.randomUUID().toString()
                            + originalFileName.substring(originalFileName.lastIndexOf('.')); // 파일 확장자([ex].jpg)만 가져옴
                    fileInfoDto.setSaveFolder(today); // 해당 날짜별 폴더에 저장되어있음
                    fileInfoDto.setOriginalFile(originalFileName);
                    fileInfoDto.setSaveFile(saveFileName);
                    logger.debug("원본 파일 이름 : {}, 실제 저장 파일 이름 : {}", mfile.getOriginalFilename(), saveFileName);
                    mfile.transferTo(new File(folder, saveFileName));
                }
                fileInfos.add(fileInfoDto);
            }
            diaryRequestDto.setFileInfos(fileInfos);
        }
    }
    
    private ResponseEntity<String> exceptionHandling(Exception e) {
        e.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error : " + e.getMessage());
    }	
}

package com.ssafy.trip.diary.controller;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trip.diary.model.dto.AttractionResponseDto;
import com.ssafy.trip.diary.service.DiaryService;

@RequestMapping("/diary")
@RestController
public class DiaryController {
	private static final Logger logger = LoggerFactory.getLogger(DiaryController.class);
	
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
	
    private ResponseEntity<String> exceptionHandling(Exception e) {
        e.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error : " + e.getMessage());
    }	
}

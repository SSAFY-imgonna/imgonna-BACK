package com.ssafy.trip.accompany.comment.controller;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trip.accompany.comment.controller.AccompanyCommentController;
import com.ssafy.trip.accompany.comment.model.dto.AccompanyComment;
import com.ssafy.trip.accompany.comment.service.AccompanyCommentService;

@RestController
@RequestMapping("/accompany")
public class AccompanyCommentController {
	private static final Logger logger = LoggerFactory.getLogger(AccompanyCommentController.class);

	private AccompanyCommentService accompanyCommentService;

	public AccompanyCommentController(AccompanyCommentService accompanyCommentService) {
		super();
		this.accompanyCommentService = accompanyCommentService;
	}

	/**
	 * accompany 댓글 작성
	 * @param accompanyComment
	 * @return
	 */
	@PostMapping("/{accompanyNo}/comments")
	public ResponseEntity<?> createAccompanyComment(@PathVariable int accompanyNo, @RequestBody AccompanyComment accompanyComment) {
		logger.info("writeComment accompanyComment - {}", accompanyComment);
		
		try {
			// AccompanyComment 객체에 id 등록해줘야!!!
			
			// AccompanyComment 객체에 현재 accompany 글번호 등록해줘야!!!
			accompanyComment.setAccompanyNo(accompanyNo);
			accompanyCommentService.createAccompanyComment(accompanyComment);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}	
	
	/**
	 * accompany 댓글 목록
	 * @param map
	 * @return
	 */
	@GetMapping("/{accompanyNo}/comments")
	public ResponseEntity<?> getAccompanyCommentList(
			@PathVariable("accompanyNo") String accompanyNo, @RequestParam Map<String, String> map) {
		logger.info("listComment map - {}", map);
		map.put("accompanyNo", accompanyNo);
		
		try {
			List<AccompanyComment> AccompanyCommentList = accompanyCommentService.getAccompanyCommentList(map);
			HttpHeaders header = new HttpHeaders();
			header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
			return ResponseEntity.ok().headers(header).body(AccompanyCommentList);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	/**
	 * accompany 댓글 상세 => 댓글 수정에 사용됨
	 * @param commentNo
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/{accompanyNo}/comments/{commentNo}")
	public ResponseEntity<AccompanyComment> getAccompanyComment(@PathVariable int commentNo) throws Exception {
		logger.info("getAccompanyComment - 호출 : " + commentNo);
		
		return new ResponseEntity<AccompanyComment>(accompanyCommentService.getAccompanyComment(commentNo), HttpStatus.OK);
	}

	
	/**
	 * accompany 댓글 수정 
	 * @param accompanyComment
	 * @return
	 * @throws Exception
	 */
	@PutMapping("/{accompanyNo}/comments/{commentNo}")
	public ResponseEntity<String> modifyAccompanyComment(@PathVariable int commentNo, @RequestBody AccompanyComment accompanyComment) throws Exception {
		logger.info("modifyAccompanyComment - 호출 {}", accompanyComment);
		
		// AccompanyComment 객체에 id 등록해줘야!!!
		
		// AccompanyComment 객체에 현재 accompany 댓글번호 등록해줘야!!!
		accompanyComment.setCommentNo(commentNo);
		accompanyCommentService.modifyAccompanyComment(accompanyComment);
		return ResponseEntity.ok().build();
	}
	
	/**
	 * accompany 댓글 삭제
	 * @param commentNo
	 * @return
	 * @throws Exception
	 */
	@DeleteMapping("/{accompanyNo}/comments/{commentNo}")
	public ResponseEntity<String> deleteAccompanyComment(@PathVariable int commentNo) throws Exception {
		logger.info("deleteAccompanyComment - 호출");
		
		accompanyCommentService.deleteAccompanyComment(commentNo);
		return ResponseEntity.ok().build();
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}	
}

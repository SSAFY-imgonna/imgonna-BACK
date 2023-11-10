package com.ssafy.trip.qna.comment.controller;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trip.qna.comment.model.QnaComment;
import com.ssafy.trip.qna.comment.service.QnaCommentService;

@RestController
@RequestMapping("/qna")
public class QnaCommentController {
	private static final Logger logger = LoggerFactory.getLogger(QnaCommentController.class);
//	private static final String SUCCESS = "success";
//	private static final String FAIL = "fail";
	
	private QnaCommentService qnaCommentService;
	
	public QnaCommentController(QnaCommentService qnaCommentService) {
		super();
		this.qnaCommentService = qnaCommentService;
	}
	
	/**
	 * qna 댓글 작성
	 * @param qnaComment
	 * @return
	 */
	@PostMapping("/{qnaNo}/comments")
	public ResponseEntity<?> createQnaComment(@PathVariable int qnaNo, @RequestBody QnaComment qnaComment) {
		logger.info("writeComment qnaComment - {}", qnaComment);
		
		try {
			// QnaComment 객체에 id 등록해줘야!!!
			
			// QnaComment 객체에 현재 qna 글번호 등록해줘야!!!
			qnaComment.setQnaNo(qnaNo);
			qnaCommentService.createQnaComment(qnaComment);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}	
	
	/**
	 * qna 댓글 목록
	 * @param map
	 * @return
	 */
	@GetMapping("/{qnaNo}/comments")
	public ResponseEntity<?> getQnaCommentList(
			@PathVariable("qnaNo") String qnaNo, @RequestParam Map<String, String> map) {
		logger.info("listComment map - {}", map);
		map.put("qnaNo", qnaNo);
		
		try {
			List<QnaComment> QnaCommentList = qnaCommentService.getQnaCommentList(map);
			HttpHeaders header = new HttpHeaders();
			header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
			return ResponseEntity.ok().headers(header).body(QnaCommentList);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	/**
	 * qna 댓글 상세 => 댓글 수정에 사용됨
	 * @param commentNo
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/{qnaNo}/comments/{commentNo}")
	public ResponseEntity<QnaComment> getQnaComment(@PathVariable int commentNo) throws Exception {
		logger.info("getQnaComment - 호출 : " + commentNo);
		
		return new ResponseEntity<QnaComment>(qnaCommentService.getQnaComment(commentNo), HttpStatus.OK);
	}

	
	/**
	 * qna 댓글 수정 
	 * @param qnaComment
	 * @return
	 * @throws Exception
	 */
	@PutMapping("/{qnaNo}/comments/{commentNo}")
	public ResponseEntity<String> modifyQnaComment(@PathVariable int commentNo, @RequestBody QnaComment qnaComment) throws Exception {
		logger.info("modifyQnaComment - 호출 {}", qnaComment);
		
		// QnaComment 객체에 id 등록해줘야!!!
		
		// QnaComment 객체에 현재 qna 댓글번호 등록해줘야!!!
		qnaComment.setCommentNo(commentNo);
		qnaCommentService.modifyQnaComment(qnaComment);
		return ResponseEntity.ok().build();
	}
	
	/**
	 * qna 댓글 삭제
	 * @param commentNo
	 * @return
	 * @throws Exception
	 */
	@DeleteMapping("/{qnaNo}/comments/{commentNo}")
	public ResponseEntity<String> deleteQnaComment(@PathVariable int commentNo) throws Exception {
		logger.info("deleteQnaComment - 호출");
		
		qnaCommentService.deleteQnaComment(commentNo);
		return ResponseEntity.ok().build();
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

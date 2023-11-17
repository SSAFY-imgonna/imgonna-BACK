package com.ssafy.imgonna.qna.comment.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.imgonna.qna.comment.mapper.QnaCommentMapper;
import com.ssafy.imgonna.qna.comment.model.QnaComment;

@Service
public class QnaCommentSeviceImpl implements QnaCommentService {

	private QnaCommentMapper qnaCommentMapper;

	@Autowired
	public QnaCommentSeviceImpl(QnaCommentMapper qnaCommentMapper) {
		super();
		this.qnaCommentMapper = qnaCommentMapper;
	}
	
	@Override
	public void createQnaComment(QnaComment qnaComment) throws Exception {
		qnaCommentMapper.createQnaComment(qnaComment);
	}

	@Override
	public List<QnaComment> getQnaCommentList(Map<String, String> map) throws Exception {
//		댓글 목록 관련 부분 주석처리
//		Map<String, Object> param = new HashMap<String, Object>();
//		param.put("word", map.get("word") == null ? "" : map.get("word"));
//		int currentPage = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
//		int sizePerPage = Integer.parseInt(map.get("spp") == null ? "20" : map.get("spp"));
//		int start = currentPage * sizePerPage - sizePerPage;
//		param.put("start", start);
//		param.put("listsize", sizePerPage);

		List<QnaComment> qnaCommentList = qnaCommentMapper.getQnaCommentList(map);

		return qnaCommentList;
	}

	@Override
	public QnaComment getQnaComment(int commentNo) throws Exception {
		return qnaCommentMapper.getQnaComment(commentNo);
	}

	@Override
	public void modifyQnaComment(QnaComment qnaComment) throws Exception {
		qnaCommentMapper.modifyQnaComment(qnaComment);
	}

	@Override
	public void deleteQnaComment(int commentNo) throws Exception {
		qnaCommentMapper.deleteQnaComment(commentNo);
	}

}

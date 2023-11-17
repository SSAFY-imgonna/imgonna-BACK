package com.ssafy.imgonna.qna.comment.service;

import java.util.List;
import java.util.Map;

import com.ssafy.imgonna.qna.comment.model.QnaComment;

public interface QnaCommentService {

	void createQnaComment(QnaComment qnaComment) throws Exception;

	List<QnaComment> getQnaCommentList(Map<String, String> map) throws Exception;

	QnaComment getQnaComment(int commentNo) throws Exception;

	void modifyQnaComment(QnaComment qnaComment) throws Exception;

	void deleteQnaComment(int commentNo) throws Exception;

}

package com.ssafy.trip.accompany.comment.service;

import java.util.List;
import java.util.Map;

import com.ssafy.trip.accompany.comment.model.dto.AccompanyComment;


public interface AccompanyCommentService {
	void createAccompanyComment(AccompanyComment accompanyComment) throws Exception;

	List<AccompanyComment> getAccompanyCommentList(Map<String, String> map) throws Exception;

	AccompanyComment getAccompanyComment(int commentNo) throws Exception;

	void modifyAccompanyComment(AccompanyComment accompanyComment) throws Exception;

	void deleteAccompanyComment(int commentNo) throws Exception;
}

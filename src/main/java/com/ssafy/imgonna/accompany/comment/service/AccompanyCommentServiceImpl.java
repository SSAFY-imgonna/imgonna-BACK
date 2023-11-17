package com.ssafy.imgonna.accompany.comment.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.imgonna.accompany.comment.model.dto.AccompanyComment;
import com.ssafy.imgonna.accompany.comment.model.mapper.AccompanyCommentMapper;

@Service
public class AccompanyCommentServiceImpl implements AccompanyCommentService {
	
	private AccompanyCommentMapper accompanyCommentMapper;
	
	@Autowired
	public AccompanyCommentServiceImpl(AccompanyCommentMapper accompanyCommentMapper) {
		super();
		this.accompanyCommentMapper = accompanyCommentMapper;
	}

	@Override
	public void createAccompanyComment(AccompanyComment accompanyComment) throws Exception {
		accompanyCommentMapper.createAccompanyComment(accompanyComment);
	}

	@Override
	public List<AccompanyComment> getAccompanyCommentList(Map<String, String> map) throws Exception {
//		댓글 목록 관련 부분 주석처리
//		Map<String, Object> param = new HashMap<String, Object>();
//		param.put("word", map.get("word") == null ? "" : map.get("word"));
//		int currentPage = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
//		int sizePerPage = Integer.parseInt(map.get("spp") == null ? "20" : map.get("spp"));
//		int start = currentPage * sizePerPage - sizePerPage;
//		param.put("start", start);
//		param.put("listsize", sizePerPage);
		
		List<AccompanyComment> accompanyCommentList = accompanyCommentMapper.getAccompanyCommentList(map);
		
		return accompanyCommentList;
	}

	@Override
	public AccompanyComment getAccompanyComment(int commentNo) throws Exception {
		return accompanyCommentMapper.getAccompanyComment(commentNo);
	}

	@Override
	public void modifyAccompanyComment(AccompanyComment accompanyComment) throws Exception {
		accompanyCommentMapper.modifyAccompanyComment(accompanyComment);
	}

	@Override
	public void deleteAccompanyComment(int commentNo) throws Exception {
		accompanyCommentMapper.deleteAccompanyComment(commentNo);
	}

}

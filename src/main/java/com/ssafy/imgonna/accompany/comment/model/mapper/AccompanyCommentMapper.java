package com.ssafy.imgonna.accompany.comment.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.imgonna.accompany.comment.model.dto.AccompanyComment;

@Mapper
public interface AccompanyCommentMapper {

	void createAccompanyComment(AccompanyComment accompanyComment) throws SQLException;

	List<AccompanyComment> getAccompanyCommentList(Map<String, String> map) throws SQLException;

	AccompanyComment getAccompanyComment(int commentNo) throws SQLException;

	void modifyAccompanyComment(AccompanyComment accompanyComment) throws SQLException;

	void deleteAccompanyComment(int commentNo) throws SQLException;	
}

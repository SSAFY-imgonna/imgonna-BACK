package com.ssafy.imgonna.qna.comment.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.imgonna.qna.comment.model.QnaComment;

@Mapper
public interface QnaCommentMapper {

	void createQnaComment(QnaComment qnaComment) throws SQLException;

	List<QnaComment> getQnaCommentList(Map<String, String> map) throws SQLException;

	QnaComment getQnaComment(int commentNo) throws SQLException;

	void modifyQnaComment(QnaComment qnaComment) throws SQLException;

	void deleteQnaComment(int commentNo) throws SQLException;

}

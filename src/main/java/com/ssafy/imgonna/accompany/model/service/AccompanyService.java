package com.ssafy.imgonna.accompany.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.imgonna.accompany.model.dto.Accompany;
import com.ssafy.imgonna.accompany.model.dto.AccompanyRequestDto;
import com.ssafy.imgonna.accompany.model.dto.AccompanyResponseDto;

public interface AccompanyService {
	// 동행 글 작성
	void createAccompany(AccompanyRequestDto accompanyRequestDto);

	// 동행 글 목록
	List<Accompany> getAccompanyList(Map<String, String> map);

	// 동행 글 조회수 증가
	void updateHit(int accompanyNo);

	// 동행 글 상세
	AccompanyResponseDto getAccompanyByAccompanyNo(int accompanyNo);
	
	// 동행 글 수정
	void modifyAccompany(AccompanyRequestDto accompanyRequestDto, Map<String, String> map) throws SQLException;
	
	// 동행 글 삭제
	void deleteAccompany(int accompanyNo, String uploadPath);
    

	
//	/** 이미 신청되어있는지 여부 */
//	int isJoin( Map<String, String> map);
//
//	/** 신청 */
//	void join(Map<String, String> map);
//	
//	/** 신청 취소하기 */
//	void joinCancel(Map<String, String> map);
	
//	/** 댓글 목록 */
//	List<AccompanyCommDto> getCommList(int accompanyNo);
//	/** 댓글 작성 */
//	int createComm(AccompanyCommDto dto);
//	/** 댓글 수정 */
//	int modifyComm(AccompanyCommDto dto);
//	/** 댓글 삭제 */
//	int deleteComment(int commentNo);
//	/** 댓글 개수 세기 */
//	int commentCount(int accompanyNo);


	
}

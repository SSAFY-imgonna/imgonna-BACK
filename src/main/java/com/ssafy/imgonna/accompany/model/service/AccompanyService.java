package com.ssafy.imgonna.accompany.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.imgonna.accompany.model.dto.Accompany;
import com.ssafy.imgonna.accompany.model.dto.AccompanyListResponseDto;
import com.ssafy.imgonna.accompany.model.dto.AccompanyRequestDto;
import com.ssafy.imgonna.accompany.model.dto.AccompanyResponseDto;

public interface AccompanyService {
	// 동행 글 작성
	void createAccompany(AccompanyRequestDto accompanyRequestDto);

	// 동행 글 목록
	AccompanyListResponseDto getAccompanyList(Map<String, String> map);

	// 동행 글 조회수 증가
	void updateHit(int accompanyNo);

	// 동행 글 상세
	AccompanyResponseDto getAccompanyByAccompanyNo(int accompanyNo);
	
	// 동행 글 수정
	void modifyAccompany(AccompanyRequestDto accompanyRequestDto, Map<String, String> map) throws SQLException;
	
	// 동행 글 삭제
	void deleteAccompany(int accompanyNo, String uploadPath);
    
	// 동행 신청 여부
	int isJoin( Map<String, String> map);

	// 동행 신청
	void join(Map<String, String> map);
		
	// 동행 신청 취소
	void joinCancel(Map<String, String> map);

}

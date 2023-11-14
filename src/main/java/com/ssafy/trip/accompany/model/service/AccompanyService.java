package com.ssafy.trip.accompany.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.trip.accompany.model.dto.Accompany;
import com.ssafy.trip.accompany.model.dto.AccompanyRequestDto;
import com.ssafy.trip.file.model.dto.FileInfoDto;

public interface AccompanyService {
	// 동행 글 목록
	List<Accompany> getAccompanyList(Map<String, String> map);
	
	// 업로드 파일 목록
//	List<FileInfoDto> fileInfoList(int accompanyNo);

	// 동행 글 작성
	void createAccompany(AccompanyRequestDto accompanyRequestDto);
	
//	// 동행 글 상세
//	Accompany getAccompanyByAccompanyNo(int accompanyNo);
//
//	// 동행 글 조회수 증가
//	void updateHit(int accompanyNo);
//	
//	// 동행 글 삭제
//	void deleteAccompany(int accompanyNo, String uploadPath);
//
//	// 동행 글 수정
//    void modifyAccompany(Accompany accompanyDto, Map<String, String> map) throws SQLException;
//
//    
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

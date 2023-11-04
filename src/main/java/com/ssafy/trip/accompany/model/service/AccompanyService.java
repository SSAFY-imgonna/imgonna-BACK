package com.ssafy.trip.accompany.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.trip.accompany.model.AccompanyDto;
import com.ssafy.trip.file.model.dto.FileInfoDto;

public interface AccompanyService {
	/** 글 목록 */
	List<AccompanyDto> list(Map<String, String> map);

	List<FileInfoDto> fileInfoList(int accompanyNo);

	/** 글 작성 */
	void write(AccompanyDto accompanyDto);
	
	/** 글 상세 */
	AccompanyDto getAccompanyByAccompanyNo(int accompanyNo);

	/** 글 수정*/
//	void modifyAccompany(AccompanyDto accompanyDto);
	
	/** 글 삭제*/
	void deleteAccompany(int accompanyNo, String uploadPath);

    void modifyAccompany(AccompanyDto accompanyDto, Map<String, String> map) throws SQLException;


//	/** 조회수 증가 */
//	int updateHit(int accompanyNo);

	//	/** 이미 신청되어있는지 여부 */
//	int isJoin(int accompanyNo, String userId);
//	/** 신청 */
//	int join(int accompanyNo, String userId);
//	/** 신청 취소하기 */
//	int joinCancel(int accompanyNo, String userId);
	
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
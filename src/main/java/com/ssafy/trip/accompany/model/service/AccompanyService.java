package com.ssafy.trip.accompany.model.service;

import java.util.List;

import com.ssafy.accompany.model.AccompanyCommDto;
import com.ssafy.accompany.model.AccompanyDto;

public interface AccompanyService {
	/** 글 작성 */
	int write(AccompanyDto accompanyDto);
	/** 글 목록 */
	List<AccompanyDto> list();
	/** 글 상세 */
	AccompanyDto view(int accompanyNo);
	/** 조회수 증가 */
	int updateHit(int accompanyNo);
	/** 이미 신청되어있는지 여부 */
	int isJoin(int accompanyNo, String userId);
	/** 신청 */
	int join(int accompanyNo, String userId);
	/** 신청 취소하기 */
	int joinCancel(int accompanyNo, String userId);
	
	/** 댓글 목록 */
	List<AccompanyCommDto> getCommList(int accompanyNo);
	/** 댓글 작성 */
	int createComm(AccompanyCommDto dto);
	/** 댓글 수정 */
	int modifyComm(AccompanyCommDto dto);
	/** 댓글 삭제 */
	int deleteComment(int commentNo);
	/** 댓글 개수 세기 */
	int commentCount(int accompanyNo);


	
}

package com.ssafy.trip.accompany.dao;

import com.ssafy.trip.accompany.model.AccompanyDto;

import java.util.List;


public interface AccompanyDao {
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
}

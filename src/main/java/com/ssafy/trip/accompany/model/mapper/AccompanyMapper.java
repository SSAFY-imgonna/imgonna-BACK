package com.ssafy.trip.accompany.model.mapper;

import com.ssafy.trip.accompany.model.AccompanyDto;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccompanyMapper {
	/** 글 목록 */
	List<AccompanyDto> list(Map<String, String> map);

	/** 글 작성 */
	int write(AccompanyDto accompanyDto);
	
	/** 파일 등록 */
	void registerFile(AccompanyDto accompanyDto);
	
	/** 글 상세 */
	AccompanyDto getAccompanyByAccompanyNo(int accompanyNo);
	
//	/** 조회수 증가 */
//	int updateHit(int accompanyNo);
//	/** 이미 신청되어있는지 여부 */
//	int isJoin(int accompanyNo, String userId);
//	/** 신청 */
//	int join(int accompanyNo, String userId);	
}

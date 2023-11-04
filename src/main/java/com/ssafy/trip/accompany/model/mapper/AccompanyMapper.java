package com.ssafy.trip.accompany.model.mapper;

import com.ssafy.trip.accompany.model.AccompanyDto;
import com.ssafy.trip.accompany.model.FileInfoDto;

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
	
	/** 글 수정 */
//	void modifyAccompany(AccompanyDto accompanyDto) throws SQLException;

	/** 파일 삭제 */
	void deleteFile(int accompanyNo);

	/** 글 삭제 */
	void deleteAccompany(int accompanyNo);
	
	/** 파일 정보 목록 */
	List<FileInfoDto> fileInfoList(int accompanyNo);
	
	
//	/** 조회수 증가 */
//	void updateHit(int accompanyNo);
//	/** 이미 신청되어있는지 여부 */
//	int isJoin(int accompanyNo, String userId);
//	/** 신청 */
//	int join(int accompanyNo, String userId);	
}

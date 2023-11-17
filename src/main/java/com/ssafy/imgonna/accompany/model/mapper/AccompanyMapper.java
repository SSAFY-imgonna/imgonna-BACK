package com.ssafy.imgonna.accompany.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.imgonna.accompany.model.dto.Accompany;
import com.ssafy.imgonna.accompany.model.dto.AccompanyRequestDto;
import com.ssafy.imgonna.accompany.model.dto.AccompanyResponseDto;
import com.ssafy.imgonna.file.model.dto.FileInfoDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccompanyMapper {
	// 동행 글 목록
	List<Accompany> getAccompanyList(Map<String, String> map);

	// 동행 글 작성
	void createAccompany(AccompanyRequestDto accompanyRequestDto);

	// 업로드 파일 목록
	List<FileInfoDto> fileInfoList(int accompanyNo);
	
	// 조회수 증가
	void updateHit(int accompanyNo);	

	// 동행 글 상세
	AccompanyResponseDto getAccompanyByAccompanyNo(int accompanyNo);
	
	// 동행 글 수정
	void modifyAccompany(AccompanyRequestDto accompanyRequestDto) throws SQLException;

	// 동행 글 삭제
	void deleteAccompany(int accompanyNo);

//	/** 이미 신청되어있는지 여부 */
//	int isJoin(Map<String, String> map);
//	
//	/** 신청 */
//	int join(Map<String, String> map);	
//	
//	/** 현재 모집인원 증가 */
//	void increaseAccompanyNum(Map<String, String> map);
//	
//	/** 신청 취소하기 */
//	int joinCancel(Map<String, String> map);
//	
//	/** 현재 모집인원 감소 */
//	void decreaseAccompanyNum(Map<String, String> map);
}

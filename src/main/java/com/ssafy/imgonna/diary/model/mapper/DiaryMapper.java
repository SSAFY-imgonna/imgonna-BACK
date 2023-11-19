package com.ssafy.imgonna.diary.model.mapper;

import com.ssafy.imgonna.attraction.model.dto.AttractionInfo;
import com.ssafy.imgonna.diary.model.dto.AttractionResponseDto;
import com.ssafy.imgonna.diary.model.dto.DiaryRequestDto;
import com.ssafy.imgonna.diary.model.dto.DiaryResponseDto;
import com.ssafy.imgonna.file.model.dto.FileInfoDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DiaryMapper {
	// 관광지명에 따른 관광지 목록
	List<AttractionResponseDto> getAttractionListByTitle(String title);
	
	// 여행일기 목록
	List<DiaryResponseDto> getDiaryList(Map<String, Object> map);	
	
	// 전체 여행 일기 수
	int getTotalDiaryCount(Map<String, Object> param);
	
	// 여행일기 작성
	void createDiary(DiaryRequestDto diaryRequestDto);
	
	// 업로드 파일 목록
	List<FileInfoDto> fileInfoList(int diaryNo);

	// 여행일기 상세
	DiaryResponseDto getDiaryByDiaryNo(int diaryNo);
	
	// 여행일기 수정
	void modifyDiary(DiaryRequestDto diaryRequestDto);

	// 여행일기 삭제
	void deleteDiary(int diaryNo);

	List<String> getRankList();

	List<AttractionInfo> getAttractionListById(String id);
}

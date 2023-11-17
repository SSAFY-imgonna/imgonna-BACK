package com.ssafy.trip.diary.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.diary.model.dto.DiaryRequestDto;
import com.ssafy.trip.diary.model.dto.DiaryResponseDto;
import com.ssafy.trip.accompany.model.dto.Accompany;
import com.ssafy.trip.diary.model.dto.AttractionResponseDto;
import com.ssafy.trip.diary.model.dto.Diary;
import com.ssafy.trip.file.model.dto.FileInfoDto;

@Mapper
public interface DiaryMapper {
	// 관광지명에 따른 관광지 목록
	List<AttractionResponseDto> getAttractionListByTitle(String title);
	
//	// 여행일기 목록
//	List<Diary> getDiaryList(Map<String, String> map);	
	
	// 여행일기 작성
	void createDiary(DiaryRequestDto diaryRequestDto);
	
	// 업로드 파일 목록
	List<FileInfoDto> fileInfoList(int diaryNo);
	
//	// 조회수 증가
//	void updateHit(int diaryNo);	
//
//	// 여행일기 상세
//	DiaryResponseDto getDiaryByDiaryNo(int diaryNo);
//	
//	// 여행일기 수정
//	void modifyDiary(DiaryRequestDto diaryRequestDto);
//
//	// 여행일기 삭제
//	void deleteDiary(int diaryNo);
}

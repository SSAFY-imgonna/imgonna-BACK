package com.ssafy.trip.diary.service;

import java.util.List;
import java.util.Map;

import com.ssafy.trip.diary.model.dto.AttractionResponseDto;
import com.ssafy.trip.diary.model.dto.Diary;
import com.ssafy.trip.diary.model.dto.DiaryListResponseDto;
import com.ssafy.trip.diary.model.dto.DiaryRequestDto;
import com.ssafy.trip.diary.model.dto.DiaryResponseDto;
import com.ssafy.trip.file.model.dto.FileInfoDto;

public interface DiaryService {
	// 관광지명에 따른 관광지 목록
	List<AttractionResponseDto> getAttractionListByTitle(String title);
	
	// 여행일기 목록
	DiaryListResponseDto getDiaryList(Map<String, String> map);	
	
	// 여행일기 작성
	void createDiary(DiaryRequestDto diaryRequestDto);
	
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

package com.ssafy.imgonna.diary.service;

import com.ssafy.imgonna.diary.model.dto.*;

import java.util.List;
import java.util.Map;

public interface DiaryService {
	// 관광지명에 따른 관광지 목록
	List<AttractionResponseDto> getAttractionListByTitle(String title);
	
	// 여행일기 목록
	DiaryListResponseDto getDiaryList(Map<String, String> map);	
	
	// 여행일기 작성
	void createDiary(DiaryRequestDto diaryRequestDto);

	// 여행일기 상세
	DiaryResponseDto getDiaryByDiaryNo(int diaryNo);
	
	// 여행일기 수정
	void modifyDiary(DiaryRequestDto diaryRequestDto, Map<String, String> map);

	// 여행일기 삭제
	void deleteDiary(int diaryNo, String uploadPath);

    List<RankResponseDto> getRankList();
}

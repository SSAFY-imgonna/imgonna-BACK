package com.ssafy.trip.diary.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.trip.diary.model.dto.AttractionResponseDto;
import com.ssafy.trip.diary.model.dto.Diary;
import com.ssafy.trip.diary.model.dto.DiaryRequestDto;
import com.ssafy.trip.diary.model.dto.DiaryResponseDto;
import com.ssafy.trip.diary.model.mapper.DiaryMapper;
import com.ssafy.trip.file.model.dto.FileInfoDto;
import com.ssafy.trip.file.model.service.FileService;

@Service
public class DiaryServiceImpl implements DiaryService {
	private DiaryMapper diaryMapper;
	private FileService fileService;
	
	@Autowired
	public DiaryServiceImpl(DiaryMapper diaryMapper, FileService fileService) {
		super();
		this.diaryMapper = diaryMapper;
		this.fileService = fileService;
	}
	
	// 관광지명에 따른 관광지 목록
	@Override
	public List<AttractionResponseDto> getAttractionListByTitle(String title) {
		return diaryMapper.getAttractionListByTitle(title);
	}

//	// 여행일기 목록
//	@Override
//	public List<Diary> getDiaryList(Map<String, String> map) {
//
//	}

	// 여행일기 작성
	@Override
	@Transactional
	public void createDiary(DiaryRequestDto diaryRequestDto) {
		// 여행일기 작성
		diaryMapper.createDiary(diaryRequestDto);
		// 이미지 저장
    	List<FileInfoDto> fileInfos = diaryRequestDto.getFileInfos();
    	if (fileInfos != null && !fileInfos.isEmpty()) { // 파일 정보가 있다면
    		fileService.registerFile(diaryRequestDto);
    	}
	}

//	// 조회수 증가
//	@Override
//	public void updateHit(int diaryNo) {
//
//	}
//
//	// 여행일기 상세
//	@Override
//	public DiaryResponseDto getDiaryByDiaryNo(int diaryNo) {
//
//	}
//
//	// 여행일기 수정
//	@Override
//	public void modifyDiary(DiaryRequestDto diaryRequestDto) {
//
//	}
//
//	// 여행일기 삭제
//	@Override
//	public void deleteDiary(int diaryNo) {
//
//	}

}

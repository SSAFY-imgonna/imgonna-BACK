package com.ssafy.trip.diary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.trip.diary.model.dto.AttractionResponseDto;
import com.ssafy.trip.diary.model.mapper.DiaryMapper;

@Service
public class DiaryServiceImpl implements DiaryService {
	private DiaryMapper diaryMapper;
	
	@Autowired
	public DiaryServiceImpl(DiaryMapper diaryMapper) {
		super();
		this.diaryMapper = diaryMapper;
	}

	@Override
	public List<AttractionResponseDto> getAttractionListByTitle(String title) {
		return diaryMapper.getAttractionListByTitle(title);
	}

}

package com.ssafy.trip.diary.service;

import java.util.List;

import com.ssafy.trip.diary.model.dto.AttractionResponseDto;

public interface DiaryService {
	List<AttractionResponseDto> getAttractionListByTitle(String title);
}

package com.ssafy.trip.diary.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.diary.model.dto.AttractionResponseDto;

@Mapper
public interface DiaryMapper {
	List<AttractionResponseDto> getAttractionListByTitle(String title);
}

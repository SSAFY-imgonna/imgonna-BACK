package com.ssafy.trip.enjoytrip.model.service;

import com.ssafy.trip.enjoytrip.model.AttractionInfoDto;

import java.util.List;
import java.util.Map;

public interface AttractionService {
	List<AttractionInfoDto> getAttractionInfo(Map<String, Integer> map);
}

package com.ssafy.trip.enjoytrip.model.dao;

import com.ssafy.trip.enjoytrip.model.AttractionInfoDto;

import java.util.List;
import java.util.Map;


public interface AttractionDao {

	List<AttractionInfoDto> getAttractionInfo(Map<String, Integer> map);
}

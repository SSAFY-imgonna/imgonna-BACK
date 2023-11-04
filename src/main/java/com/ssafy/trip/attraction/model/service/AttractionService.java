package com.ssafy.trip.attraction.model.service;

import com.ssafy.trip.attraction.model.dto.AttractionInfo;
import com.ssafy.trip.attraction.model.dto.Gugun;
import com.ssafy.trip.attraction.model.dto.Sido;

import java.util.List;
import java.util.Map;

public interface AttractionService {
	List<AttractionInfo> getAttractionInfo(Map<String, Integer> map);

	List<Gugun> getGugunBySidoCode(int sidoCode);

	List<Sido> getSidoList();
}

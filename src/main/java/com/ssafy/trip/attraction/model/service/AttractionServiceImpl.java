package com.ssafy.trip.attraction.model.service;

import com.ssafy.trip.attraction.model.dto.AttractionInfo;
import com.ssafy.trip.attraction.model.dto.Gugun;
import com.ssafy.trip.attraction.model.dto.Sido;
import com.ssafy.trip.attraction.model.mapper.AttractionMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AttractionServiceImpl implements AttractionService {

	private AttractionMapper attractionMapper;

	public AttractionServiceImpl(AttractionMapper attractionMapper) {
		this.attractionMapper = attractionMapper;
	}

	@Override
	public List<AttractionInfo> getAttractionInfo(Map<String, Integer> map) {
		return attractionMapper.getAttractionInfo(map);
	}

	@Override
	public List<Gugun> getGugunBySidoCode(int sidoCode) {
		return attractionMapper.getGugunBySidoCode(sidoCode);
	}

	@Override
	public List<Sido> getSidoList() {
		return attractionMapper.getSidoList();
	}

}

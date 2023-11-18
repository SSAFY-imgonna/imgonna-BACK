package com.ssafy.imgonna.attraction.model.service;

import com.ssafy.imgonna.attraction.model.dto.AttractionInfo;
import com.ssafy.imgonna.attraction.model.dto.AttractionRequestDto;
import com.ssafy.imgonna.attraction.model.dto.Gugun;
import com.ssafy.imgonna.attraction.model.dto.Sido;
import com.ssafy.imgonna.attraction.model.mapper.AttractionMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AttractionServiceImpl implements AttractionService {

	private AttractionMapper attractionMapper;

	public AttractionServiceImpl(AttractionMapper attractionMapper) {
		this.attractionMapper = attractionMapper;
	}

	@Override
	public List<AttractionInfo> getAttractionInfo(AttractionRequestDto attractionRequestDto) {
		Map<String, Integer> map = new HashMap<>();
		map.put("sidoCode", attractionRequestDto.getSidoCode());
		map.put("gugunCode", attractionRequestDto.getGugunCode());
		if(attractionRequestDto.getContentTypeId()!=0) {
			map.put("contentTypeId", attractionRequestDto.getContentTypeId());
		}
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

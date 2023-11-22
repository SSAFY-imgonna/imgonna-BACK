package com.ssafy.imgonna.attraction.model.service;

import com.ssafy.imgonna.attraction.model.dto.AttractionInfo;
import com.ssafy.imgonna.attraction.model.dto.AttractionRequestDto;
import com.ssafy.imgonna.attraction.model.dto.Gugun;
import com.ssafy.imgonna.attraction.model.dto.Sido;

import java.util.List;

public interface AttractionService {
	List<AttractionInfo> getAttractionInfo(AttractionRequestDto attractionRequestDto);

	List<AttractionInfo> getAttractionListByContentIdList(List<Integer> contentIdList);
	
	List<Gugun> getGugunBySidoCode(int sidoCode);

	List<Sido> getSidoList();
}

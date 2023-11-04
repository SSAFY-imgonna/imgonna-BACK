package com.ssafy.trip.attraction.model.mapper;

import com.ssafy.trip.attraction.model.dto.AttractionInfo;
import com.ssafy.trip.attraction.model.dto.Gugun;
import com.ssafy.trip.attraction.model.dto.Sido;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface AttractionMapper {

	List<AttractionInfo> getAttractionInfo(Map<String, Integer> map);
	List<Gugun> getGugunBySidoCode(int sidoCode);
	List<Sido> getSidoList();
}

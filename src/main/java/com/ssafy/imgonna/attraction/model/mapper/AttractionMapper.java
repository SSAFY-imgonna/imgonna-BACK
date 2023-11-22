package com.ssafy.imgonna.attraction.model.mapper;

import com.ssafy.imgonna.attraction.model.dto.AttractionInfo;
import com.ssafy.imgonna.attraction.model.dto.Gugun;
import com.ssafy.imgonna.attraction.model.dto.Sido;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface AttractionMapper {

	List<AttractionInfo> getAttractionInfo(Map<String, Integer> map);
	
	List<AttractionInfo> getAttractionListByContentIdList(List<Integer> contentIdList);
	
	List<Gugun> getGugunBySidoCode(int sidoCode);
	
	List<Sido> getSidoList();
}

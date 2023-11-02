package com.ssafy.trip.enjoytrip.model.service;

import com.ssafy.trip.enjoytrip.model.AttractionInfoDto;
import com.ssafy.trip.enjoytrip.model.dao.AttractionDao;
import com.ssafy.trip.enjoytrip.model.dao.AttractionDaoImpl;
import com.ssafy.trip.enjoytrip.model.service.AttractionService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttractionServiceImpl implements AttractionService {

	private AttractionDao attractionDao = AttractionDaoImpl.getInstance();
	
	private static AttractionService instance = new AttractionServiceImpl();
	private AttractionServiceImpl() {}
	
	public static AttractionService getInstance() {
		return instance;
	}

	@Override
	public List<AttractionInfoDto> getAttractionInfo(Map<String, Integer> map) {
		return attractionDao.getAttractionInfo(map);
	}

}

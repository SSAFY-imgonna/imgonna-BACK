package com.ssafy.trip.enjoytrip.model.dao;

import com.ssafy.trip.enjoytrip.model.GugunDto;

import java.util.List;

public interface GugunDao {
	List<GugunDto> getGugunBySidoCode(int sidoCode);
}

package com.ssafy.trip.accompany.service;

import com.ssafy.trip.accompany.dao.AccompanyDao;
import com.ssafy.trip.accompany.dao.AccompanyDaoImpl;
import com.ssafy.trip.accompany.model.AccompanyDto;

import java.util.List;


public class AccompanyServiceImpl implements AccompanyService {
	private AccompanyDao dao = AccompanyDaoImpl.getInstance();
	
	// service에 싱글톤 패턴 적용
	private static AccompanyService instance = new AccompanyServiceImpl();
	private AccompanyServiceImpl() {}
	public static AccompanyService getInstance() {
		return instance;
	}
	
	/** 글 작성 */
	@Override
	public int write(AccompanyDto accompanyDto) {
		return dao.write(accompanyDto);
	}
	
	/** 글 목록 */
	@Override
	public List<AccompanyDto> list() {
		return dao.list();
	}
	
	/** 글 상세 */
	@Override
	public AccompanyDto view(int accompanyNo) {
		return dao.view(accompanyNo);
	}
	
	/** 조회수 증가 */
	@Override
	public int updateHit(int accompanyNo) {
		return dao.updateHit(accompanyNo);
	}
	
	/** 이미 신청되어 있는지 여부 */
	@Override
	public int isJoin(int accompanyNo, String userId) {
		return dao.isJoin(accompanyNo, userId);
	}
	
	/** 신청 */
	@Override
	public int join(int accompanyNo, String userId) {
		return dao.join(accompanyNo, userId);
	}


}

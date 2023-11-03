package com.ssafy.trip.accompany.model.service;

import java.util.List;

import com.ssafy.accompany.model.AccompanyCommDto;
import com.ssafy.accompany.model.AccompanyDto;
import com.ssafy.accompany.dao.AccompanyDao;
import com.ssafy.accompany.dao.AccompanyDaoImpl;

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
	
	/** 신청 취소하기 */
	@Override
	public int joinCancel(int accompanyNo, String userId) {
		// TODO Auto-generated method stub
		return dao.joinCancel(accompanyNo, userId);
	}

	
	/** 댓글 목록 */
	@Override
	public List<AccompanyCommDto> getCommList(int accompanyNo) {
		return dao.getCommList(accompanyNo);
	}
	
	/** 댓글 작성 */
	@Override
	public int createComm(AccompanyCommDto dto) {
		return dao.createComm(dto);
	}
	
	/** 댓글 수정 */
	@Override
	public int modifyComm(AccompanyCommDto dto) {
		return dao.modifyComm(dto);
	}
	
	/** 댓글 삭제 */
	@Override
	public int deleteComment(int commentNo) {
		return dao.deleteComment(commentNo);
	}
	
	/** 댓글 개수 세기 */
	@Override
	public int commentCount(int accompanyNo) {
		return dao.commentCount(accompanyNo);
	}



}

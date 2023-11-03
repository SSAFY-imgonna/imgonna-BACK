package com.ssafy.trip.accompany.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.trip.accompany.model.AccompanyCommDto;
import com.ssafy.trip.accompany.model.AccompanyDto;
import com.ssafy.trip.accompany.model.mapper.AccompanyMapper;

@Service
public class AccompanyServiceImpl implements AccompanyService {
	
	private AccompanyMapper accompanyMapper;
	
	@Autowired
	public AccompanyServiceImpl(AccompanyMapper accompanyMapper) {
		super();
		this.accompanyMapper = accompanyMapper;
	}
	
	/** 글 목록 */
	@Override
	public List<AccompanyDto> list(Map<String, String> map) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		String key = map.get("key");
//		if("userid".equals(key))
//			key = "b.user_id";
		param.put("key", key == null ? "" : key);
		param.put("word", map.get("word") == null ? "" : map.get("word"));
//		int pgNo = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
//		int start = pgNo * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
//		param.put("start", start);
//		param.put("listsize", SizeConstant.LIST_SIZE);

		return accompanyMapper.list(map);
	}

//	/** 글 작성 */
//	@Override
//	public int write(AccompanyDto accompanyDto) {
//		return dao.write(accompanyDto);
//	}
//	
//	
//	/** 글 상세 */
//	@Override
//	public AccompanyDto view(int accompanyNo) {
//		return dao.view(accompanyNo);
//	}
//	
//	/** 조회수 증가 */
//	@Override
//	public int updateHit(int accompanyNo) {
//		return dao.updateHit(accompanyNo);
//	}
//	
//	/** 이미 신청되어 있는지 여부 */
//	@Override
//	public int isJoin(int accompanyNo, String userId) {
//		return dao.isJoin(accompanyNo, userId);
//	}
//	
//	/** 신청 */
//	@Override
//	public int join(int accompanyNo, String userId) {
//		return dao.join(accompanyNo, userId);
//	}
//	
//	/** 신청 취소하기 */
//	@Override
//	public int joinCancel(int accompanyNo, String userId) {
//		// TODO Auto-generated method stub
//		return dao.joinCancel(accompanyNo, userId);
//	}

	
//	/** 댓글 목록 */
//	@Override
//	public List<AccompanyCommDto> getCommList(int accompanyNo) {
//		return dao.getCommList(accompanyNo);
//	}
//	
//	/** 댓글 작성 */
//	@Override
//	public int createComm(AccompanyCommDto dto) {
//		return dao.createComm(dto);
//	}
//	
//	/** 댓글 수정 */
//	@Override
//	public int modifyComm(AccompanyCommDto dto) {
//		return dao.modifyComm(dto);
//	}
//	
//	/** 댓글 삭제 */
//	@Override
//	public int deleteComment(int commentNo) {
//		return dao.deleteComment(commentNo);
//	}
//	
//	/** 댓글 개수 세기 */
//	@Override
//	public int commentCount(int accompanyNo) {
//		return dao.commentCount(accompanyNo);
//	}



}

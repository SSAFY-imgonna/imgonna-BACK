package com.ssafy.imgonna.board.model.dao;

import com.ssafy.imgonna.board.model.BoardDto;

import java.util.List;
import java.util.Map;

/*
- [create]: create도메인(Dto);
- [read]
    - 전체: get도메인List(Map);
    - 단건: get도메인By프로퍼티명(프로퍼티);
- [update]: modify도메인(Dto);
- [delete]: delete도메인(pk);

* service, dao 메서드명 동일
 */
public interface BoardDao {
	
	int createBoard(BoardDto boardDto); // 게시글, 공지글 작성
	
	List<BoardDto> getBoardList(Map<String, Object> param); // 게시글 전체 조회(페이징 처리에 이용)
	
	int getBoardCount(Map<String, Object> param); // 게시글 갯수 조회(페이징 처리에 이용)
	
	BoardDto getBoardByarticleNo(int articleNo); // 게시글, 공지글 단건 조회
	
	int modifyHit(int articleNo); // 게시글, 공지글 조회수 1증가
	
	int modifyBoard(BoardDto boardDto); // 게시글, 공지글 수정
	
	int deleteBoard(int articleNo); // 게시글, 공지글 삭제
}

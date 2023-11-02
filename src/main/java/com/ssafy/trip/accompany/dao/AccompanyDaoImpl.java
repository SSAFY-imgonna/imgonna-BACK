package com.ssafy.trip.accompany.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.trip.accompany.model.AccompanyDto;
import com.ssafy.trip.util.DBUtil;

public class AccompanyDaoImpl implements AccompanyDao {
	// dao에 싱글톤 패턴 적용
	private static AccompanyDao instance = new AccompanyDaoImpl();
	private AccompanyDaoImpl() {}
	public static AccompanyDao getInstance() {
		return instance;
	}
	
	/** 글 작성 */
	@Override
	public int write(AccompanyDto accompanyDto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("insert into accompany (accompany_title, accompany_content, accompany_loc, accompany_total, "
						+ "accompany_date, id, nickname, accompany_photo) \n");
			sql.append("values (?, ?, ?, ?, ?, ?, ?, ?) \n");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, accompanyDto.getAccompanyTitle());
			pstmt.setString(2, accompanyDto.getAccompanyContent());
			pstmt.setString(3, accompanyDto.getAccompanyLoc());
			pstmt.setInt(4, accompanyDto.getAccompanyTotal());
			pstmt.setString(5, accompanyDto.getAccompanyDate());
			pstmt.setString(6, accompanyDto.getId());
			pstmt.setString(7, accompanyDto.getNickname());
			pstmt.setString(8, accompanyDto.getAccompanyPhoto());
			
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt, conn);
		}
	
		return cnt;
	}
	
	/** 글 목록 */
	@Override
	public List<AccompanyDto> list() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<AccompanyDto> list = new ArrayList<>();
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("select * from accompany \n");
			sql.append("order by accompany_no desc \n");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				AccompanyDto dto = new AccompanyDto();
				dto.setAccompanyNo(rs.getInt("accompany_no"));
				dto.setAccompanyTitle(rs.getString("accompany_title"));
				dto.setAccompanyContent(rs.getString("accompany_content"));
				dto.setAccompanyLoc(rs.getString("accompany_loc"));
				dto.setAccompanyDate(rs.getString("accompany_date"));
				dto.setAccompanyNum(rs.getInt("accompany_num"));
				dto.setAccompanyTotal(rs.getInt("accompany_total"));
				dto.setAccompanyPhoto(rs.getString("accompany_photo"));
				dto.setHit(rs.getInt("hit"));
				dto.setUserId(rs.getString("id"));
				dto.setUserNickname(rs.getString("nickname"));
				dto.setRegDate(rs.getString("reg_date"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
		
		return list;
	}
	
	/** 글 상세 */
	@Override
	public AccompanyDto view(int accompanyNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		AccompanyDto dto = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("select * from accompany \n");
			sql.append("where accompany_no=? \n");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, accompanyNo);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new AccompanyDto();
				dto.setAccompanyNo(rs.getInt("accompany_no"));
				dto.setAccompanyTitle(rs.getString("accompany_title"));
				dto.setAccompanyContent(rs.getString("accompany_content"));
				dto.setAccompanyLoc(rs.getString("accompany_loc"));
				dto.setAccompanyDate(rs.getString("accompany_date"));
				dto.setAccompanyNum(rs.getInt("accompany_num"));
				dto.setAccompanyTotal(rs.getInt("accompany_total"));
				dto.setAccompanyPhoto(rs.getString("accompany_photo"));
				dto.setHit(rs.getInt("hit"));
				dto.setUserId(rs.getString("id"));
				dto.setUserNickname(rs.getString("nickname"));
				dto.setRegDate(rs.getString("reg_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
		
		return dto;
	}
	
	/** 조회수 증가 */
	@Override
	public int updateHit(int accompanyNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("update accompany \n");
			sql.append("set hit = hit + 1 \n");
			sql.append("where accompany_no = ?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, accompanyNo);
			
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt, conn);
		}
	
		return cnt;
	}
	
	/** 이미 신청되어있는지 여부 */
	@Override
	public int isJoin(int accompanyNo, String userId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0;
		
		try {
			conn = DBUtil.getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("select * from accompany_join \n");
			sql.append("where accompany_no=? and id=? \n");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, accompanyNo);
			pstmt.setString(2, userId);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				cnt = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
		
		return cnt;
	}
	
	/** 신청 */
	@Override
	public int join(int accompanyNo, String userId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			
			StringBuilder sql = new StringBuilder();
			
			// accompany_join 테이블에 레코드 추가
			sql.append("insert into accompany_join (accompany_no, id) \n");
			sql.append("values (?, ?) \n");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, accompanyNo);
			pstmt.setString(2, userId);
			pstmt.executeUpdate();
			pstmt.close();
			
			// accompany 테이블에 accompany_num 업데이트
			sql = new StringBuilder();
			sql.append("update accompany \n");
			sql.append("set accompany_num = accompany_num + 1 \n");
			sql.append("where accompany_no = ?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, accompanyNo);
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt, conn);
		}
	
		return cnt;
	}

}

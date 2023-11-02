package com.ssafy.trip.enjoytrip.model.dao;

import com.ssafy.trip.enjoytrip.model.GugunDto;
import com.ssafy.trip.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GugunDaoImpl implements GugunDao {
	
	private DBUtil util = DBUtil.getInstance();
	
	private static GugunDao instance = new GugunDaoImpl();
	private GugunDaoImpl() {}

	public static GugunDao getInstance() {
		return instance;
	}

	@Override
	public List<GugunDto> getGugunBySidoCode(int sidoCode) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<GugunDto> list = new ArrayList<>();
		
		try {
			conn = util.getConnection();
			StringBuilder sql = new StringBuilder("select * from gugun \n");
			sql.append("where sido_code = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, sidoCode);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				GugunDto gugunDto = new GugunDto();
				gugunDto.setGugunCode(rs.getInt("gugun_code"));
				gugunDto.setGugunName(rs.getString("gugun_name"));
				gugunDto.setSidoCode(rs.getInt("sido_code"));
				
				list.add(gugunDto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs, pstmt, conn);
		}
		return list;
	}

}

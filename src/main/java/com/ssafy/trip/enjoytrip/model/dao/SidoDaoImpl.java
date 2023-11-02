package com.ssafy.trip.enjoytrip.model.dao;

import com.ssafy.trip.enjoytrip.model.SidoDto;
import com.ssafy.trip.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SidoDaoImpl implements SidoDao {

	private static DBUtil util = DBUtil.getInstance();
	
	private static SidoDao instance = new SidoDaoImpl();
	private SidoDaoImpl() {}
	
	public static SidoDao getInstance() {
		return instance;
	}

	@Override
	public List<SidoDto> getSidoList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SidoDto> list = new ArrayList<>();
		
		try {
			conn = util.getConnection();
			StringBuilder sql = new StringBuilder("select * from sido");
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				SidoDto sidoDto = new SidoDto();
				sidoDto.setSidoCode(rs.getInt("sido_code"));
				sidoDto.setSidoName(rs.getString("sido_name"));
				
				list.add(sidoDto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs, pstmt, conn);
		}
		return list;
	}

}

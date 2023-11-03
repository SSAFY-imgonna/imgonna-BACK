package com.ssafy.trip.enjoytrip.model.dao;

import com.ssafy.trip.enjoytrip.model.AttractionInfoDto;
import com.ssafy.trip.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttractionDaoImpl implements AttractionDao {
	
	private DBUtil util = DBUtil.getInstance();
	
	private static AttractionDao instance = new AttractionDaoImpl();
	private AttractionDaoImpl() {}

	public static AttractionDao getInstance() {
		return instance;
	}

	@Override
	public List<AttractionInfoDto> getAttractionInfo(Map<String, Integer> map) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AttractionInfoDto> list = new ArrayList<>();
		
		try {
			conn = util.getConnection();
			StringBuilder sql = new StringBuilder("select * from attraction_info \n");
			int sidoCode = map.get("sidoCode");
			int gugunCode = map.get("gugunCode");
			int contentTypeId = map.get("contentTypeId");
			int cnt = 0;
			if (sidoCode != 0) cnt++;
			if (gugunCode != 0) cnt++;
			if (contentTypeId != 0) cnt++;
			
			if (cnt > 0) {
				sql.append("where ");
				if (sidoCode != 0) {
					sql.append("sido_code = ? ");
				}
				if (--cnt > 0) {
					sql.append("and ");
				}
				if (gugunCode != 0) {
					sql.append("gugun_code = ? ");
				}
				if (--cnt > 0) {
					sql.append("and ");
				}
				if (contentTypeId != 0) {
					sql.append("content_type_id = ? ");
				}
			}
			pstmt = conn.prepareStatement(sql.toString());
			int idx = 0;
			if (sidoCode != 0) {
				pstmt.setInt(++idx, sidoCode);
			}
			if (gugunCode != 0) {
				pstmt.setInt(++idx, gugunCode);
			}
			if (contentTypeId != 0) {
				pstmt.setInt(++idx, contentTypeId);
			}
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				AttractionInfoDto attractionInfoDto = new AttractionInfoDto();
				attractionInfoDto.setContentId(rs.getInt("content_id"));
				attractionInfoDto.setContentTypeId(rs.getInt("content_type_id"));
				attractionInfoDto.setTitle(rs.getString("title"));
				attractionInfoDto.setAddr1(rs.getString("addr1"));
				attractionInfoDto.setAddr2(rs.getString("addr2"));
				attractionInfoDto.setZipcode(rs.getString("zipcode"));
				attractionInfoDto.setTel(rs.getString("tel"));
				attractionInfoDto.setFirstImage(rs.getString("first_image"));
				attractionInfoDto.setFirstImage2(rs.getString("first_image2"));
				attractionInfoDto.setReadcount(rs.getInt("readcount"));
				attractionInfoDto.setSidoCode(rs.getInt("sido_code"));
				attractionInfoDto.setGugunCode(rs.getInt("gugun_code"));
				attractionInfoDto.setLatitude(rs.getDouble("latitude"));
				attractionInfoDto.setLongitude(rs.getDouble("longitude"));
				attractionInfoDto.setMlevel(rs.getString("mlevel"));
				
				list.add(attractionInfoDto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs, pstmt, conn);
		}
		return list;
	}

}

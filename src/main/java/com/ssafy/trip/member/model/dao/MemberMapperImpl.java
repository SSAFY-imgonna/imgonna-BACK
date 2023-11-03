//package com.ssafy.trip.member.model.dao;
//
//import com.ssafy.trip.member.model.mapper.MemberMapper;
//import com.ssafy.trip.util.DBUtil;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class MemberMapperImpl implements MemberMapper {
//
//	private DBUtil dbUtil = DBUtil.getInstance();
//	private static MemberMapper instance;
//
//	public static MemberMapper getInstance() {
//		if (instance == null) {
//			instance = new MemberMapperImpl();
//		}
//		return instance;
//	}
//
//	@Override
//	public MemberDto getMemberByIdAndPassword(String id, String password) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		MemberDto memberDto = null;
//		try {
//			conn = dbUtil.getConnection();
//
//			StringBuilder sql = new StringBuilder();
//			sql.append("select * from members\n");
//			sql.append("where id = ? and password = ?");
//			pstmt = conn.prepareStatement(sql.toString());
//			pstmt.setString(1, id);
//			pstmt.setString(2, password);
//
//			rs = pstmt.executeQuery();
//
//			if (rs.next()) {
//				memberDto = new MemberDto();
//				memberDto.setId(rs.getString("id"));
//				memberDto.setEmail(rs.getString("email"));
//				memberDto.setName(rs.getString("name"));
//				memberDto.setPassword(rs.getString("password"));
//				memberDto.setPhone(rs.getString("phone"));
//				memberDto.setNickname(rs.getString("nickname"));
//				memberDto.setJoinDate(rs.getString("join_date"));
//				memberDto.setRole(rs.getString("role"));
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			dbUtil.close(conn, pstmt, rs);
//		}
//
//		return memberDto;
//	}
//
//	@Override
//	public int getMemberById(String id) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		int cnt = 0;
//
//		try {
//			conn = dbUtil.getConnection();
//
//			StringBuilder sql = new StringBuilder();
//			sql.append("select count(*) from members\n");
//			sql.append("where id= ? ");
//			pstmt = conn.prepareStatement(sql.toString());
//			pstmt.setString(1, id);
//
//			rs = pstmt.executeQuery();
//			rs.next();
//			cnt = rs.getInt("count");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return cnt;
//	}
//
//	@Override
//	public int getMemberByEmail(String email) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		int cnt = 0;
//
//		try {
//			conn = dbUtil.getConnection();
//
//			StringBuilder sql = new StringBuilder();
//			sql.append("select count(*) from members\n");
//			sql.append("where email= ? ");
//			pstmt = conn.prepareStatement(sql.toString());
//			pstmt.setString(1, email);
//
//			rs = pstmt.executeQuery();
//			rs.next();
//			cnt = rs.getInt("count");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return cnt;
//	}
//
//	@Override
//	public int createMember(MemberDto memberDto) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		int cnt = 0;
//		try {
//			conn = dbUtil.getConnection();
//			StringBuilder sql = new StringBuilder();
//			sql.append("insert into members (id, email, name, password, phone, nickname, role, salt) \n");
//			sql.append("values (?, ?, ?, ?, ?, ?, ?, ?)");
//			pstmt = conn.prepareStatement(sql.toString());
//			pstmt.setString(1, memberDto.getId());
//			pstmt.setString(2, memberDto.getEmail());
//			pstmt.setString(3, memberDto.getName());
//			pstmt.setString(4, memberDto.getPassword());
//			pstmt.setString(5, memberDto.getPhone());
//			pstmt.setString(6, memberDto.getNickname());
//			pstmt.setString(7, memberDto.getRole());
//			pstmt.setString(8, memberDto.getSalt());
//			cnt = pstmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			dbUtil.close(pstmt, conn);
//		}
//		return cnt;
//	}
//
//	@Override
//	public void delete(String id) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		try {
//			conn = dbUtil.getConnection();
//			StringBuilder sql = new StringBuilder();
//			sql.append("delete from members \n");
//			sql.append("where id = ?");
//			pstmt = conn.prepareStatement(sql.toString());
//			pstmt.setString(1, id);
//			pstmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			dbUtil.close(pstmt, conn);
//		}
//	}
//
//	@Override
//	public int updateMember(MemberDto memberDto) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		int cnt = 0;
//		try {
//			conn = dbUtil.getConnection();
//			StringBuilder sql = new StringBuilder();
//			sql.append("update members\n");
//			sql.append("set email=?, name=?, phone=?, nickname=? \n");
//			sql.append("where id=?");
//			pstmt = conn.prepareStatement(sql.toString());
//			pstmt.setString(1, memberDto.getEmail());
//			pstmt.setString(2, memberDto.getName());
//			pstmt.setString(3, memberDto.getPhone());
//			pstmt.setString(4, memberDto.getNickname());
//			pstmt.setString(5, memberDto.getId());
//			cnt = pstmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			dbUtil.close(pstmt, conn);
//		}
//		return cnt;
//	}
//
//	@Override
//	public int updateMemberPasswordById(String id, String password) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		int cnt = 0;
//		try {
//			conn = dbUtil.getConnection();
//			StringBuilder sql = new StringBuilder();
//			sql.append("update members\n");
//			sql.append("set password=? \n");
//			sql.append("where id=?");
//			pstmt = conn.prepareStatement(sql.toString());
//			pstmt.setString(1, password);
//			pstmt.setString(2, id);
//			cnt = pstmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			dbUtil.close(pstmt, conn);
//		}
//		return cnt;
//	}
//
//	@Override
//	public String getMemberIdByEmailAndName(String email, String name) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		String id = null;
//
//		try {
//			conn = dbUtil.getConnection();
//			StringBuilder sql = new StringBuilder();
//			sql.append("select id from members\n");
//			sql.append("where email = ? and name = ?");
//			pstmt = conn.prepareStatement(sql.toString());
//			pstmt.setString(1, email);
//			pstmt.setString(2, name);
//			ResultSet rs = pstmt.executeQuery();
//
//			rs.next();
//			id = rs.getString("id");
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			dbUtil.close(pstmt, conn);
//		}
//		return id;
//	}
//
//	@Override
//	public String getMemberPasswordByIdAndEmailAndPhone(String id, String email, String phone) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		String password = null;
//
//		try {
//			conn = dbUtil.getConnection();
//			StringBuilder sql = new StringBuilder();
//			sql.append("select password from members\n");
//			sql.append("where id = ? and email = ? and phone = ?");
//			pstmt = conn.prepareStatement(sql.toString());
//			pstmt.setString(1, id);
//			pstmt.setString(2, email);
//			pstmt.setString(3, phone);
//			ResultSet rs = pstmt.executeQuery();
//
//			rs.next();
//			password = rs.getString("password");
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			dbUtil.close(pstmt, conn);
//		}
//		return password;
//	}
//
//	@Override
//	public String getSaltById(String id) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		String salt = null;
//
//		try {
//			conn = dbUtil.getConnection();
//			StringBuilder sql = new StringBuilder();
//			sql.append("select salt from members\n");
//			sql.append("where id = ?");
//			pstmt = conn.prepareStatement(sql.toString());
//			pstmt.setString(1, id);
//			ResultSet rs = pstmt.executeQuery();
//
//			rs.next();
//			salt = rs.getString("salt");
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			dbUtil.close(pstmt, conn);
//		}
//		return salt;
//	}
//
//}

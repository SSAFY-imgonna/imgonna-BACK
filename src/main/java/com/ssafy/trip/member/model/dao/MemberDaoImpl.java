package com.ssafy.trip.member.model.dao;

import com.ssafy.trip.member.model.Member;
import com.ssafy.trip.util.DBUtil;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class MemberDaoImpl implements MemberDao {

	private DBUtil dbUtil = DBUtil.getInstance();
	private static MemberDao instance;

	public static MemberDao getInstance() {
		if (instance == null) {
			instance = new MemberDaoImpl();
		}
		return instance;
	}

	@Override
	public Member getMemberByIdAndPassword(String id, String password) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		Member member = null;
		try {
			conn = dbUtil.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("select * from members\n");
			sql.append("where id = ? and password = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			pstmt.setString(2, password);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				member = new Member();
				member.setId(rs.getString("id"));
				member.setEmail(rs.getString("email"));
				member.setName(rs.getString("name"));
				member.setPassword(rs.getString("password"));
				member.setPhone(rs.getString("phone"));
				member.setNickname(rs.getString("nickname"));
				member.setJoinDate(rs.getString("join_date"));
				member.setRole(rs.getString("role"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(conn, pstmt, rs);
		}

		return member;
	}

	@Override
	public int getMemberById(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0;

		try {
			conn = dbUtil.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("select count(*) from members\n");
			sql.append("where id= ? ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt("count");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cnt;
	}

	@Override
	public int getMemberByEmail(String email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0;

		try {
			conn = dbUtil.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("select count(*) from members\n");
			sql.append("where email= ? ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, email);

			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt("count");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cnt;
	}

	@Override
	public int createMember(Member member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into members (id, email, name, password, phone, nickname, role, salt) \n");
			sql.append("values (?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getEmail());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getPassword());
			pstmt.setString(5, member.getPhone());
			pstmt.setString(6, member.getNickname());
			pstmt.setString(7, member.getRole());
			pstmt.setString(8, member.getSalt());
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, conn);
		}
		return cnt;
	}

	@Override
	public void delete(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("delete from members \n");
			sql.append("where id = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

	@Override
	public int updateMember(Member member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("update members\n");
			sql.append("set email=?, name=?, phone=?, nickname=? \n");
			sql.append("where id=?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, member.getEmail());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getPhone());
			pstmt.setString(4, member.getNickname());
			pstmt.setString(5, member.getId());
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, conn);
		}
		return cnt;
	}

	@Override
	public int updateMemberPasswordById(String id, String password) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("update members\n");
			sql.append("set password=? \n");
			sql.append("where id=?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, password);
			pstmt.setString(2, id);
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, conn);
		}
		return cnt;
	}

	@Override
	public String getMemberIdByEmailAndName(String email, String name) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String id = null;

		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select id from members\n");
			sql.append("where email = ? and name = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, email);
			pstmt.setString(2, name);
			ResultSet rs = pstmt.executeQuery();

			rs.next();
			id = rs.getString("id");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, conn);
		}
		return id;
	}

	@Override
	public String getMemberPasswordByIdAndEmailAndPhone(String id, String email, String phone) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String password = null;

		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select password from members\n");
			sql.append("where id = ? and email = ? and phone = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			pstmt.setString(2, email);
			pstmt.setString(3, phone);
			ResultSet rs = pstmt.executeQuery();

			rs.next();
			password = rs.getString("password");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, conn);
		}
		return password;
	}

	@Override
	public String getSaltById(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String salt = null;

		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select salt from members\n");
			sql.append("where id = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();

			rs.next();
			salt = rs.getString("salt");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, conn);
		}
		return salt;
	}

}

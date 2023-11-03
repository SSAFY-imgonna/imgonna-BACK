package com.ssafy.trip.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ssafy.trip.board.model.BoardDto;
import com.ssafy.trip.util.DBUtil;

public class BoardDaoImpl implements BoardDao {
	
	private DBUtil util = DBUtil.getInstance();
	
	private static BoardDao instance = new BoardDaoImpl();
	private BoardDaoImpl() {}

	public static BoardDao getInstance() {
		return instance;
	}

	/*
		insert into board(id, subject, content, type)
		values (?, ?, ?, ?, ?, ?);
	 */
	@Override
	public int createBoard(BoardDto boardDto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = util.getConnection();
			StringBuilder sql = new StringBuilder("insert into board(id, subject, content, type) \n");
			sql.append("values (?, ?, ?, ?)");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, boardDto.getId());
			pstmt.setString(2, boardDto.getSubject());
			pstmt.setString(3, boardDto.getContent());
			pstmt.setString(4, boardDto.getType());
			
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(pstmt, conn);
		}
		return 0;
	}

	/*
		select * from board
		where type = ?
		order by article_no desc
		limit ?, ?
	 */
	@Override
	public List<BoardDto> getBoardList(Map<String, Object> param) {
		List<BoardDto> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = util.getConnection();
			StringBuilder sql = new StringBuilder("select * from board \n");
			sql.append("where type = ? \n");
			sql.append("order by article_no desc \n");
			sql.append("limit ?, ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, (String) param.get("type"));
			pstmt.setInt(2, (Integer) param.get("start"));
			pstmt.setInt(3, (Integer) param.get("listsize"));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardDto boardDto = new BoardDto();
				boardDto.setArticleNo(rs.getInt("article_no"));
				boardDto.setId(rs.getString("id"));
				boardDto.setSubject(rs.getString("subject"));
				boardDto.setContent(rs.getString("content"));
				boardDto.setHit(rs.getInt("hit"));
				boardDto.setRegisterTime(rs.getString("register_time"));
				boardDto.setType(rs.getString("type"));
				
				list.add(boardDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs, pstmt, conn);
		}
		return list;
	}

	@Override
	public int getBoardCount(Map<String, Object> param) {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = util.getConnection();
			StringBuilder sql = new StringBuilder("select count(article_no) from board \n");
			sql.append("where type = ? \n");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, (String) param.get("type"));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cnt = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs, pstmt, conn);
		}
		return cnt;
	}

	@Override
	public BoardDto getBoardByarticleNo(int articleNo) {
		BoardDto boardDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = util.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from board \n");
			sql.append("where article_no = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, articleNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				boardDto = new BoardDto();
				boardDto.setArticleNo(rs.getInt("article_no"));
				boardDto.setId(rs.getString("id"));
				boardDto.setSubject(rs.getString("subject"));
				boardDto.setContent(rs.getString("content"));
				boardDto.setHit(rs.getInt("hit"));
				boardDto.setRegisterTime(rs.getString("register_time"));
				boardDto.setType(rs.getString("type"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs, pstmt, conn);
		}
		return boardDto;
	}

	@Override
	public int modifyHit(int articleNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = util.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("update board \n");
			sql.append("set hit = hit + 1 \n");
			sql.append("where article_no = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, articleNo);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(pstmt, conn);
		}
		return 0;
	}

	@Override
	public int modifyBoard(BoardDto boardDto) {
		// TODO : 글번호에 해당하는 제목과 내용 변경.
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = util.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("update board \n");
			sql.append("set subject = ?, content = ? \n");
			sql.append("where article_no = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, boardDto.getSubject());
			pstmt.setString(2, boardDto.getContent());
			pstmt.setInt(3, boardDto.getArticleNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(pstmt, conn);
		}
		return 0;
	}

	@Override
	public int deleteBoard(int articleNo) {
		// TODO : 글번호에 해당하는 글삭제
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = util.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("delete from board \n");
			sql.append("where article_no = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, articleNo);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(pstmt, conn);
		}
		return 0;
	}

}

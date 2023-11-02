package com.ssafy.trip.board.model;

/**
 * 게시글, 공지글 DTO
 * @author user
 *
 */
public class BoardDto {
	private int articleNo; // 기본키
	private String id; // 작성자 아이디
	private String subject; // 제목
	private String content; // 내용
	private int hit; // 조회수
	private String registerTime; // 작성일자
	private String type; // 게시물, 공지글 여부(board, notice)
	public int getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "BoardDto [articleNo=" + articleNo + ", id=" + id + ", subject=" + subject + ", content=" + content
				+ ", hit=" + hit + ", registerTime=" + registerTime + ", type=" + type + "]";
	}
	
}

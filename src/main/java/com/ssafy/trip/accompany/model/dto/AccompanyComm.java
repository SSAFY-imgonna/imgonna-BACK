package com.ssafy.trip.accompany.model.dto;

import java.io.Serializable;

public class AccompanyComm implements Serializable{
	private int commentNo;
	private int accompanyNo;
	private String id;
	private String commentContent;
	private String regDate;
	
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public int getAccompanyNo() {
		return accompanyNo;
	}
	public void setAccompanyNo(int accompanyNo) {
		this.accompanyNo = accompanyNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
	@Override
	public String toString() {
		return "AccompanyCommDto [commentNo=" + commentNo + ", accompanyNo=" + accompanyNo + ", id=" + id
				+ ", commentContent=" + commentContent + ", regDate=" + regDate + "]";
	}
}

package com.ssafy.imgonna.accompany.model.dto;

import java.io.Serializable;

public class AccompanyComm implements Serializable{
	private int commentNo;
	private int accompanyNo;
	private String id;
	private String commentContent;
	private String regDate;
	private String photo;
	
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
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	@Override
	public String toString() {
		return "AccompanyComm [commentNo=" + commentNo + ", accompanyNo=" + accompanyNo + ", id=" + id
				+ ", commentContent=" + commentContent + ", regDate=" + regDate + ", photo=" + photo + "]";
	}
	
}

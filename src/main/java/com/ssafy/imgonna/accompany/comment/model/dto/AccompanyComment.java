package com.ssafy.imgonna.accompany.comment.model.dto;

public class AccompanyComment {
	private int commentNo;
	private int accompanyNo;
	private String content;
	private String createdTime;
	private String modifiedTime;
	private String id;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public String getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	
	@Override
	public String toString() {
		return "AccompanyComment [commentNo=" + commentNo + ", accompanyNo=" + accompanyNo + ", content=" + content
				+ ", createdTime=" + createdTime + ", modifiedTime=" + modifiedTime + ", id=" + id + ", photo=" + photo
				+ "]";
	}
	
}

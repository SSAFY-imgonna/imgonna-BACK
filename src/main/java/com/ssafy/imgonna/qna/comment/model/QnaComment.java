package com.ssafy.imgonna.qna.comment.model;

public class QnaComment {
	private int commentNo;
	private int qnaNo;
	private String content;
	private String createdTime;
	private String modifiedTime;
	private String id;
	
	
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public int getQnaNo() {
		return qnaNo;
	}
	public void setQnaNo(int qnaNo) {
		this.qnaNo = qnaNo;
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
	
	
	@Override
	public String toString() {
		return "QnaComment [commentNo=" + commentNo + ", qnaNo=" + qnaNo + ", content=" + content + ", createdTime="
				+ createdTime + ", modifiedTime=" + modifiedTime + ", id=" + id + "]";
	}
	
}

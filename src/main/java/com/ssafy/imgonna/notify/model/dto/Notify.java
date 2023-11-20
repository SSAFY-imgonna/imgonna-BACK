package com.ssafy.imgonna.notify.model.dto;

public class Notify {
	private int notifyNo;
	private int tableName;
	private int pkNo;
	private String content;
	private String send;
	private String receive;
	private int isRead;
	private String createdTime;
	
	
	public int getNotifyNo() {
		return notifyNo;
	}
	public void setNotifyNo(int notifyNo) {
		this.notifyNo = notifyNo;
	}
	public int getTableName() {
		return tableName;
	}
	public void setTableName(int tableName) {
		this.tableName = tableName;
	}
	public int getPkNo() {
		return pkNo;
	}
	public void setPkNo(int pkNo) {
		this.pkNo = pkNo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSend() {
		return send;
	}
	public void setSend(String send) {
		this.send = send;
	}
	public String getReceive() {
		return receive;
	}
	public void setReceive(String receive) {
		this.receive = receive;
	}
	public int getIsRead() {
		return isRead;
	}
	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	
	
	@Override
	public String toString() {
		return "Notify [notifyNo=" + notifyNo + ", tableName=" + tableName + ", pkNo=" + pkNo + ", content=" + content
				+ ", send=" + send + ", receive=" + receive + ", isRead=" + isRead + ", createdTime=" + createdTime
				+ "]";
	}

	
}

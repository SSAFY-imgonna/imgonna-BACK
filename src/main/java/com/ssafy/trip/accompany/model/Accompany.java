package com.ssafy.trip.accompany.model;

import com.ssafy.trip.file.model.dto.FileInfoDto;

import java.util.List;

public class Accompany {
	private int accompanyNo;
	private String title;
	private String content;
	private String addr;
	private String joinTime;
	private int currentNum;
	private int limitNum;
	private int hit;
	private String id;
	private String createdTime;
	private String modifiedTime;
	private String status;
	private String themeNo;	
	private List<FileInfoDto> fileInfos;
	
	
	public int getAccompanyNo() {
		return accompanyNo;
	}
	public void setAccompanyNo(int accompanyNo) {
		this.accompanyNo = accompanyNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getJoinTime() {
		return joinTime;
	}
	public void setJoinTime(String joinTime) {
		this.joinTime = joinTime;
	}
	public int getCurrentNum() {
		return currentNum;
	}
	public void setCurrentNum(int currentNum) {
		this.currentNum = currentNum;
	}
	public int getLimitNum() {
		return limitNum;
	}
	public void setLimitNum(int limitNum) {
		this.limitNum = limitNum;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getThemeNo() {
		return themeNo;
	}
	public void setThemeNo(String themeNo) {
		this.themeNo = themeNo;
	}
	public List<FileInfoDto> getFileInfos() {
		return fileInfos;
	}
	public void setFileInfos(List<FileInfoDto> fileInfos) {
		this.fileInfos = fileInfos;
	}
	
	
	@Override
	public String toString() {
		return "Accompany [accompanyNo=" + accompanyNo + ", title=" + title + ", content=" + content + ", addr=" + addr
				+ ", joinTime=" + joinTime + ", currentNum=" + currentNum + ", limitNum=" + limitNum + ", hit=" + hit
				+ ", id=" + id + ", createdTime=" + createdTime + ", modifiedTime="
				+ modifiedTime + ", status=" + status + ", themeNo=" + themeNo + ", fileInfos=" + fileInfos + "]";
	}
	
}

package com.ssafy.trip.accompany.model.dto;

import com.ssafy.trip.file.model.dto.FileInfoDto;

import java.util.List;

public class AccompanyResponseDto {
	private int accompanyNo;
	private String title;
	private String content;
	private String addr;
	private String date;
	private String time;
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
	private boolean isJoin;
	
	
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public void setJoin(boolean isJoin) {
		this.isJoin = isJoin;
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
	public boolean isJoin() {
		return isJoin;
	}
	public void setIsJoin(boolean isJoin) {
		this.isJoin = isJoin;
	}
	
	
	@Override
	public String toString() {
		return "AccompanyResponseDto [accompanyNo=" + accompanyNo + ", title=" + title + ", content=" + content
				+ ", addr=" + addr + ", date=" + date + ", time=" + time + ", joinTime=" + joinTime + ", currentNum="
				+ currentNum + ", limitNum=" + limitNum + ", hit=" + hit + ", id=" + id + ", createdTime=" + createdTime
				+ ", modifiedTime=" + modifiedTime + ", status=" + status + ", themeNo=" + themeNo + ", fileInfos="
				+ fileInfos + ", isJoin=" + isJoin + "]";
	}
	
}

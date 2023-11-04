package com.ssafy.trip.accompany.model;

import java.util.List;

public class AccompanyDto {
	private int accompanyNo;
	private String accompanyTitle;
	private String accompanyContent;
	private String accompanyLoc;
	private String accompanyDate;
	private int accompanyNum;
	private int accompanyTotal;
	private int hit;
	private String id;
	private String nickname;
	private String regDate;
	private List<FileInfoDto> fileInfos;
	
	public int getAccompanyNo() {
		return accompanyNo;
	}
	public void setAccompanyNo(int accompanyNo) {
		this.accompanyNo = accompanyNo;
	}
	public String getAccompanyTitle() {
		return accompanyTitle;
	}
	public void setAccompanyTitle(String accompanyTitle) {
		this.accompanyTitle = accompanyTitle;
	}
	public String getAccompanyContent() {
		return accompanyContent;
	}
	public void setAccompanyContent(String accompanyContent) {
		this.accompanyContent = accompanyContent;
	}
	public String getAccompanyLoc() {
		return accompanyLoc;
	}
	public void setAccompanyLoc(String accompanyLoc) {
		this.accompanyLoc = accompanyLoc;
	}
	public String getAccompanyDate() {
		return accompanyDate;
	}
	public void setAccompanyDate(String accompanyDate) {
		this.accompanyDate = accompanyDate;
	}
	public int getAccompanyNum() {
		return accompanyNum;
	}
	public void setAccompanyNum(int accompanyNum) {
		this.accompanyNum = accompanyNum;
	}
	public int getAccompanyTotal() {
		return accompanyTotal;
	}
	public void setAccompanyTotal(int accompanyTotal) {
		this.accompanyTotal = accompanyTotal;
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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public List<FileInfoDto> getFileInfos() {
		return fileInfos;
	}
	public void setFileInfos(List<FileInfoDto> fileInfos) {
		this.fileInfos = fileInfos;
	}
	
	@Override
	public String toString() {
		return "AccompanyDto [accompanyNo=" + accompanyNo + ", accompanyTitle=" + accompanyTitle + ", accompanyContent="
				+ accompanyContent + ", accompanyLoc=" + accompanyLoc + ", accompanyDate=" + accompanyDate
				+ ", accompanyNum=" + accompanyNum + ", accompanyTotal=" + accompanyTotal + ", hit=" + hit + ", id="
				+ id + ", nickname=" + nickname + ", regDate=" + regDate + ", fileInfos=" + fileInfos + "]";
	}	
	
}

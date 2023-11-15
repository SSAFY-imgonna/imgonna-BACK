package com.ssafy.trip.accompany.model.dto;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.trip.file.model.dto.FileInfoDto;

public class AccompanyRequestDto {
	private int accompanyNo;
	private String title;
	private String content;
	private String addr;
	private String date;
	private String time;
	private String joinTime;
	private int limitNum;
	private MultipartFile[] upfile;
	private List<FileInfoDto> fileInfos;	
	private String status;
	private String themeNo;	
	private String id;
	
	
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
	public String getJoinTime() {
		return joinTime;
	}
	public void setJoinTime(String joinTime) {
		this.joinTime = joinTime;
	}
	public int getLimitNum() {
		return limitNum;
	}
	public void setLimitNum(int limitNum) {
		this.limitNum = limitNum;
	}
	public MultipartFile[] getUpfile() {
		return upfile;
	}
	public void setUpfile(MultipartFile[] upfile) {
		this.upfile = upfile;
	}
	public List<FileInfoDto> getFileInfos() {
		return fileInfos;
	}
	public void setFileInfos(List<FileInfoDto> fileInfos) {
		this.fileInfos = fileInfos;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	@Override
	public String toString() {
		return "AccompanyRequestDto [accompanyNo=" + accompanyNo + ", title=" + title + ", content=" + content
				+ ", addr=" + addr + ", date=" + date + ", time=" + time + ", joinTime=" + joinTime + ", limitNum="
				+ limitNum + ", upfile=" + Arrays.toString(upfile) + ", fileInfos=" + fileInfos + ", status=" + status
				+ ", themeNo=" + themeNo + ", id=" + id + "]";
	}
	

}

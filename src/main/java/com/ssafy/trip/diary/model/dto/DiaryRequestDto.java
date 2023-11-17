package com.ssafy.trip.diary.model.dto;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.trip.file.model.dto.FileInfoDto;

public class DiaryRequestDto {
	private int diaryNo;
	private String travelTime;
	private String attractionName;
	private String id;
	private int contentId;
	private String title;
	private String content;
	private String weather;
	private String emotion;
	private int isPublic;	
	private MultipartFile[] upfile;
	private List<FileInfoDto> fileInfos;
	
	
	public int getDiaryNo() {
		return diaryNo;
	}
	public void setDiaryNo(int diaryNo) {
		this.diaryNo = diaryNo;
	}
	public String getTravelTime() {
		return travelTime;
	}
	public void setTravelTime(String travelTime) {
		this.travelTime = travelTime;
	}
	public String getAttractionName() {
		return attractionName;
	}
	public void setAttractionName(String attractionName) {
		this.attractionName = attractionName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getContentId() {
		return contentId;
	}
	public void setContentId(int contentId) {
		this.contentId = contentId;
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
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	public String getEmotion() {
		return emotion;
	}
	public void setEmotion(String emotion) {
		this.emotion = emotion;
	}
	public int getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(int isPublic) {
		this.isPublic = isPublic;
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
	
	
	@Override
	public String toString() {
		return "DiaryRequestDto [diaryNo=" + diaryNo + ", travelTime=" + travelTime + ", attractionName="
				+ attractionName + ", id=" + id + ", contentId=" + contentId + ", title=" + title + ", content="
				+ content + ", weather=" + weather + ", emotion=" + emotion + ", isPublic=" + isPublic + ", upfile="
				+ Arrays.toString(upfile) + ", fileInfos=" + fileInfos + "]";
	}

	
}


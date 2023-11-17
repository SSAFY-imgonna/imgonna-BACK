package com.ssafy.imgonna.diary.model.dto;

import java.util.List;

import com.ssafy.imgonna.file.model.dto.FileInfoDto;

public class DiaryResponseDto {
	private int diaryNo;
	private String travelTime;
	private String id;
	private int contentId;
	private String title;
	private String content;
	private String createdTime;
	private String weather;
	private String emotion;
	private int isPublic;
	private String latitude;
	private String longitude;
	private int contentTypeId;
	private String attraction;
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
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
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
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public int getContentTypeId() {
		return contentTypeId;
	}
	public void setContentTypeId(int contentTypeId) {
		this.contentTypeId = contentTypeId;
	}
	public String getAttraction() {
		return attraction;
	}
	public void setAttraction(String attraction) {
		this.attraction = attraction;
	}
	public List<FileInfoDto> getFileInfos() {
		return fileInfos;
	}
	public void setFileInfos(List<FileInfoDto> fileInfos) {
		this.fileInfos = fileInfos;
	}
	
	
	@Override
	public String toString() {
		return "DiaryResponseDto [diaryNo=" + diaryNo + ", travelTime=" + travelTime + ", id=" + id + ", contentId="
				+ contentId + ", title=" + title + ", content=" + content + ", createdTime=" + createdTime
				+ ", weather=" + weather + ", emotion=" + emotion + ", isPublic=" + isPublic + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", contentTypeId=" + contentTypeId + ", attraction=" + attraction
				+ ", fileInfos=" + fileInfos + "]";
	}
	
}
